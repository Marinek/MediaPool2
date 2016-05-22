package de.mediapool.server.mvc.module.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.mediapool.server.entities.product.repository.ProductRepository;

@Controller
public class IndexController {

	@Autowired
	private ProductRepository productRepo;
	
    @RequestMapping("/")
    public String index(Model model) {
    	
    	Map<String, Object> fillIndexModel = fillIndexModel(null);
    	
    	model.addAllAttributes(fillIndexModel);
    	
        return "views/index";
    }

    public Map<String, Object> fillIndexModel(Map<String, Object> model) {
    	if(model == null) {
    		model = new HashMap<>();
    	}
    	
    	model.put("productCount", productRepo.count());
    	
    	return model;
    }

}