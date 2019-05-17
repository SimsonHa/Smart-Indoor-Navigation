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
	private String macAdres;
	
	
	public Pi() {
		
	}


	public Pi(String macAdres) {
		super();
		this.macAdres = macAdres;
		this.status = false;
	}


	public boolean isStatus() {
		return status;
	}


	public String getMacAdres() {
		return macAdres;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public void setMacAdres(String macAdres) {
		this.macAdres = macAdres;
	}
	
	
	
}
