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
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.product.movies.domain.ProductMovieNodeDTO;
import de.mediapool.server.entities.product.movies.repository.ProductMovieRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/movieproduct")
public class ProductMovieController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ProductMovieController.class);

	@Autowired
	private ProductMovieRepository productMovieRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ProductMovieNodeDTO getMovieProduct(@PathVariable("id") String id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getProductMovie(id)");

		ProductMovieNodeDTO productMovie = productMovieRepository.findById(id);

		return productMovie;
	}

	@RequestMapping
	public List<MovieNodeDTO> findProdcutMovieByName(String name) {
		return new ArrayList<>();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ProductMovieNodeDTO createMovie(@RequestBody ProductMovieNodeDTO newProductMovie, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createProductMovie(newProductMovie)");

		ProductMovieNodeDTO save = productMovieRepository.save(newProductMovie);

		return save;
	}

}
