package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity
public class Kante {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Max(value = 2, message = "Maximal 2 Wegpunkte pro Kante")
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Wegpunkt> wegpunkte = new ArrayList<>();
	
	@ManyToOne
	private Grundriss grundriss;
	
	
	public Kante() {
		
	}
	
	public Kante(List<Wegpunkt> wegpunkte) {
		this.wegpunkte = wegpunkte;
	}
	public Kante(List<Wegpunkt> wegpunkte, Grundriss grundriss) {
		this.wegpunkte = wegpunkte;
		this.grundriss = grundriss;
	}

	public List<Wegpunkt> getWegpunkte() {
		return wegpunkte;
	}

	public Grundriss getGrundriss() {
		return grundriss;
	}

	public void setWegpunkte(List<Wegpunkt> wegpunkte) {
		this.wegpunkte = wegpunkte;
	}

	public void setGrundriss(Grundriss grundriss) {
		this.grundriss = grundriss;
	}
	
	
}
