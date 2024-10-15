package org.frisbeemall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 URL /image/** 到本地 static/image 目录
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/image/");
    }
}
