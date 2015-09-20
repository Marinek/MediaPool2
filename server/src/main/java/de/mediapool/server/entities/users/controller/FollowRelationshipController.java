package de.mediapool.server.entities.users.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.users.domain.FellowshipType;
import de.mediapool.server.entities.users.domain.FollowRelationship;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.repository.FollowRelationshipRepository;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/follow")
public class FollowRelationshipController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(FollowRelationshipController.class);

	@Autowired
	private FollowRelationshipRepository followRelationshipRepository;

	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.AUTHENTICATED)
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public FollowRelationship createFellowship(@AuthenticationPrincipal User user, User fUser) {
		FollowRelationship relation = new FollowRelationship();

		Set<FollowRelationship> followedUser = user.getFollowedUser();
		if (followedUser == null) {
			followedUser = new HashSet<>();
		}

		relation.setSince(new Date());

		relation.setFellowshipType(FellowshipType.FRIEND);

		relation.setUser(user);

		relation.setFUser(fUser);

		followedUser.add(relation);

		followRelationshipRepository.save(relation);

		return relation;
	}
}
