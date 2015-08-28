package de.mediapool.server.entities.users.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.entities.users.domain.UserRoleNodeDTO;
import de.mediapool.server.entities.users.repository.UserRepository;

@RestController
@RequestMapping("/public/rest/user")
public class UserController implements MPController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/{id}")
	public UserNodeDTO getUser(@PathVariable("id") String id) {
		return userRepository.findById(id);
	}
	
	@RequestMapping(value="register")
	private void createUser() {
		UserNodeDTO user = new UserNodeDTO();
		
		user.setGraphId(2l);
		user.setId(UUID.randomUUID().toString());
		user.setPassword("root");
		user.setUsername("admin");
		
		UserRoleNodeDTO userRoleUser = new UserRoleNodeDTO();
		
		userRoleUser.setName("USER");
		
		user.getRoles().add(userRoleUser);
		
		userRepository.save(user);
	}
}
