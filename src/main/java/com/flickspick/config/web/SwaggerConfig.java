package com.flickspick.config.web;

import static com.flickspick.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;
import static com.flickspick.common.consts.Static.DEV;
import static com.flickspick.common.consts.Static.DEV_SERVER_URL;
import static com.flickspick.common.consts.Static.GITHUB_URL;
import static com.flickspick.common.consts.Static.LOCAL_SERVER_URL;
import static com.flickspick.common.consts.Static.PROD;
import static com.flickspick.common.consts.Static.PROD_SERVER_URL;
import static com.flickspick.common.consts.Static.SERVER_NAME;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.helper.EnvironmentHelper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final EnvironmentHelper environmentHelper;

    @PostConstruct
    public void init() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(AuthUser.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(swaggerServers())
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        AUTH_TOKEN_HEADER_KEY,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.APIKEY)
                                                .in(SecurityScheme.In.HEADER)
                                                .name(AUTH_TOKEN_HEADER_KEY)
                                                .description(
                                                        "인증이 필요한 경우 ex) X-FP-AUTH-TOKEN xxxxxxx")))
                .security(List.of(new SecurityRequirement().addList(AUTH_TOKEN_HEADER_KEY)))
                .info(swaggerInfo());
    }

    private List<Server> swaggerServers() {
        Server server = new Server();
        String serverUrl = getServerUrl();
        server.url(serverUrl);
        return List.of(server);
    }

    private String getServerUrl() {
        switch (environmentHelper.getCurrentProfile()) {
            case PROD:
                return PROD_SERVER_URL;
            case DEV:
                return DEV_SERVER_URL;
            default:
                return LOCAL_SERVER_URL;
        }
    }

    private Info swaggerInfo() {
        License license = new License();
        license.url(GITHUB_URL);
        license.setName(SERVER_NAME);
        return new Info().title("Flicks Pick").description("극락 영화 추천").license(license);
    }
}
