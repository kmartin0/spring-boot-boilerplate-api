package com.boilerplate.boilerplateapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig {
	@Bean
	public FilterRegistrationBean<CorsFilter> customCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(
				Arrays.asList(
						"http://localhost:4200",
						"https://localhost:4200",
						"https://web.postman.co"
				)
		);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setExposedHeaders(Collections.singletonList("WWW-Authenticate"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

		//Important. tell Spring to load this filter at the right point in the chain
		//(with an order of precedence higher than oauth2's filters)
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		bean.addUrlPatterns("*"); // Set bean to all url patterns so the authorization server will also use this cors policy.

		return bean;
	}
}