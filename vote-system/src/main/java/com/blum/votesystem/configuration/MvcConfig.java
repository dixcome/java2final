package com.blum.votesystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/stats").setViewName("stats");
        registry.addViewController("/useredit").setViewName("forms/user-edit");
        registry.addViewController("/questionadd").setViewName("forms/question-add");
        registry.addViewController("/answeradd").setViewName("forms/answer-add");

    }

}
