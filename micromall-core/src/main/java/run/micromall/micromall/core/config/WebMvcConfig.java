package run.micromall.micromall.core.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author songhaozhi
 * @since 2021/02/16
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 释放静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 通过addResourceHandler添加资源映射路径，然后通过addResourceLocations来指定路径。可以访问自定义upload文件夹
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
