package de.mediapool.server.entities.media.movies.controller;

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

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.media.movies.domain.MovieMediaNodeDTO;
import de.mediapool.server.entities.media.movies.repository.MovieMediaRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;

@RestController
@RequestMapping("/rest/movies")
public class MovieMediaController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(MovieMediaController.class);

	@Autowired
	private MovieMediaRepository movieRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result<MovieMediaNodeDTO> getMovies() {
		logger.debug("Invoking: getMovies()");
		
		Result<MovieMediaNodeDTO> findAll = movieRepository.findAll();

		return findAll;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public MovieMediaNodeDTO getMovie(@PathVariable("id") Long id) {
		logger.debug("Invoking: getMovie(id)");

		MovieMediaNodeDTO movies = movieRepository.findOne(id);

		return movies;
	}

	@RequestMapping
	public List<MovieMediaNodeDTO> findMovieByTitle(String title) {
		List<MovieMediaNodeDTO> movieList = movieRepository.findByTitle(title);
		return movieList;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteMovie(@PathVariable("id") Long id) {
		movieRepository.delete(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MovieMediaNodeDTO createMovie(@RequestBody MovieMediaNodeDTO newMovie, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createMovie(newMovie)");

		MovieMediaNodeDTO save = movieRepository.save(newMovie);

		return save;
	}

}
