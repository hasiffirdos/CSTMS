package com.wego.cstms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

//@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
//                .allowedOriginPatterns("/**")
//                .allowedOrigins("http://localhost:3000/")
//                .allowedMethods(String.valueOf(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")));
    }
}