package de.mediapool.server.entities.users.controller;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.entities.users.domain.UserRoleNodeDTO;
import de.mediapool.server.entities.users.repository.UserRepository;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/user")
public class UserController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_ADMIN)
	@RequestMapping(value = "{id}", method=RequestMethod.GET)
	public UserNodeDTO getUser(@PathVariable("id") String id) {
		return userRepository.findById(id);
	}

	@PreAuthorize(PreAuthorization.AUTHENTICATED)
	@RequestMapping(value="current", method=RequestMethod.GET)
	public UserNodeDTO getUser(@AuthenticationPrincipal UserNodeDTO userNode) {
		return userNode;
	}

	@RequestMapping(value="register", method=RequestMethod.POST)
	public UserNodeDTO createUser(@RequestBody UserNodeDTO userNodeDTO) {
		userNodeDTO.setId(UUID.randomUUID().toString());

		{
			UserRoleNodeDTO userRoleUser = new UserRoleNodeDTO();
			userRoleUser.setName("USER");
			userNodeDTO.getRoles().add(userRoleUser);
		}

		{
			UserRoleNodeDTO userRoleUser = new UserRoleNodeDTO();
			userRoleUser.setName("ADMIN");
			userNodeDTO.getRoles().add(userRoleUser);
		}

		return userRepository.save(userNodeDTO);
	}
}
