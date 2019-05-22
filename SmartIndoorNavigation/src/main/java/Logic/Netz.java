package Logic;
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
		
		
		List<Netzkante> nKList = new ArrayList();
		for (int i =0; i<kSize; i++) {
			double nP1X = kanten.get(i).getWegpunkte().get(0).getPosX();
			double nP1Y = kanten.get(i).getWegpunkte().get(0).getPosY();
			
			Netzpunkt nP1 = new Netzpunkt(nP1X, nP1Y);
			
			double nP2X = kanten.get(i).getWegpunkte().get(1).getPosX();
			double nP2Y = kanten.get(i).getWegpunkte().get(1).getPosY();
			
			Netzpunkt nP2 = new Netzpunkt(nP2X, nP2Y);
			List<Netzpunkt> nPList = new ArrayList();
			nPList.add(nP1);
			nPList.add(nP2);
			
			Netzkante nK = new Netzkante(nPList);	
			
			nKList.add(nK);			
		}
		
		int eslSize = esls.size();
		
		List<Netzkante> nKListNew = new ArrayList();
		List<Netzkante> nKListDelete = new ArrayList();
		
		for (int j = 0; j<eslSize; j++) {
			//Sehr große Zahl 
			double laenge = 999999999;
			//Nähste Kante
			Netzkante netzKante = new Netzkante();
			//Neuer Wegpunkt
			double xNew = 0;
			double yNew = 0;
			
			for (int k =0; k<kSize; k++) {
				//Kantenpunkte
				double aX = nKList.get(k).getNetzPunkte().get(0).getPosX();
				double aY = nKList.get(k).getNetzPunkte().get(0).getPosY();
				double bX = nKList.get(k).getNetzPunkte().get(1).getPosX();
				double bY = nKList.get(k).getNetzPunkte().get(1).getPosY();
				
				
				double m1 = (aY-bY)/(aX-bX);
				double r1 = aY-(m1*aX);
				
				//Negativer Kehrwert
				double m2 = Math.pow(m1, -1) * -1;
				
				//ESL Punkt
				double pX = esls.get(j).getPosX();
				double pY = esls.get(j).getPosY();
				
				double r2 = pY-(m2*pX);
				
				//Schnittpunkt berechnen
				double x = (r2-r1)/(m1-m2);
				double y = m1*x+r1;
				
				//Länge des Etiketts zur Kante berechnen
				double l = Math.sqrt(Math.pow((x-pX), 2)+Math.pow((y-pY), 2));
				//Wenn es der kürzere Weg ist, dann speichern
				if(l<laenge) {
					laenge = l;
					netzKante = nKList.get(k);
					xNew = x;
					yNew = y;
					
				}
			}
			
			Netzpunkt pNew = new Netzpunkt(xNew, yNew);
			pNew.setArtikel(esls.get(j).getArtikel());
			
			List<Netzpunkt> l1New = new ArrayList();
			l1New.add(pNew);
			l1New.add(netzKante.getNetzPunkte().get(0));
			Netzkante k1New = new Netzkante(l1New);
			
			List<Netzpunkt> l2New = new ArrayList();
			l1New.add(pNew);
			l1New.add(netzKante.getNetzPunkte().get(1));
			Netzkante k2New = new Netzkante(l2New);
			
			
			nKListDelete.add(netzKante);
			nKListNew.add(k1New);
			nKListNew.add(k2New);

			
		}
		
		int nKListDeleteSize = nKListDelete.size();
		int nKListNewSize = nKListNew.size();
		//Überflüssige Kanten löschen
		for(int e = 0; e< nKListDeleteSize; e++) {
			int delIndex = nKList.indexOf(nKListDelete.get(e));
			nKList.remove(delIndex);
		}
		//Neue Kanten hinzufügen
		for(int f = 0; f< nKListNewSize; f++) {
			nKList.add(nKListNew.get(f));
		}
		
		//Netz speichern
		
		kSize = nKList.size();
		
		for(int g = 0; g< kSize; g++) {
			repoNetzpunkt.save(nKList.get(g).getNetzPunkte().get(0));
			repoNetzpunkt.save(nKList.get(g).getNetzPunkte().get(1));
			
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
