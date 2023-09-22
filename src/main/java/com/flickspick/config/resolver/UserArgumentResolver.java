package com.flickspick.config.resolver;

import static com.flickspick.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;

import com.flickspick.auth.application.TokenService;
import com.flickspick.auth.model.AuthToken;
import com.flickspick.auth.model.AuthUser;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        var httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        var accessToken = httpServletRequest.getHeader(AUTH_TOKEN_HEADER_KEY);

        if (accessToken == null) {
            if (parameter.isOptional()) {
                return null;
            }

            accessToken = "";
        }

        var token = new AuthToken(accessToken);

        return tokenService.getAuthUser(token);
    }
}
