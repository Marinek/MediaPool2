package de.mediapool.server.rest.module.entities.listing.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.lists.domain.Listing;
import de.mediapool.server.entities.lists.repository.ListingRepository;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.security.domain.MPUserDetails;

@RestController
@RequestMapping("/rest/list")
public class ListingController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ListingController.class);

	@Autowired
	private ListingRepository listRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Listing getList(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getList(id, test)");

		Listing list = listRepository.findOne(id);

		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Listing createList(@RequestBody String title, @AuthenticationPrincipal User currentUser) {
		logger.debug("Invoking: createMovie(newList, currentUser)");

		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void addToList(@PathVariable("id") Long id, @RequestBody Product newProduct, @AuthenticationPrincipal User currentUser) {
		logger.debug("Invoking: addToList(newMovie, currentUser)");

		if (newProduct == null) {
			return;
		}

		Listing currentList = listRepository.findOne(id);

		currentList.addToList(newProduct);

		listRepository.save(currentList);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, @AuthenticationPrincipal User currentUser) {
		logger.debug("Invoking: delete(id, currentUser)");

		Listing currentList = listRepository.findOne(id);

		listRepository.delete(currentList);
	}

}
