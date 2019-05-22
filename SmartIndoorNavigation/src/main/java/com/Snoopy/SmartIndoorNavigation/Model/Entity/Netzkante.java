package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Netzkante {

	@Id
	@GeneratedValue
	private Long id;
	
	//@Max(value = 2, message = "Maximal 2 Wegpunkte pro Kante")
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Netzpunkt> netzPunkte = new ArrayList<>();
	
	public Netzkante() {
		
	}
	public Netzkante(List<Netzpunkt> netzPunkte) {
		super();
		this.netzPunkte = netzPunkte;
	}
	public List<Netzpunkt> getNetzPunkte() {
		return netzPunkte;
	}
	public void setNetzPunkte(List<Netzpunkt> netzPunkte) {
		this.netzPunkte = netzPunkte;
	}
	
	
	
	
}
