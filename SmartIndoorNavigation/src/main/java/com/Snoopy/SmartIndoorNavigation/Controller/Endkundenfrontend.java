package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Logic.TravelingSalesman;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperArtikel;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperArtikelNummer;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.GrundrissRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;

@RestController
public class Endkundenfrontend {
	
	@Autowired
	ArtikelRepository repoArtikel;
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	GrundrissRepository repoGrundriss;
	@Autowired
	ESLRepository repoESL;
	
	@Autowired TravelingSalesman service;

	
	
	@CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/fastPath")
    public List<Netzkante> netzKante(@RequestBody WrapperArtikelNummer artikelNr) {
		List <Artikel> aL = new ArrayList();
		for(int i = 0; i < artikelNr.getArtNr().size();i++) {
			Artikel a = repoArtikel.findByArtNr(artikelNr.getArtNr().get(i));
			aL.add(a);
		}
    	return service.path(aL);
   
    }
    

	@CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("/artikelPos/{artikelNr}")
    public ESL artikelPos(@PathVariable String artikelNr) {
		
	
		Artikel a = repoArtikel.findByArtNr(artikelNr);

		try {
			return repoESL.findByArtikel(a);
		}
		catch(Exception e) {
			System.out.println("Der Artikel hat noch keine Position!");
		}
    	return null;
    }
    
    //Bewertung des Artikels über Pfadvariable
    //@PostMapping("/bewertung/{artikelID}/{bewertung}")
    


}
