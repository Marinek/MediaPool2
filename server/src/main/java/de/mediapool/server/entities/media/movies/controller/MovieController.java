package de.mediapool.server.entities.media.movies.controller;

import java.util.List;

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
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.media.movies.repository.MovieRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;

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

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public MovieWrapper getMovie(@PathVariable("id") Long id) {
		logger.debug("Invoking: getMovie(id)");

		MovieWrapper movie = new MovieWrapper();
		MovieNodeDTO movies = movieRepository.findOne(id);
		
		movie.setMovie(movies);

		return movie;
	}

	@RequestMapping
	public List<MovieNodeDTO> findMovieByTitle(String title) {
		List<MovieNodeDTO> movieList = movieRepository.findByTitle(title);
		return movieList;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteMovie(@PathVariable("id") Long id) {
		movieRepository.delete(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MovieNodeDTO createMovie(@RequestBody MovieNodeDTO newMovie, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createMovie(newMovie)");

		MovieNodeDTO save = movieRepository.save(newMovie);

		return save;
	}

}
