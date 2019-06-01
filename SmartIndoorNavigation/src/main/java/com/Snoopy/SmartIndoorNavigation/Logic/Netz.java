package com.Snoopy.SmartIndoorNavigation.Logic;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzkanteRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.NetzpunktRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;

@Service
public class Netz {
	


	@Autowired
	NetzkanteRepository repoNetzkante;
	@Autowired
	NetzpunktRepository repoNetzpunkt;
	
	List<Kante> kanten;
	List<ESL> esls;
	
	
	public Netz() {
		
	}
	public Netz(List<Kante> kanten, List<ESL> esls) {
		this.kanten = kanten;
		this.esls = esls;
	}
	
	public void netzUpdate() {

		
		
		int kSize = kanten.size();
		
		//Aus allen Kanten neue Netzkanten erzeugen
		List<Netzkante> nKList = new ArrayList();
		for (int i =0; i<kSize; i++) {
			double nP1X = kanten.get(i).getWegpunkte().get(0).getPosX();
			double nP1Y = kanten.get(i).getWegpunkte().get(0).getPosY();
			boolean start1 = kanten.get(i).getWegpunkte().get(0).getStart();
			
			Netzpunkt nP1 = new Netzpunkt(nP1X, nP1Y, start1);
			
			double nP2X = kanten.get(i).getWegpunkte().get(1).getPosX();
			double nP2Y = kanten.get(i).getWegpunkte().get(1).getPosY();
			boolean start2 = kanten.get(i).getWegpunkte().get(0).getStart();
			
			Netzpunkt nP2 = new Netzpunkt(nP2X, nP2Y, start2);
			List<Netzpunkt> nPList = new ArrayList();
			nPList.add(nP1);
			nPList.add(nP2);
			
			Netzkante nK = new Netzkante(nPList);	
			
			nKList.add(nK);			
		}
		
		int eslSize = esls.size();
		
		//List<Netzkante> nKListNew = new ArrayList();
		//List<Netzkante> nKListDelete = new ArrayList();
		
		double laenge;
		for (int j = 0; j<eslSize; j++) {
			//Sehr große Zahl 
			laenge = 999999999;
			//Nähste Kante
			Netzkante netzKante = new Netzkante();
			//Neuer Wegpunkt
			double xNew = 0;
			double yNew = 0;
			
			
			kSize = nKList.size();
			System.out.println("KSize: " + kSize);

			for (int k =0; k<kSize; k++) {
				//Kantenpunkte
				double aX = nKList.get(k).getNetzPunkte().get(0).getPosX();
				double aY = nKList.get(k).getNetzPunkte().get(0).getPosY();
				double bX = nKList.get(k).getNetzPunkte().get(1).getPosX();
				double bY = nKList.get(k).getNetzPunkte().get(1).getPosY();
				
				
				double m1 = (bY-aY)/(bX-aX);
				double r1 = aY-(m1*aX);
				
				System.out.println("M1: "+m1);
				System.out.println("R1: "+ r1);
				
				//Negativer Kehrwert
				double m2 = Math.pow(m1, -1) * -1;
				
				System.out.println("NegKehr: "+ m2);
				//ESL Punkt
				double pX = esls.get(j).getPosX();
				double pY = esls.get(j).getPosY();
				
				double r2 = pY-(m2*pX);
				System.out.println("R2: "+r2);
				
				//Schnittpunkt berechnen
				double x = (r2-r1)/(m1-m2);
				double y = m1*x+r1;
				System.out.println("X: " + x);
				System.out.println("Y: " + y);
				
				//Länge des Etiketts zur Kante berechnen
				double l = Math.sqrt(Math.pow((x-pX), 2)+Math.pow((y-pY), 2));
				

				//Wenn es der kürzere Weg ist, dann speichern
				if(l<laenge) {
					laenge = l;
					
					netzKante = nKList.get(k);
					System.out.println("Check: " + netzKante);
					xNew = x;
					yNew = y;
					
				}

			}
			
			Netzpunkt pNew = new Netzpunkt(xNew, yNew, false);
			pNew.setArtikel(esls.get(j).getArtikel());
			
			List<Netzpunkt> l1New = new ArrayList();
			l1New.add(netzKante.getNetzPunkte().get(0));
			l1New.add(pNew);
			Netzkante k1New = new Netzkante(l1New);
			
			List<Netzpunkt> l2New = new ArrayList();
			l2New.add(pNew);
			l2New.add(netzKante.getNetzPunkte().get(1));
			Netzkante k2New = new Netzkante(l2New);
			
			//ALte Kante entfernen
			int delIndex = nKList.indexOf(netzKante);
			nKList.remove(delIndex);
			
			//Neue Kanten hinzufügen
			nKList.add(k1New);
			nKList.add(k2New);
									
		}

		//Netz speichern
		
		System.out.println(nKList);
		kSize = nKList.size();
		
		for(int g = 0; g< kSize; g++) {
//			repoNetzpunkt.save(nKList.get(g).getNetzPunkte().get(0));
//			repoNetzpunkt.save(nKList.get(g).getNetzPunkte().get(1));
			
			System.out.println(nKList.get(g));
//			Netzkante netzKante = nKList.get(g);
//			netzKante.setId((long) g);
			
			repoNetzkante.save(nKList.get(g));
		}
		
	}
	public List<Kante> getKanten() {
		return kanten;
	}
	public List<ESL> getEsls() {
		return (List<ESL>) esls;
	}
	public void setKanten(Iterable<Kante> iterable) {
		this.kanten = (List<Kante>) iterable;
	}
	public void setEsls(Iterable<ESL> iterable) {
		this.esls = (List<ESL>) iterable;
	}
	

}
