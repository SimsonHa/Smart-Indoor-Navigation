package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Grundriss;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.PiRepository;

@RestController
public class Verwaltungsfrontend {

	@Autowired
	ArtikelRepository repository1;
	PiRepository repository2;
	ESLRepository repository3;
	
	
	    @GetMapping("/artikel")
	    public List<Artikel> artikel() {
	       List<Artikel> a = (List<Artikel>) repository1.findAll();
	        return a;
	    }
	    
	    @GetMapping("/pi/{status}")
	    public List<Pi> findByStatus(@PathVariable boolean status){
	    	List<Pi> p = (List<Pi>) repository2.findByStatus(true);   	
	    	return p;
	    }
	    
	    @PutMapping("/piConnect")
	    public boolean connect(@RequestBody ESL esl, @RequestBody Pi pi) {
	    	esl.setPi(pi);
	    	repository3.save(esl);
	    	return true;
	    }
	    
	    @PostMapping("netz")
	    public boolean netz(@RequestBody Grundriss gr, @RequestBody Wegpunkt wp , @RequestBody ESL esl) {
	    	
	    	Grundriss newGr = new Grundriss();
	    	//BeanUtils.copyProperties(source, target);
	    	
	    	return true;
	    	
	    	
	    }
}
	    

