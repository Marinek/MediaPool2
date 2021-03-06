package de.mediapool.server.mvc.module.entities.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.product.repository.ProductRepository;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.security.domain.PreAuthorization;

@Controller
public class ProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/products")
	public String getAllProducts(Model model) {
		List<Product> listProducts = productRepository.findAllOrderByTitle();
		
		model.addAttribute("products", listProducts);
		
		return "views/products/listProducts";
	}

	@RequestMapping("/product")
	public String getProduct(@RequestParam(value = "id") Long productId, Model model) {
		List<User> productOwner = productRepository.findOwner(productId);
		
		
		model.addAttribute("prod", productRepository.findOneById(productId));
		model.addAttribute("ownerList", productOwner);
		model.addAttribute("hasOwner",productOwner.iterator().hasNext() );
		
		return "views/products/fragmentProduct :: productDetail";
	}

    @RequestMapping(value="/editProduct", method=RequestMethod.GET)
    public String greetingForm(@RequestParam(name="id", required=false)Long productId, Model model) {
    	if(productId != null) {
    		model.addAttribute("product", productRepository.findOne(productId));
    	} else {
    		model.addAttribute("product", new Product());
    	}
    	
        return "views/products/editProduct";
    }
	
	@RequestMapping(value="/editProduct", method=RequestMethod.POST )
	public String getProduct(@ModelAttribute Product product, Model model) {
		logger.debug("Invoking: getProduct(product, model)");
		
		model.addAttribute("product", productRepository.save(product));
		
		return "redirect:/products";
	}
	
	@RequestMapping(value="/removeProduct", method=RequestMethod.GET)
	@PreAuthorize(PreAuthorization.ROLE_USER)
	public String removeProduct(@RequestParam(name="id", required=true)Long productId, Model model) {
		productRepository.delete(productId);
		
		return "redirect:/products";
	}
	
}
