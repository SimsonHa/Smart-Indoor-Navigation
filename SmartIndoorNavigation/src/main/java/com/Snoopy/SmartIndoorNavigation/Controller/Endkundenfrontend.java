package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Logic.TravelingSalesman;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperArtikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;

@RestController
public class Endkundenfrontend {
	
	@Autowired
	ArtikelRepository repoArtikel;
	@Autowired
	NetzkanteRepository repoNetzkante;
	
	@Autowired TravelingSalesman service;

	
    @PostMapping("/fastPath")
    public List<Netzkante> netzKante(@RequestBody WrapperArtikel artikel) {
    	service.setArtikel(artikel.getArtikel());
    	return service.path();
   
    }
    @GetMapping("/artikel")
    public List<Artikel> artikel() {
       List<Artikel> a = (List<Artikel>) repoArtikel.findAll();
        return a;
    }
    
    //Bewertung des Artikels über Pfadvariable
    //@PostMapping("/bewertung/{artikelID}/{bewertung}")
    
    
    //Punkt des Artikels auf Bild anzeigen!

}
