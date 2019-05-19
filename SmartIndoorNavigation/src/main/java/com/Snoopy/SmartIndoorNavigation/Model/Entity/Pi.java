package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pi {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private boolean status;
	
	@NotNull
	private String macAdress;
	
	
	public Pi() {
		
	}


	public Pi(String macAdress) {
		super();
		this.macAdress = macAdress;
		this.status = false;
	}


	public boolean getStatus() {
		return status;
	}


	public String getMacAdres() {
		return macAdress;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public void setMacAdres(String macAdres) {
		this.macAdress = macAdress;
	}
	
	
	
}
