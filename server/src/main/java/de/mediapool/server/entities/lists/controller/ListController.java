package de.mediapool.server.entities.lists.controller;

import java.util.Date;

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
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
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
	public ListNodeDTO getList(@PathVariable("id") String id, @AuthenticationPrincipal MPUserDetails  test) {
		logger.debug("Invoking: getList(id, test)");

		ListNodeDTO list = listRepository.findById(id);

		return list;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ListNodeDTO createList(@RequestBody ListNodeDTO newList, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createMovie(newList, currentUser)");
		
		newList.setCreated(new Date());
		newList.setCreatedBy(currentUser);
		
		ListNodeDTO savedList = listRepository.save(newList);

		return savedList;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void addToList(@PathVariable("id") String id, @RequestBody MovieNodeDTO newMovie, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: addToList(newMovie, currentUser)");
		
		if(newMovie == null) {
			return;
		}
		
		ListNodeDTO currentList = listRepository.findById(id);
		
		currentList.addToList(newMovie);
		
		listRepository.save(currentList);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: delete(id, currentUser)");
		
		ListNodeDTO currentList = listRepository.findById(id);
		
		listRepository.delete(currentList);
	}
	

}
