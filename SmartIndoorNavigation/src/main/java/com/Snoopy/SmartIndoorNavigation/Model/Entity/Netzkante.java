package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Netzkante {

	@Id
	@GeneratedValue
	private Long id;
	
	//@Max(value = 2, message = "Maximal 2 Wegpunkte pro Kante")
	@ManyToMany(cascade = CascadeType.ALL)
	//@LazyCollection(LazyCollectionOption.FALSE)
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double distance () {
		double x = Math.abs(netzPunkte.get(0).getPosX() - netzPunkte.get(1).getPosX());
		double y = Math.abs(netzPunkte.get(0).getPosY() - netzPunkte.get(1).getPosY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	
	
	
}
