package com.Snoopy.SmartIndoorNavigation.Logic;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;

public class WrapperESL {
	Artikel product;
	Transform transform;
	public WrapperESL(Artikel product, Transform transform) {
		super();
		this.product = product;
		this.transform = transform;
	}
	
	public WrapperESL() {
		
	}

	public Artikel getArtikel() {
		return product;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setArtikel(Artikel product) {
		this.product = product;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
	
}
