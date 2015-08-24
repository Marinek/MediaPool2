package de.mediapool.server.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.users.domain.UserNodeDTO;
import de.mediapool.server.users.repository.UserRepository;

public class MPUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		UserNodeDTO findByUsername = userRepository.findByUsername(arg0);
		
		if(findByUsername == null) {
			throw new UsernameNotFoundException("Unknown user: " + arg0);
		}
		
		return new MPUserDetails(findByUsername);
	}

}
