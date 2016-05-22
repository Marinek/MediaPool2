package de.mediapool.server.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author marcinek
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootApp.class};
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {};
    }
 
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {};
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {        
        registration.setInitParameter("spring.profiles.active", "default");
    }
}