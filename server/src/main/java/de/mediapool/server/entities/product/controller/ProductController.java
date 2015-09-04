package de.mediapool.server.entities.product.controller;

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
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import de.mediapool.server.entities.product.repository.ProductRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/movieproduct")
public class ProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ProductNodeDTO geProduct(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getProductMovie(id)");

		ProductNodeDTO product = productRepository.findOne(id);

		return product;
	}

	@RequestMapping
	public List<MovieNodeDTO> findProdcutMovieByTitle(String title) {
		return new ArrayList<>();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ProductNodeDTO createMovie(@RequestBody ProductNodeDTO newProduct, @AuthenticationPrincipal UserNodeDTO currentUser) {
		logger.debug("Invoking: createProductMovie(newProductMovie)");

		ProductNodeDTO save = productRepository.save(newProduct);

		return save;
	}

}
