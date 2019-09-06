package com.brazo.ep2_web.filter;


import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


/**
 * 1.实现 Filter 接口，实现 Filter 方法
 * 2.添加@Configuration 注解，将自定义Filter加入过滤链
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyDefFilter");
        registration.setOrder(1);

        return registration;
    }

    public class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterchain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;

            System.out.println("This is my filter:" + request.getRequestURI());


            sresponse.setCharacterEncoding("GBK");
            filterchain.doFilter(srequest, sresponse);
        }

        @Override
        public void destroy() {

        }
    }
}
