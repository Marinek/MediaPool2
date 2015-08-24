package de.mediapool.server.security.simple.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.mediapool.server.users.domain.UserNodeDTO;

@ControllerAdvice
public class SecurityControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityControllerAdvice.class);
 
	@ModelAttribute
	public UserNodeDTO currentUser(@AuthenticationPrincipal Authentication authentication) {
		logger.debug("Invoking: currentUser(authentication)");
		
		if (null == authentication){
			return null;
		}
		
		logger.debug(authentication.toString());
		
		return new UserNodeDTO();
	}
}