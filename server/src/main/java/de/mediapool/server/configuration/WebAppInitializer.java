package de.mediapool.server.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import de.mediapool.server.security.simple.config.SecurityConfiguration;

/**
 * 
 * @author marcinek
 *
 */
public class WebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

	public WebAppInitializer() {
		super(SecurityConfiguration.class, AppConfig.class);

		logger.debug("Invoking: WebAppInitializer()");
	}
}