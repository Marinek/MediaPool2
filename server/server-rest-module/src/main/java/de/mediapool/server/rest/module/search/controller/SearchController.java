package de.mediapool.server.rest.module.search.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.builder.ember.EmberModel;
import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.media.movies.domain.Movie;
import de.mediapool.server.search.domain.SearchCriteria;
import de.mediapool.server.search.repository.impl.MovieSearchRepository;

@RestController
@RequestMapping(value = "/rest/search")
public class SearchController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private MovieSearchRepository searchRepo;
	
	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public EmberModel search(@RequestBody SearchCriteria searchCriteria) {
		
		Collection<Movie> findBy = searchRepo.findBy(searchCriteria);
		
		return new EmberModel.Builder<Movie>(Movie.class, findBy).build();
	}
}
