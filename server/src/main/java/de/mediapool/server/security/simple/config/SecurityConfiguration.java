package de.mediapool.server.security.simple.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import de.mediapool.server.security.services.MPUserDetailsService;
import de.mediapool.server.security.simple.controller.RESTAuthenticationEntryPoint;
import de.mediapool.server.security.simple.controller.RESTAuthenticationFailureHandler;
import de.mediapool.server.security.simple.controller.RESTAuthenticationSuccessHandler;

@ComponentScan("de.mediapool.server")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	/**
	 * This section defines the user accounts which can be used for
	 * authentication as well as the roles each user has.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Invoking: configure(auth)");

		auth.userDetailsService(customUserDetailsService());
		
//		auth.inMemoryAuthentication()
//		.withUser("greg").password("greg").roles("USER").and()
//		.withUser("ollie").password("gierke").roles("USER", "ADMIN");
	}
	
	@Bean   
	public UserDetailsService customUserDetailsService() {
		return new MPUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("Invoking: configure(http)");
		http.authorizeRequests().antMatchers("/rest/**").authenticated();
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);
		http.logout().logoutUrl("/logout");
	}

	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}
}