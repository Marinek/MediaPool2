package de.mediapool.server.movies.controller;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.movies.domain.MovieNodeDTO;
import de.mediapool.server.movies.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired MovieRepository movieRepository;
	
	@PostConstruct
	public void init () {
		logger.debug("Invoking: init()");
	}
	
	@RequestMapping("/get/{id}")
	public MovieNodeDTO getMovie(@PathVariable("id") String id) {
		logger.debug("Invoking: getMovie(id)");
		
		MovieNodeDTO movie = movieRepository.findById(id);
		
		return movie;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MovieNodeDTO createMovie(@RequestBody MovieNodeDTO newMovie) {
		logger.debug("Invoking: createMovie(newMovie)");
		
		if(newMovie.getId() != null) {
			return newMovie;
		}
		
		newMovie.setId(UUID.randomUUID().toString());
		
		movieRepository.save(newMovie);
		
		return newMovie;
	}
	
}
