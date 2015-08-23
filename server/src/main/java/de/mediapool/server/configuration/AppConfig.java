package de.mediapool.server.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("de.mediapool")
@EnableWebMvc
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}
	
}
