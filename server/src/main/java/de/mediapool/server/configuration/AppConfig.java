package de.mediapool.server.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@ComponentScan("de.mediapool.server") 
@EnableWebMvc   
public class AppConfig {

}
