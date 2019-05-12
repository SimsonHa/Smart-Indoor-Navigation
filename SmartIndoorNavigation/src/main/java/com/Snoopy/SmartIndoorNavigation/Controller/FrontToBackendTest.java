package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Product;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ProductRepository;

@Controller
public class FrontToBackendTest {

	@Autowired
	ProductRepository repository;
	
	    @GetMapping("/test")
	    public String test(Model model) {
	       List<Product> p = (List<Product>) repository.findAll();
	        	model.addAttribute("products", p);
	        return "test.html";
	    }
	    @GetMapping("/index")
	    public String index() {
	        return "index";
	    }
	    @GetMapping("/canvas")
	    public String canvas() {
	        return "canvas";
	    }
}
