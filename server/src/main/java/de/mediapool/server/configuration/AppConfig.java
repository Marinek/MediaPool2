package de.mediapool.server.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@EnableAutoConfiguration
@ComponentScan("de.mediapool.server") 
@EnableWebMvc   
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class AppConfig {

}
