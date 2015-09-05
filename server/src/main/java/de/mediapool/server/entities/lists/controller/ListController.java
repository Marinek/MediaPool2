package de.mediapool.server.entities.lists.controller;

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
import de.mediapool.server.entities.lists.domain.ListNodeDTO;
import de.mediapool.server.entities.lists.repository.ListRepository;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.security.domain.MPUserDetails;

@RestController
@RequestMapping("/rest/list")
public class ListController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ListController.class);

	@Autowired
	private ListRepository listRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ListNodeDTO getList(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getList(id, test)");

		ListNodeDTO list = listRepository.findOne(id);

		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ListNodeDTO createList(@RequestBody String title, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createMovie(newList, currentUser)");

		currentUser.createNewList(title);

		ListNodeDTO newList = currentUser.getListByTitle(title);

		listRepository.save(newList);

		return newList;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void addToList(@PathVariable("id") Long id, @RequestBody ProductNodeDTO newProduct, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: addToList(newMovie, currentUser)");

		if (newProduct == null) {
			return;
		}

		ListNodeDTO currentList = listRepository.findOne(id);

		currentList.addToList(newProduct);

		listRepository.save(currentList);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: delete(id, currentUser)");

		ListNodeDTO currentList = listRepository.findOne(id);

		listRepository.delete(currentList);
	}

}
