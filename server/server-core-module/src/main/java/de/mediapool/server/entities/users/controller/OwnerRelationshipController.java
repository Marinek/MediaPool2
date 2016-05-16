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
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.users.domain.OwnerRelationship;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.repository.OwnerRelationshipRepository;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/ownership")
public class OwnerRelationshipController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(OwnerRelationshipController.class);

	@Autowired
	private OwnerRelationshipRepository ownerRelationshipRepository;

	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.AUTHENTICATED)
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public OwnerRelationship create(@AuthenticationPrincipal User user, Product product) {
		OwnerRelationship relation = new OwnerRelationship();
		Set<OwnerRelationship> owendProducts = user.getOwendProducts();

		if (owendProducts == null) {
			owendProducts = new HashSet<>();
		}

		relation.setSince(new Date());
		relation.setOwnes(product);
		relation.setUser(user);

		relation.setCondition("Good");
		relation.setKnown(true);
		relation.setLastUsed(new Date());
		relation.setRating(5);
		relation.setVisible(Visibility.FRIENDS);
		relation.setStorageLocation("Map");
		relation.setStorageNumber(1);

		owendProducts.add(relation);

		ownerRelationshipRepository.save(relation);

		return relation;
	}
}
