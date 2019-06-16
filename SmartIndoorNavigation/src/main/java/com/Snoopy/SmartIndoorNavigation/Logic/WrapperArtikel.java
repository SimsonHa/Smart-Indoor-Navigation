package com.Snoopy.SmartIndoorNavigation.Logic;

import java.util.List;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;


public class WrapperArtikel {

	private List<Artikel> artikel;
	
	public WrapperArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	public WrapperArtikel() {
		
	}

	public List<Artikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	
	
}
