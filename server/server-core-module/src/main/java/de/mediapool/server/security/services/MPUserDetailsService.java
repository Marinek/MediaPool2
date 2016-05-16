package de.mediapool.server.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.repository.UserRepository;
import de.mediapool.server.security.domain.MPUserDetails;

public class MPUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		User findByUsername = userRepository.findByUsername(arg0);
		
		if(findByUsername == null) {
			throw new UsernameNotFoundException("Unknown user: " + arg0);
		}
		
		return new MPUserDetails(findByUsername);
	}

}
