package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Netzpunkt {

	@Id
	@GeneratedValue
	private Long id;
	
	private double posX;
	private double posY;
	
	private boolean start;
	
	@OneToOne
	Artikel artikel;
	
	public Netzpunkt() {
		
	}
	
	public Netzpunkt(double posX, double posY, boolean start) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.start = start;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public boolean getStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
	
	
	
	
	
}
