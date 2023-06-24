package com.project.coffeshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestAuthorizerConfig implements WebMvcConfigurer {

    private final RequestInterceptor requestInterceptor;

    public RequestAuthorizerConfig(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .excludePathPatterns("/api/user/sign-up")
                .excludePathPatterns("/api/user/sign-in")
                .excludePathPatterns("/api/user/refresh");

    }
}
