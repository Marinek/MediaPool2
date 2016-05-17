package de.mediapool.server.mvc.module.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
public class ThemeleafConfig {

	//start Thymeleaf specific configuration
	@Bean(name ="templateResolver")	
	public SpringResourceTemplateResolver getTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("XHTML");
		return templateResolver;
	}
	
	@Bean(name ="templateEngine")	    
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.setTemplateResolver(getTemplateResolver());
		return templateEngine;
	}
	
	@Bean(name="viewResolver")
	public ThymeleafViewResolver getViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
		viewResolver.setTemplateEngine(getTemplateEngine());
		return viewResolver;
	}
	//end Thymeleaf specific configuration
}
