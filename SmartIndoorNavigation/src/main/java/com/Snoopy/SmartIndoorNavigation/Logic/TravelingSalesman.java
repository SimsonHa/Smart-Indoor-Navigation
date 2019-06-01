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

	
	List<Artikel> artikel;
	
	public TravelingSalesman() {
		
	}
	
	public TravelingSalesman(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
	public List<Netzkante> path1(){
		//Dijkstra
		List<Netzkante> allKanten = (List<Netzkante>) repoNetzkante.findAll();
		
		List<Netzkante> bestWay;
		
		Netzpunkt startPunkt;
		
		int allSize = allKanten.size();
		
		
		//Startpunkt ermitteln
		for(int i =0; i<allSize; i++) {
			if(allKanten.get(i).getNetzPunkte().get(0).getStart()) {
				startPunkt = allKanten.get(i).getNetzPunkte().get(0);
				break;
				
			}
			else if (allKanten.get(i).getNetzPunkte().get(1).getStart()) {
				startPunkt = allKanten.get(i).getNetzPunkte().get(1);
				break;
			}
		}
	
		
		
		
		return null;
		
	}
	
	public List<Netzkante> path2(){
		
		return null;
	}
	
	

	public List<Artikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
	
}
