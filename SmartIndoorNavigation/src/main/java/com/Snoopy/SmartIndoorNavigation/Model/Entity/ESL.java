package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ESL {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private double posX;
	@NotNull
	private double posY;
	
	@OneToOne
	private Artikel artikel;
	
	@OneToOne
	private Pi pi;
	
	@ManyToOne
	private Grundriss grundriss;
	
	public ESL() {
		
	}
	
	public ESL(double posX, double posY, Grundriss grundriss) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.grundriss = grundriss;
	}
	public ESL(double posX, double posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Pi getPi() {
		return pi;
	}

	public void setPi(Pi pi) {
		this.pi = pi;
	}
	
	
	
	
	
	
	

}
