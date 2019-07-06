package com.Snoopy.SmartIndoorNavigation.Logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzpunktRepository;

@Service
public class DijkstraWorker {
	
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;
	@Autowired
	ESLRepository repoESL;
	
	
	List<Netzkante> allKanten;
	List<Netzpunkt> allPunkte;
	

	
	
	public Dijkstra work (Netzpunkt start, Netzpunkt search) {
		allKanten = (List<Netzkante>) repoNetzkante.findAll();
		allPunkte = (List<Netzpunkt>) repoNetzpunkt.findAll();
		
		// Erreichbare Knoten
		List<Dijkstra> reach = new ArrayList();
		//List<Dijkstra> output = new ArrayList();
		

		
		//Schleife um alle Punkte zu besuchen
		while(true) {

			//In der ersten Runde den Startpunkt markieren
			if(reach.isEmpty()) {
				//Aktuell betrachteter Knoten
				Dijkstra curNode = new Dijkstra(start, null, 0);
				//Der aktuelle Knoten soll später nicht mehr betrachtet werden
				curNode.setStatus(false);
				reach.add(curNode);
				//Verknüpfte Wege finden
				List<Netzkante> netzKConnected = this.findConnected(curNode.getNetzpunkt());
				
				for(int j = 0; j<netzKConnected.size();j++) {
					List<Netzkante> netzKConnectedAdd = new ArrayList();
					netzKConnectedAdd.add(netzKConnected.get(j));
					//Jeden verbundenen Knoten zu reach hinzufügen
					if(netzKConnected.get(j).getNetzPunkte().get(0).equals(curNode.getNetzpunkt())) {
						reach.add(new Dijkstra(netzKConnected.get(j).getNetzPunkte().get(1), netzKConnectedAdd ,netzKConnected.get(j).distance()));
					}
					else {
						reach.add(new Dijkstra(netzKConnected.get(j).getNetzPunkte().get(0), netzKConnectedAdd ,netzKConnected.get(j).distance()));
					}
//					System.out.println("Aktueller Knoten: (" + curNode.getNetzpunkt().getPosX() + "/" + curNode.getNetzpunkt().getPosY() +")");
//					System.out.println("ReachSize: " +reach.size());
					
				}
				
			}
			//In allen anderen Runden den Punkt markieren mit der kürzesten Strecke
			else {
				double distance = 999999999;
				int index =0;
				//Index des nähsten Punkt herausfinden
				for(int j = 0; j<reach.size();j++) {
					//Schon besuchte Punkte weglassen
					if(reach.get(j).getGewicht()<distance && reach.get(j).isStatus()){
						distance = reach.get(j).getGewicht();
						index = j;
					}
				}
				//Aktueller Punkt
				Dijkstra curNode = reach.get(index);
				//Testing

				//Der aktuelle Knoten soll später nicht mehr betrachtet werden
				//reach.get(index).setStatus(false);
				
				//Check ob wir am Ziel sind? Abbrechen der While-Schleife !
				if(curNode.getNetzpunkt().equals(search)) {
					//reach.get(reach.indexOf(curNode)).setFinale(true);
					return curNode;
				}
				
				//Alle Verbindungen
				List<Netzkante> netzKConnected = this.findConnected(curNode.getNetzpunkt());
				List<Netzpunkt> netzPConnected = new ArrayList();
				//Verbundene Punkte zur Liste hinzufügen
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
							if(reach.get(l).getGewicht()>curNode.getGewicht()+netzKConnected.get(k).distance()) {
								//Zwischenliste um Kanten zusammenzuführen
								List<Netzkante> addNK = new ArrayList();
								addNK.addAll(curNode.getNetzkanten());
								addNK.add(netzKConnected.get(k));
								reach.get(l).setGewicht(netzKConnected.get(k).distance());
								reach.get(l).setNetzkanten(addNK);
							}
						}
					}
					//Wenn der Punkt neu ist, dann einfach zu reach hinzufügen
					if(contains) {
						//Zwischenliste um Kanten zusammenzuführen
						List<Netzkante> addNK = new ArrayList();
						addNK.addAll(curNode.getNetzkanten());
						addNK.add(netzKConnected.get(k));
						reach.add(new Dijkstra(netzPConnected.get(k), addNK, netzKConnected.get(k).distance()));
					}
				}
				
				boolean del1 = true;
				for(int k =0; k<netzPConnected.size();k++) {
					boolean del2 = false;
					for(int t =0; t<reach.size(); t++) {
						if(reach.get(t).getNetzpunkt().equals(netzPConnected.get(k))) {
							del2 = true;
						}
					}
					if(!del2) {
						del1 = false;
					}
				}
				
				if(del1) {
					//Der aktuelle Knoten soll später nicht mehr betrachtet werden
					reach.get(index).setStatus(false);
				}

				
				
				
//					System.out.println("Aktueller Knoten: (" + curNode.getNetzpunkt().getPosX() + "/" + curNode.getNetzpunkt().getPosY() +")" );
//					System.out.println("ReachSize: " +reach.size());
//					for(int b =0; b<reach.size(); b++) {
//						try {
//							System.out.println("Reach" + b +": " + reach.get(b).getNetzpunkt().getPosX() + "/" + reach.get(b).getNetzpunkt().getPosY() + ", Gewicht: " + reach.get(b).getGewicht() + ", Status: " + reach.get(b).isStatus()+", Artikel: " +reach.get(b).getNetzpunkt().getArtikel().getName());
//						}
//						catch(Exception e) {
//							
//						}
//							System.out.println("Reach" + b +": " + reach.get(b).getNetzpunkt().getPosX() + "/" + reach.get(b).getNetzpunkt().getPosY() + ", Gewicht: " + reach.get(b).getGewicht() + ", Status: " + reach.get(b).isStatus());
//					}



			}
		}
		
	
		
	
	}
	
	public List<Netzkante> findConnected (Netzpunkt np){
		List<Netzkante> all = (List<Netzkante>) repoNetzkante.findAll();
		List<Netzkante> output = new ArrayList();
		for(int i = 0; i <all.size();i++) {
			if(all.get(i).getNetzPunkte().get(0).equals(np)  || all.get(i).getNetzPunkte().get(1).equals(np)) {
				output.add(all.get(i));
			}
		}
		return output;
	}

}
