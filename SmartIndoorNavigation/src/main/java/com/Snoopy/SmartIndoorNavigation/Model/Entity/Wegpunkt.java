package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Wegpunkt {

	@Id
	@GeneratedValue
	private Long id;
	
	private double posX;
	private double posY;
	

	
	public Wegpunkt() {
		
	}

	public Wegpunkt(double posX, double posY) {
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

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	
	
	
}
