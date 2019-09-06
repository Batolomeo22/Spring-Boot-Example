package com.brazo.ep2_web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class JspResolver {

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Value("${spring.mvc.view.view-name}")
    private String viewname;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    @Value("${spring.mvc.view.order}")
    private int order;

    @Bean
    InternalResourceViewResolver jspViewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(prefix);
        viewResolver.setViewNames(viewname);
        viewResolver.setSuffix(suffix);
        viewResolver.setOrder(order);

        return viewResolver;
    }
}
