package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.Snoopy.SmartIndoorNavigation.Model.Repository.WegpunktRepository;

@Entity
public class Wegpunkt {

	@Id
	@GeneratedValue
	private Long id;
	
	private double posX;
	private double posY;
	
	//Anfang oder Ende
	private String status;
	
	//@Autowired
	//WegpunktRepository repoWegpunkt;
	
	public Wegpunkt() {
		
	}

	public Wegpunkt(double posX, double posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		//Ersten Wegpunkt markieren
		/*if(repoWegpunkt.count()<=0) {
			start = true;
		}
		else {
			start = false;
		}*/

	}
	public Wegpunkt(double posX, double posY, String status) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
	
}
