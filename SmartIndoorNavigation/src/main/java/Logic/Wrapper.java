package Logic;

import java.util.List;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Grundriss;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kante;

public class Wrapper {
	private Grundriss grundriss;
	private List<Kante> kanten;
	private List<ESL> esls;
	
	public Wrapper(Grundriss grundriss, List<Kante> kanten, List<ESL>esls) {
		this.grundriss = grundriss;
		this.kanten = kanten;
		this.esls = esls;
	}
	
	public Wrapper() {
		
	}

	public Grundriss getGrundriss() {
		return grundriss;
	}

	public List<Kante> getKanten() {
		return kanten;
	}

	public List<ESL> getEsls() {
		return esls;
	}

	public void setGrundriss(Grundriss grundriss) {
		this.grundriss = grundriss;
	}

	public void setKanten(List<Kante> kanten) {
		this.kanten = kanten;
	}

	public void setEsls(List<ESL> esls) {
		this.esls = esls;
	}
	
	
	
	

}

