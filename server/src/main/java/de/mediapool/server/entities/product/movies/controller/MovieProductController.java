package de.mediapool.server.entities.product.movies.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.media.movies.domain.MovieMediaNodeDTO;
import de.mediapool.server.entities.product.movies.domain.MovieProductNodeDTO;
import de.mediapool.server.entities.product.movies.repository.MovieProductRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/movieproduct")
public class MovieProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(MovieProductController.class);

	@Autowired
	private MovieProductRepository productMovieRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public MovieProductNodeDTO getMovieProduct(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getProductMovie(id)");

		MovieProductNodeDTO productMovie = productMovieRepository.findOne(id);

		return productMovie;
	}

	@RequestMapping
	public List<MovieMediaNodeDTO> findProdcutMovieByTitle(String title) {
		return new ArrayList<>();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MovieProductNodeDTO createMovie(@RequestBody MovieProductNodeDTO newProductMovie, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createProductMovie(newProductMovie)");

		MovieProductNodeDTO save = productMovieRepository.save(newProductMovie);

		return save;
	}

}
