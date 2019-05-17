package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;

@Entity
public class Kante {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Max(value = 2, message = "Maximal 2 Wegpunkte pro Kante")
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Wegpunkt> wegpunkte = new ArrayList<>();
	
	public Kante(Wegpunkt wegpunkt1, Wegpunkt wegpunkt2) {
		this.wegpunkte.add(wegpunkt1);
		this.wegpunkte.add(wegpunkt2);
	}

}
