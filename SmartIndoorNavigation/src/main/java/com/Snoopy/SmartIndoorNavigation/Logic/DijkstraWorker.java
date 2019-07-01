package com.Snoopy.SmartIndoorNavigation.Logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzpunktRepository;

@Service
public class DijkstraWorker {
	
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;
	
	
	List<Netzkante> allKanten;
	List<Netzpunkt> allPunkte;
	
	

	
	public Dijkstra work (Netzpunkt start, Netzpunkt search) {
		allKanten = (List<Netzkante>) repoNetzkante.findAll();
		allPunkte = (List<Netzpunkt>) repoNetzpunkt.findAll();
		
		List<Dijkstra> reach = new ArrayList();
		List<Dijkstra> output = new ArrayList();
		
		for(int i = 0; i<allPunkte.size();i++) {
			//In der ersten Runde den Startpunkt markieren
			if(i==0) {
				Dijkstra curNode = new Dijkstra(start, null, 0);
				curNode.setStatus(false);
				//Verknüpfte Wege finden
				List<Netzkante> netzKConnected = repoNetzkante.findConnected(curNode.getNetzpunkt());
				for(int j = 0; j<netzKConnected.size();j++) {
					if(netzKConnected.get(j).getNetzPunkte().get(0).equals(curNode.getNetzpunkt())) {
						reach.add(new Dijkstra(netzKConnected.get(j).getNetzPunkte().get(1), (List)netzKConnected.get(j) ,netzKConnected.get(j).distance()));
					}
					else {
						reach.add(new Dijkstra(netzKConnected.get(j).getNetzPunkte().get(0), (List)netzKConnected.get(j) ,netzKConnected.get(j).distance()));
					}
				}
			}
			//In allen anderen Runden den Punkt markieren mit der kürzesten Strecke
			else {
				double distance = 999999999;
				int index =0;
				for(int j = 0; j<reach.size();j++) {
					//Schon besuchte Punkte weglassen
					if(reach.get(j).getGewicht()<distance && reach.get(j).isStatus()){
						index = j;
					}
				}
				//Aktueller Punkt
				Dijkstra curNode = reach.get(index);
				curNode.setStatus(false);
				
				//Check ob wir am Ziel sind? Abbrechen der For-Schleife !
				if(curNode.getNetzpunkt().equals(search)) {
					//reach.get(reach.indexOf(curNode)).setFinale(true);
					return curNode;
				}
				
				//Alle Verbindungen
				List<Netzkante> netzKConnected = repoNetzkante.findConnected(curNode.getNetzpunkt());
				List<Netzpunkt> netzPConnected = new ArrayList();
				
				for(int j = 0; j<netzKConnected.size();j++) {
					if(netzKConnected.get(j).getNetzPunkte().get(0).equals(curNode.getNetzpunkt())) {
						netzPConnected.add(netzKConnected.get(j).getNetzPunkte().get(1));
					}
					else {
						netzPConnected.add(netzKConnected.get(j).getNetzPunkte().get(0));
					}
				}
				
				//Checken ob die Punkte schon in Reach sind?
				for(int k =0; k<netzPConnected.size(); k++) {
					boolean contains = true;
					for(int l =0; l < reach.size(); l++) {
						//Wenn der Punkt noch nicht in Reach ist, boolean auf false setzen
						if(reach.get(l).getNetzpunkt().equals(netzPConnected.get(k))) {
							contains = false;
							//Wenn der Punkt bereits existiert, muss erstmal gecheckt werden wie weit die Entfernung ist
							if(reach.get(l).getGewicht()>netzKConnected.get(k).distance()) {
								reach.get(l).setGewicht(netzKConnected.get(k).distance());
								reach.get(l).setNetzkanten(curNode.getNetzkanten());
							}
						}
					}
					//Wenn der Punkt neu ist, dann einfach zu reach hinzufügen
					if(contains) {
						reach.add(new Dijkstra(netzPConnected.get(k), curNode.getNetzkanten(), netzKConnected.get(k).distance()));
					}	
				}

			}
			
		}
		return null;
	}

}
