package com.br.theportfolio.config;

import com.br.theportfolio.security.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    public WebConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/experiencias/**")
                .addPathPatterns("/api/biografia/**")
                .excludePathPatterns("/v3/**", "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**");
    }
}

