package com.Snoopy.SmartIndoorNavigation.Model.Entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
public class Grundriss {
	@Id
	@GeneratedValue
	private Long id;
	
	private byte[] image;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ESL> esls = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Kante> kanten = new ArrayList<>();

	public Grundriss() {
		
	}
	
	public Grundriss(byte[] image) {
		super();
		this.image = image;
	}


	public byte[] getImage() { 
		return image;
	}


	public List<ESL> getEsls() {
		return esls;
	}


	public List<Kante> getKanten() {
		return kanten;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
}
	
	
	

