package de.mediapool.server.mvc.module.entities.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.product.repository.ProductRepository;

@Controller
public class ProductController implements MPController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/products")
	public String getAllProducts(Model model) {
		Result<Product> listProducts = productRepository.findAll();
		
		model.addAttribute("products", listProducts);
		
		return "views/products/listProducts";
	}

	@RequestMapping("/product")
	public String getProduct(@RequestParam(value = "id") Long productId, Model model) {
		Result<Product> listProducts = productRepository.findAll();
		
		model.addAttribute("products", listProducts);
		
		model.addAttribute("prod", productRepository.findOne(productId));
		
		return "views/products/listProducts";
	}
	
}
