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
	
	private int[] bild;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ESL> esls = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Kante> kanten = new ArrayList<>();

	public Grundriss() {
		
	}
	
	public Grundriss(int[] bild) {
		super();
		this.bild = bild;
	}


	public int[] getBild() { 
		return bild;
	}


	public List<ESL> getEsls() {
		return esls;
	}


	public List<Kante> getKanten() {
		return kanten;
	}


	public void setBild(int[] bild) {
		this.bild = bild;
	}

	public Long getId() {
		return id;
	}

	public void setEsls(List<ESL> esls) {
		this.esls = esls;
	}

	public void setKanten(List<Kante> kanten) {
		this.kanten = kanten;
	}
	
	

	
	
}
	
	
	

