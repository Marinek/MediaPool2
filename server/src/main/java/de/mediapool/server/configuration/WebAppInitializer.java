package de.mediapool.server.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import de.mediapool.server.security.simple.config.SecurityConfiguration;

/**
 * 
 * @author marcinek
 *
 */
@Order(2)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
	@Autowired
	private SimpleCORSFilter corsFilter = new SimpleCORSFilter();
	
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {SecurityConfiguration.class};
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {AppConfig.class};
    }
 
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {corsFilter, characterEncodingFilter};
    }
 
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {        
        registration.setInitParameter("spring.profiles.active", "default");
    }
}