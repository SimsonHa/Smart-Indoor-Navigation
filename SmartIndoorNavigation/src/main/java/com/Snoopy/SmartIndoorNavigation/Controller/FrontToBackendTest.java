package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Product;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ProductRepository;

@RestController
public class FrontToBackendTest {

	@Autowired
	ProductRepository repository;
	
	    @GetMapping("/product")
	    public List<Product> product() {
	       List<Product> p = (List<Product>) repository.findAll();
	        return p;
	    }
}
