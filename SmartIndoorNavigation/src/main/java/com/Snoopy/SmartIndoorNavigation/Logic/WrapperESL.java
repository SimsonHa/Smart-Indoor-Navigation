package com.Snoopy.SmartIndoorNavigation.Logic;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;

public class WrapperESL {
	Artikel artikel;
	Label label;
	public WrapperESL(Artikel artikel, Label place) {
		super();
		this.artikel = artikel;
		this.label = label;
	}
	
	public WrapperESL() {
		
	}

	public Artikel getArtikel() {
		return artikel;
	}



	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}




	
}
