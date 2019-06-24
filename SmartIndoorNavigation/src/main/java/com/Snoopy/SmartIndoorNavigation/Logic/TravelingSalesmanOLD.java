package com.Snoopy.SmartIndoorNavigation.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzpunktRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;

@Service
public class TravelingSalesmanOLD {
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;

	
	List<Artikel> artikel;
	
	public TravelingSalesmanOLD() {
		
	}
	
	public TravelingSalesmanOLD(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
	public List<Netzkante> path1(){
		//Dijkstra
		List<Netzkante> allKanten = (List<Netzkante>) repoNetzkante.findAll();
		List<Netzpunkt> allPunkte = (List<Netzpunkt>) repoNetzpunkt.findAll();
		
		//Es wird nur ein Artikel gesucht
		Netzpunkt search = repoNetzpunkt.findByArtikel(artikel.get(0));
		
		
		Netzpunkt start = repoNetzpunkt.findByStatus("anfang");
		List<Dijkstra> reach = new ArrayList();
		reach.add(new Dijkstra(start, null, 0));
		
		boolean flag = true;
		
		while(flag) {
			if(reach.size()==1) {
				//Verknüpfte Wege finden
				List<Netzkante> netzKConnected = repoNetzkante.findConnected(reach.get(0).getNetzpunkt());
				
				for(int i = 0; i<netzKConnected.size(); i++) {
					List<Netzkante> path = new ArrayList();
					path.add(netzKConnected.get(i));
					
					//Es soll nicht der Punkt hinzugefügt werden, der schon gefunden wurde
					if(netzKConnected.get(i).getNetzPunkte().get(0).equals(start)) {
						reach.add(new Dijkstra(netzKConnected.get(i).getNetzPunkte().get(1), path , netzKConnected.get(i).distance()));
					}
					else {
						reach.add(new Dijkstra(netzKConnected.get(i).getNetzPunkte().get(0), path , netzKConnected.get(i).distance()));
					}
					
				}
			}
			else {
				
				
				
				double gewicht = 999999999;
				int index = 0;
				//Nähsten Punkt herausfinden
				for(int i = 0; i<reach.size(); i++) {
					if(reach.get(i).getGewicht()<gewicht && reach.get(i).getGewicht()!=0) {
						index = i;
					}
				}
				
				List<Netzkante> netzKConnected = repoNetzkante.findConnected(reach.get(index).getNetzpunkt());
				
				
				
				
				List<Netzpunkt> p = new ArrayList();
				for(int i = 0; i<netzKConnected.size(); i++) {
					if(netzKConnected.get(i).getNetzPunkte().get(0).equals(reach.get(index).getNetzpunkt())) {
						p.add(netzKConnected.get(i).getNetzPunkte().get(1));
					}
					else {
						p.add(netzKConnected.get(i).getNetzPunkte().get(2));
					}
				}
				
				

				
				
				//Wenn Artikel gefunden wurde, while Schleife abbrechen
				if(reach.get(index).getNetzpunkt().equals(search)) {
					//Richtigen Pfad markieren
					reach.get(index).setStatus(true);
					flag = false;
				}
				

				

				
				
				
				for(int i = 0; i<p.size();i++) {
					boolean contains = true;
					for(int j = 0; j<reach.size();j++) {
						if(p.get(i).equals(reach.get(j).getNetzpunkt())){
							contains = false;
							if((netzKConnected.get(i).distance()+reach.get(index).getGewicht())<reach.get(j).getGewicht()) {
								reach.get(j).setGewicht(netzKConnected.get(i).distance()+reach.get(index).getGewicht());
								List<Netzkante> nK = reach.get(index).getNetzkanten();
								nK.add(netzKConnected.get(i));
								reach.get(j).setNetzkanten(nK);
							}
						}
						
					}
					if(contains) {
						List<Netzkante> nK = reach.get(index).getNetzkanten();
						nK.add(netzKConnected.get(i));
						reach.add(new Dijkstra(p.get(i),nK, reach.get(index).getGewicht()+netzKConnected.get(i).distance()));
					}
				}
				
				
				
				
			}
		}
			

		
		
		
		List<Netzkante> bestWay;
				
		int allSize = allKanten.size();
		
		

		
		
		
		return null;
		
	}
	
	public List<Netzkante> path2(){
		
		return null;
	}
	
	public double distance (Netzpunkt np1, Netzpunkt np2) {
		double x = Math.abs(np1.getPosX() - np2.getPosX());
		double y = Math.abs(np1.getPosY() - np2.getPosY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public List<Artikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
	
}
