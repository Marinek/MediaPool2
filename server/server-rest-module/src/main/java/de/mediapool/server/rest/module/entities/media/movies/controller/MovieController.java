package de.mediapool.server.rest.module.entities.media.movies.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.builder.ember.EmberModel;
import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.media.domain.PersonsRelationship;
import de.mediapool.server.entities.media.movies.domain.Movie;
import de.mediapool.server.entities.media.movies.repository.MovieRepository;
import de.mediapool.server.entities.users.domain.User;

@RestController
@RequestMapping("/rest/movies")
public class MovieController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieRepository movieRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public EmberModel getMovies() {
		logger.debug("Invoking: getMovies()");

		Result<Movie> findAll = movieRepository.findAll();

		return new EmberModel.Builder<Movie>(Movie.class, findAll)
				.build();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public EmberModel getMovie(@PathVariable("id") Long id) {
		logger.debug("Invoking: getMovie(id)");

		Movie movies = movieRepository.findOne(id);

		return new EmberModel.Builder<Movie>(movies)
				.sideLoad(PersonsRelationship.class, movies.getPersons())
				.build();
	}

	@RequestMapping
	public List<Movie> findMovieByTitle(String title) {
		List<Movie> movieList = movieRepository.findByTitle(title);
		return movieList;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteMovie(@PathVariable("id") Long id) {
		movieRepository.delete(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Movie createMovie(@RequestBody Movie newMovie, @AuthenticationPrincipal User currentUser) {
		logger.debug("Invoking: createMovie(newMovie)");

		Movie save = movieRepository.save(newMovie);

		return save;
	}

}
