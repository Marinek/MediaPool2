package de.mediapool.server.mvc.module.entities.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.lists.domain.ProductList;
import de.mediapool.server.entities.lists.repository.ProductListRepository;
import de.mediapool.server.security.domain.PreAuthorization;

@Controller
public class ProductListController implements MPController {

	@Autowired
	private ProductListRepository listRepository;
	
	@RequestMapping("/productLists")
	public String getAllLists(Model model) {
		Result<ProductList> listProducts = listRepository.findAll();
		
		model.addAttribute("productLists", listProducts);
		
		return "views/lists/listProductLists";
	}

    @RequestMapping(value="/editProductList", method=RequestMethod.GET)
    public String editProductList(@RequestParam(name="id", required=false)Long productListId, Model model) {
    	if(productListId != null) {
    		model.addAttribute("list", listRepository.findOne(productListId));
    	} else {
    		model.addAttribute("list", new ProductList());
    	}
    	
        return "views/lists/editProductList";
    }
	
	@RequestMapping(value="/editProductList", method=RequestMethod.POST )
	public String getProductList(@ModelAttribute ProductList list, Model model) {
		model.addAttribute("list", listRepository.save(list));
		
		return "redirect:/productLists";
	}
	
	@RequestMapping(value="/removeProductList", method=RequestMethod.GET)
	@PreAuthorize(PreAuthorization.ROLE_USER)
	public String removeProductList(@RequestParam(name="id", required=true)Long productListId, Model model) {
		listRepository.delete(productListId);
		
		return "redirect:/productLists";
	}
}
