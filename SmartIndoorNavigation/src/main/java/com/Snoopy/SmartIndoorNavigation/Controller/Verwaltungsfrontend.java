package com.Snoopy.SmartIndoorNavigation.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Snoopy.SmartIndoorNavigation.Logic.Netz;
import com.Snoopy.SmartIndoorNavigation.Logic.Wrapper;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperESL;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperNetz;
import com.Snoopy.SmartIndoorNavigation.Logic.WrapperNetzArr;
import com.Snoopy.SmartIndoorNavigation.MQTT.PublishArtikel;
import com.Snoopy.SmartIndoorNavigation.MQTT.SubscribePi;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Grundriss;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.GrundrissRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KategorieRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.PiRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.WegpunktRepository;

//@ComponentScan({"com.Snoopy.SmartIndoorNavigation.Logic"})

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
	@Autowired
	NetzkanteRepository repository7;
	@Autowired
	KategorieRepository repository8;
	
	@Autowired
	Netz netzService;
	
	@Autowired
	PublishArtikel publishService;
		
		//Alle Artikel zurückgeben
		@CrossOrigin(origins =  {"http://localhost:4200", "http://localhost:8082"})
		@GetMapping("/artikel")
	    public List<Artikel> artikel() {
	       List<Artikel> a = (List<Artikel>) repository1.findAll();
	        return a;
	    }
		
		//Alle ESL's zurückgeben
		@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8082"})
	    @GetMapping("/esl")
	    public List<ESL> esl(){
	    	List<ESL> esl = (List<ESL>) repository3.findAll();
	    	return esl;
	    }
		
		//Alle aktiven Pi's zurückgeben
		@CrossOrigin(origins = "http://localhost:4200")
	    @GetMapping("/pi")
	    public List<Pi> findByStatus(){
	    	List<Pi> p = (List<Pi>) repository2.findByStatus(true);   	
	    	return p;
	    }
		
		
		//ESL, Artikel und Pi verheiraten
		@CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/piConnect")
	    public void ESLconnect(@RequestBody WrapperESL wrapperESL) {
			ESL esl = new ESL(wrapperESL.getLabel().getX(), wrapperESL.getLabel().getY());
			
			esl.setArtikel(repository1.findByName(wrapperESL.getArtikel().getName()));
			esl.setPi(repository2.findByMacAdress(wrapperESL.getLabel().getMac()));
			
			repository3.save(esl);
			
			
			
			//Neues Netz mit ESL's erzeugen{
		    netzService.setEsls(repository3.findAll());
		    netzService.setKanten(repository6.findAll());
		    netzService.netzUpdate();


			//Geänderte Daten an Pi übermitteln
			List<ESL> eslList = new ArrayList();
			eslList.add(esl);
			publishService.publish(eslList);
	    }
		//Neuen Artikel anlegen
		@CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/newArtikel")
		public void NewArtikel(@RequestBody Artikel artikel) {
			
			try {
				repository8.findByName(artikel.getKategorie().getName()).getName();
				artikel.setKategorie(repository8.findByName(artikel.getKategorie().getName()));
			}
			catch(NullPointerException e) {
				repository8.save(artikel.getKategorie());
			}

			
			repository1.save(artikel);
			System.out.println("Artikel " + artikel.getName()+ " wurde angelegt!");
			//Geänderte Daten an Pi übermitteln
			try {
				List <ESL> eslList = new ArrayList();
				ESL esl = repository3.findByArtikel(artikel);
				eslList.add(esl);
				publishService.publish(eslList);
			}
			catch(Error e){
				System.out.println("Der Artikel wurde noch keinem ESL zugeordnet! Keine Nachricht an den Pi veröffentlicht!");
			}
		}
				
	   

    	
		//Kanten speichern 
		@CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/netz")
	    public void netz(@RequestBody WrapperNetzArr wrapper) {
			//Bisheriges Netz löschen

	    	repository6.deleteAll();
	    	repository4.deleteAll();
	    	int sizeWP = wrapper.getWrapperNetzArr().size();
	    	
	    	for(int i =0; i<sizeWP; i++) {
	    		Wegpunkt wp = new Wegpunkt(wrapper.getWrapperNetzArr().get(i).getId(), wrapper.getWrapperNetzArr().get(i).getX(), wrapper.getWrapperNetzArr().get(i).getY(), wrapper.getWrapperNetzArr().get(i).getStatus());
	    		repository4.save(wp);
	    	
	    	}
			for(int i =0; i<sizeWP; i++) {
				int sizeConn = wrapper.getWrapperNetzArr().get(i).getConnectedTo().length;
				for(int j =0; j<sizeConn; j++) {
					List<Wegpunkt> wpList1 = new ArrayList<Wegpunkt>();
					List<Wegpunkt> wpList2 = new ArrayList<Wegpunkt>();
					
					Optional<Wegpunkt> wp1 = repository4.findById(wrapper.getWrapperNetzArr().get(i).getId());
					long id = wrapper.getWrapperNetzArr().get(i).getConnectedTo()[j];
					Optional<Wegpunkt> wp2 = repository4.findById(id);
					
					wpList1.add(wp1.get());
					wpList1.add(wp2.get());
					
					wpList2.add(wp2.get());
					wpList2.add(wp1.get());
					
					boolean flag = true;
					int sizeKante = (int) repository6.count();
					List<Kante> kanten = (List<Kante>) repository6.findAll();
					//Check ob es die Kante schon gibt
					for(int k =0; k<sizeKante; k++) {
						if(kanten.get(k).getWegpunkte().get(0).getId() == wpList1.get(0).getId() && kanten.get(k).getWegpunkte().get(1).getId() == wpList1.get(1).getId()) {
							flag = false;
						}
						else if (kanten.get(k).getWegpunkte().get(0).getId() == wpList2.get(0).getId() && kanten.get(k).getWegpunkte().get(1).getId() == wpList2.get(1).getId()) {
							flag = false;
						}
					}
					if(flag) {
						Kante k = new Kante(wpList1);
						repository6.save(k);
					
					}
					
						

				}
			}
			
		//Neues Netz mit ESL's erzeugen wenn es schon ESL's gibt
	    if(repository6.count()>0) {
	    	netzService.setEsls(repository3.findAll());
	    	netzService.setKanten(repository6.findAll());
	    	
	    	netzService.netzUpdate();
	    }

	    }
		
		
		//Get Kanten
		@CrossOrigin(origins = "http://localhost:4200")
	    @GetMapping("/getNetz")
	    public List<WrapperNetz> getNetz() {
			List <Wegpunkt> wpS = (List<Wegpunkt>) repository4.findAll();
			List <Kante> kS = (List<Kante>) repository6.findAll();
			
			List<WrapperNetz> wrapperN = new ArrayList();
			for(int i = 0; i < wpS.size(); i++) {
				List<Integer> connectedAr = new ArrayList();
				for(int j = 0; j < kS.size(); j++){
					if(wpS.get(i).getId() == kS.get(j).getWegpunkte().get(0).getId()) {
						if(!connectedAr.contains(kS.get(j).getWegpunkte().get(1).getId())){
								connectedAr.add((int)(long)kS.get(j).getWegpunkte().get(1).getId());
						}
					}
					
					else if(wpS.get(i).getId() == kS.get(j).getWegpunkte().get(1).getId()) {
						if(!connectedAr.contains(kS.get(j).getWegpunkte().get(0).getId())){
							connectedAr.add((int)(long)kS.get(j).getWegpunkte().get(0).getId());
					}
					}
				}
				int[] connected = new int[connectedAr.size()];
				for(int k =0; k<connectedAr.size();k++) {
					connected[k] = (int) connectedAr.get(k);
				}
				
				
				WrapperNetz wN = new WrapperNetz(wpS.get(i).getId(), wpS.get(i).getPosX(), wpS.get(i).getPosY(), wpS.get(i).getStatus(),  connected);
				wrapperN.add(wN);
			}
			
			return wrapperN;
		}
}
	    

