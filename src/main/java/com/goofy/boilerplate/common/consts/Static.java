package com.goofy.boilerplate.common.consts;

public class Static {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public static final int MILLI_TO_SECOND = 1000;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int TOO_MANY_REQUESTS = 429;
    public static final int INTERNAL_SERVER = 500;
    public static final int SERVICE_UNAVAILABLE = 503;

    public static final String PROD_SERVER_URL = "https://example.com";
    public static final String DEV_SERVER_URL = "http://localhost:8080";
    public static final String LOCAL_SERVER_URL = "http://localhost:8080";
    public static final String IMAGE_DOMAIN = "https://image.example.com";

    public static final String PROD = "prod";
    public static final String DEV = "dev";
    public static final String LOCAL = "local";

    public static final String GITHUB_URL = "https://github.com/unithon-10th-1team/boilerplate-api";
    public static final String SERVER_NAME = "boilerplate-api";

    public static final String[] SwaggerPatterns = {
        "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
    };
}
