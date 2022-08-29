package com.memphis.cafe.bean;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.memphis.filter.AdminFilter;

@Component
public class RegistrationFilter {

	@Bean
    public FilterRegistrationBean adminFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AdminFilter());

        // In case you want the filter to apply to specific URL patterns only
        registration.addUrlPatterns("/inicio/*");
        return registration;
    }
}
