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
public class TravelingSalesman {
	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;

	
	List<Artikel> artikel;
	
	public TravelingSalesman() {
		
	}
	
	public TravelingSalesman(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
	public List<Netzkante> path1(){
		//Dijkstra
		List<Netzkante> allKanten = (List<Netzkante>) repoNetzkante.findAll();
		List<Netzpunkt> allPunkte = (List<Netzpunkt>) repoNetzpunkt.findAll();
		
		Netzpunkt search = repoNetzpunkt.findByArtikel(artikel.get(0));
		
		
		Netzpunkt start = repoNetzpunkt.findByStatus("anfang");
		
		for(int k=0; k<allPunkte.size();k++) {
			List<Netzpunkt> reach = new ArrayList();
			for(int i =0; i<allKanten.size(); i++) {
				if(allKanten.get(i).getNetzPunkte().get(0).equals(start)) {
					
					reach.add(allKanten.get(i).getNetzPunkte().get(1));
				}
				else if(allKanten.get(i).getNetzPunkte().get(1).equals(start)){
					reach.add(allKanten.get(i).getNetzPunkte().get(0));
				}
				
			}
			
			for(int m=0; m<reach.size(); m++) {
				System.out.println(reach.get(m).getPosX());
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
