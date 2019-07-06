package com.Snoopy.SmartIndoorNavigation.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzpunktRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;

@Service
public class TravelingSalesman {
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;

	
	@Autowired
	DijkstraWorker worker;
	

	public TravelingSalesman() {
	
	}
	
	
	//Greedy
	public List<Netzkante> path(List<Artikel> artikel){
		//Dijkstra
		List<Dijkstra> output = new ArrayList();
		
		List<Artikel> artikelTemp = artikel;
		System.out.println("Anzahl der gesuchten Artikel: " + artikel.size());
		
		int artSize = artikel.size();
		
		//Schleife so oft durchlaufen wie viele Artikel gesucht werden
		for(int x = 0; x<artSize; x++) {
			
			Dijkstra shortest = new Dijkstra(null, null, 999999999);
			
			
			for(int v =0; v<artikelTemp.size();v++) {
				System.out.println("ArtikelTemp: "+artikelTemp.get(v));
			}
			
			//Schleife mit Artikel die noch nicht besucht wurden
			for(int m = 0; m<artikelTemp.size(); m++) {
				//In der ersten Runde vom Anfang starten
				if(x==0) {
					//Wenn Strecke kürzer, dann shortest neu vergeben
					if(worker.work(repoNetzpunkt.findByStatus("anfang"), repoNetzpunkt.findByArtikel(artikelTemp.get(m))).getGewicht()<shortest.getGewicht()) {
						shortest = worker.work(repoNetzpunkt.findByStatus("anfang"), repoNetzpunkt.findByArtikel(artikelTemp.get(m)));
					}
					
				}
				else {
					if(worker.work(output.get(output.size()-1).getNetzpunkt(), repoNetzpunkt.findByArtikel(artikelTemp.get(m))).getGewicht()<shortest.getGewicht()){
						shortest = worker.work(output.get(output.size()-1).getNetzpunkt(), repoNetzpunkt.findByArtikel(artikelTemp.get(m)));
					}
				}
			}
			System.out.println("Artikel " + shortest.getNetzpunkt().getArtikel().getName() + " gefunden.");
			output.add(shortest);
			artikelTemp.remove(shortest.getNetzpunkt().getArtikel());
			
			
			System.out.println("OutputSize: " + output.size());
			System.out.println("ArtikelSize: " + artSize);

		}
		//Vom letzten Artikel zur Kasse navigieren
		Dijkstra shortest = worker.work(output.get(output.size()-1).getNetzpunkt(), repoNetzpunkt.findByStatus("ende"));
		output.add(shortest);
		
		List<Netzkante> output2 = new ArrayList();
		
		for(int z = 0; z< output.size(); z++) {
			output2.addAll(output.get(z).getNetzkanten());
		}
		
		return output2;

		
	}
	

	/*
	public double distance (Netzpunkt np1, Netzpunkt np2) {
		double x = Math.abs(np1.getPosX() - np2.getPosX());
		double y = Math.abs(np1.getPosY() - np2.getPosY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	*/

	
	
	
	
}
