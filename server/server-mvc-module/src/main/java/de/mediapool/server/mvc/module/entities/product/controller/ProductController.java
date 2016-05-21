package de.mediapool.server.mvc.module.entities.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.product.repository.ProductRepository;

@Controller
public class ProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
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
		model.addAttribute("prod", productRepository.findOne(productId));
		model.addAttribute("ownerList", productRepository.findOwner(productId));
		
		return "views/products/fragmentProduct :: productDetail";
	}

    @RequestMapping(value="/editProduct", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("product", new Product());
        return "views/products/editProduct";
    }
	
	@RequestMapping(value="/editProduct", method=RequestMethod.POST)
	public String getProduct(@ModelAttribute Product product, Model model) {
		logger.debug("Invoking: getProduct(product, model)");
		model.addAttribute("product", productRepository.save(product));
		
		return "views/products/editProduct";
	}
	
	
}
