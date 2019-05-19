package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Grundriss;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.GrundrissRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.PiRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.WegpunktRepository;

import Logic.Wrapper;

@RestController
public class Verwaltungsfrontend {

	@Autowired
	ArtikelRepository repository1;
	@Autowired
	PiRepository repository2;
	@Autowired
	ESLRepository repository3;
	@Autowired
	WegpunktRepository repository4;
	@Autowired
	GrundrissRepository repository5;
	@Autowired
	KanteRepository repository6;
	
	
	
	    @GetMapping("/artikel")
	    public List<Artikel> artikel() {
	       List<Artikel> a = (List<Artikel>) repository1.findAll();
	        return a;
	    }
	    @GetMapping("/esl")
	    public List<ESL> esl(){
	    	List<ESL> esl = (List<ESL>) repository3.findAll();
	    	return esl;
	    }
	  
	    
	    @GetMapping("/pi/{status}")
	    public List<Pi> findByStatus(@PathVariable boolean status){
	    	List<Pi> p = (List<Pi>) repository2.findByStatus(status);   	
	    	return p;
	    }
	    
	    @PutMapping("/piConnect/{posX}/{posY}/{macAdress}")
	    public ESL connect(@PathVariable double posX, @PathVariable double posY, @PathVariable String macAdress) {
	    	ESL esl = repository3.findByPosition(posX, posY);
	    	Pi pi = repository2.findByMacAdress(macAdress);
	    	esl.setPi(pi);
	    	repository3.save(esl);
	    	return esl;
	    }
	    
	    //https://www.baeldung.com/spring-request-response-body
	    //https://stackoverflow.com/questions/30511911/getting-not-supported-media-type-error
	    @PostMapping("wegpunkt")
	    public Wegpunkt wp(@RequestBody Wegpunkt wp) {
	    	
	    	repository4.save(wp);
	    	return wp;
	    }
	    @GetMapping("wegpunktAll")
	    public List<Wegpunkt> wpAll(){
	    List<Wegpunkt> wp = (List<Wegpunkt>) repository4.findAll();
	    	return wp;
		}
    	
	    
	    @PostMapping("netz")
	    public Grundriss netz(@RequestBody Wrapper wrapper) {
	    	repository5.save(wrapper.getGrundriss());
	    	
	    	int sizeESL = wrapper.getEsls().size();
	    	for(int j = 0; j<sizeESL; j++) {
	    		ESL e = new ESL(wrapper.getEsls().get(j).getPosX(), wrapper.getEsls().get(j).getPosY(), wrapper.getGrundriss());
	    		repository3.save(e);
	    	}
	    	
	    	int sizeKT = wrapper.getKanten().size();
	    	for(int i = 0; i<sizeKT; i++) {
	    		/*Wegpunkt wp1= wrapper.getKanten().get(i).getWegpunkte().get(0);
	    		Wegpunkt wp2 = wrapper.getKanten().get(i).getWegpunkte().get(1);
	    		List<Wegpunkt> wpList = new ArrayList<Wegpunkt>();
	    		wpList.add(wp1);
	    		wpList.add(wp2);
	    		repository4.save(wp1);
	    		repository4.save(wp2);
	    		*/
	    		repository6.save(wrapper.getKanten().get(i));
	    	}
	    	
	    	
	    	return wrapper.getGrundriss();
	    	
	    	
	    }
}
	    

