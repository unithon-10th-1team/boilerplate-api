package com.goofy.boilerplate.config.web;

import static com.goofy.boilerplate.common.consts.Static.*;

import com.goofy.boilerplate.common.helper.EnvironmentHelper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final EnvironmentHelper environmentHelper;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().servers(swaggerServers()).info(swaggerInfo());
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
        return new Info().title("Boilerplate API").description("Boilerplate API").license(license);
    }
}
