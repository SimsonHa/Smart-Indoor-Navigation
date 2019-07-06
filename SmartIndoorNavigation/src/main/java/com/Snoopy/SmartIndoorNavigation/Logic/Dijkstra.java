package com.Snoopy.SmartIndoorNavigation.Logic;

import java.util.List;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;

public class Dijkstra {
	//Ich selbst.
	private Netzpunkt netzpunkt;
	//Weg zu sich selbst.
	private List<Netzkante> netzkanten = null;
	//Gewicht
	private double gewicht;
	//Status
	private boolean status;
	//Final
	private boolean finale;
	
	public Dijkstra (Netzpunkt netzpunkt, List<Netzkante> netzkanten, double gewicht) {
		this.netzpunkt = netzpunkt;
		this.netzkanten = netzkanten;
		this.gewicht = gewicht;
		this.status = true;
	}

	public Netzpunkt getNetzpunkt() {
		return netzpunkt;
	}

	public List<Netzkante> getNetzkanten() {
		return netzkanten;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setNetzpunkt(Netzpunkt netzpunkt) {
		this.netzpunkt = netzpunkt;
	}

	public void setNetzkanten(List<Netzkante> netzkanten) {
		this.netzkanten = netzkanten;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void addNetzkante(Netzkante netzkante) {
		this.netzkanten.add(netzkante);
	}

	public boolean isFinale() {
		return finale;
	}

	public void setFinale(boolean finale) {
		this.finale = finale;
	}
	
	
	
	
}
