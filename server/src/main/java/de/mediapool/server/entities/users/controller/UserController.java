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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.lists.domain.Listing;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.users.domain.FellowshipType;
import de.mediapool.server.entities.users.domain.FollowRelationship;
import de.mediapool.server.entities.users.domain.OwnerRelationship;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.domain.UserRole;
import de.mediapool.server.entities.users.repository.UserRepository;
import de.mediapool.server.entities.users.repository.UserRoleRepository;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/user")
public class UserController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_ADMIN)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return userRepository.findOne(id);
	}

	@PreAuthorize(PreAuthorization.AUTHENTICATED)
	@RequestMapping(value = "current", method = RequestMethod.GET)
	public User getUser(@AuthenticationPrincipal User userNode) {
		return userNode;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {

		{
			UserRole userRoleUser = new UserRole();
			userRoleUser.setTitle("USER");
			user.getRoles().add(userRoleUser);
		}

		// {
		// UserRole userRoleUser = new UserRole();
		// userRoleUser.setTitle("ADMIN");
		// user.getRoles().add(userRoleUser);
		// }

		return userRepository.save(user);
	}

	public Listing createNewList(String title, User user) {
		Set<Listing> listing = user.getCreatedLists();
		if (listing == null) {
			listing = new HashSet<>();
		}
		Listing list = new Listing(title);
		list.setCreated(new Date());
		listing.add(list);
		user.setCreatedLists(listing);
		userRepository.save(user);

		return list;

	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// private Listing getListByTitle(String title, User user) {
	// Listing list = null;
	// Set<Listing> listing = user.getCreatedLists();
	//
	// if (listing != null) {
	// for (Listing lnd : listing) {
	// if (lnd.getTitle().equals(title)) {
	// list = lnd;
	// }
	// }
	// }
	// return list;
	// }

	public void addRole(String title, User user) {
		// Role
		Set<UserRole> roles = user.getRoles();
		if (roles == null) {
			roles = new HashSet<>();
		}
		UserRole userRole = userRoleRepository.findByTitle(title);
		if (userRole == null) {
			userRole = new UserRole();
			userRole.setTitle(title);
		}
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Deprecated
	public OwnerRelationship owens(Product product, User user) {
		OwnerRelationship relation = new OwnerRelationship();
		Set<OwnerRelationship> owendProducts = user.getOwendProducts();

		if (owendProducts == null) {
			owendProducts = new HashSet<>();
		}

		relation.setSince(new Date());
		relation.setOwnes(product);
		relation.setUser(user);
		owendProducts.add(relation);

		userRepository.save(user);

		return relation;
	}

	@Deprecated
	public FollowRelationship follows(User fUser, User user) {
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

		userRepository.save(user);

		return relation;
	}

}
