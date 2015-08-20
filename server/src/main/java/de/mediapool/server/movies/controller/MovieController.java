package de.mediapool.server.movies.controller;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.movies.domain.MovieNodeDTO;
import de.mediapool.server.movies.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired MovieRepository movieRepository;

	@Autowired GraphDatabase graphDatabase;
	
	@PostConstruct
	public void init () {
		logger.debug("Invoking: init()");
	}
	
	@RequestMapping("/{id}")
	public MovieNodeDTO getMovie(@PathParam("id") String id) {
		logger.debug("Invoking: getMovie(id)");
		
		MovieNodeDTO movie = movieRepository.findById(id);
		
		return movie;
	}
	
	@PostConstruct
	public MovieNodeDTO createMovie(@RequestBody MovieNodeDTO newMovie) {
		if(newMovie.getId() != null) {
			return newMovie;
		}
		
		newMovie.setId(UUID.randomUUID().toString());
		
		movieRepository.save(newMovie);
		
		return newMovie;
	}
	
}
