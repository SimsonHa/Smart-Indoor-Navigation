package com.Snoopy.SmartIndoorNavigation.Logic;

public class Place {
	long id;
	String mac;
	double x;
	double y;
	public Place(long id, String mac, double x, double y) {
		super();
		this.id = id;
		this.mac = mac;
		this.x = x;
		this.y = y;
	}
	
	
	public Place() {
		
	}


	public long getId() {
		return id;
	}


	public String getMac() {
		return mac;
	}


	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}



	public void setId(long id) {
		this.id = id;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public void setX(double x) {
		this.x = x;
	}


	public void setY(double y) {
		this.y = y;
	}
	
	
}
