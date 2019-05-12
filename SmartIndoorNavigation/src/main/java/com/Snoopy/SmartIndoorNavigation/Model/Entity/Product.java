package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private double price;
	private String artNr;
	
	public Product() {
		
	}
	
	public Product(String name, double price, String artNr) {
		super();
		this.name = name;
		this.price = price;
		this.artNr = artNr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getArtNr() {
		return artNr;
	}
	public void setArtNr(String artNr) {
		this.artNr = artNr;
	}
	
	
}
