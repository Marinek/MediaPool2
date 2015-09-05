package de.mediapool.server.entities.product.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.builder.ember.EmberModel;
import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.product.repository.ProductRepository;
import de.mediapool.server.security.domain.MPUserDetails;

@RestController
@RequestMapping("/rest/products")
public class ProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public EmberModel getProduct(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getProductMovie(id)");

		Product product = productRepository.findOne(id);

		return new EmberModel.Builder<>(product).build();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public EmberModel findAll() {
		Result<Product> allProducts = productRepository.findAll();
		
		return new EmberModel.Builder<Product>(Product.class, allProducts)
				.build();
		
	}

}
