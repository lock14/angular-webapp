package org.lock14.angularwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // send any unknown path that ends in a word to index.html
        // Angular will handle the route
        registry.addViewController("/**/{spring:\\w+}")
                .setViewName("forward:/");
    }
}
