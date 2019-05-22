package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Artikel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	@NotNull
	private double preis;
	@NotNull
	private String artNr;
	
	@NotNull
	@ManyToOne
	private Kategorie kategorie;
	
	public Artikel() {
		
	}
		
	public Artikel(String name, double preis, String artNr, Kategorie kategorie) {
		super();
		this.name = name;
		this.preis = preis;
		this.artNr = artNr;
		this.kategorie = kategorie;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public String getArtNr() {
		return artNr;
	}
	public void setArtNr(String artNr) {
		this.artNr = artNr;
	}

	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	
}
