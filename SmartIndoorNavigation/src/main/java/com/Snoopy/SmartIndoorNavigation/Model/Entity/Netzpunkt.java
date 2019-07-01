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
	
	private String status;
	
	@OneToOne
	Artikel artikel;
	
	//private double[] transform;
	
	public Netzpunkt() {
		
	}
	
	public Netzpunkt(double posX, double posY) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
}
