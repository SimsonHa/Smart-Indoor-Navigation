package com.Snoopy.SmartIndoorNavigation.Logic;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;

public class WrapperESL {
	Artikel artikel;
	Place place;
	public WrapperESL(Artikel artikel, Place place) {
		super();
		this.artikel = artikel;
		this.place = place;
	}
	
	public WrapperESL() {
		
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public Place getPlace() {
		return place;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public void setPlace(Place place) {
		this.place = place;
	}


	
}
