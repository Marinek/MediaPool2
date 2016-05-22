package de.mediapool.server.mvc.module.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ThymeleafLayoutInterceptor thymleafInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(thymleafInterceptor);
    }
    
}