package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wegpunkt {

	@Id
	@GeneratedValue
	private Long id;
	
	private int posX;
	private int posY;
	

	public Wegpunkt(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	
	
}
