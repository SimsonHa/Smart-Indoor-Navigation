package com.Snoopy.SmartIndoorNavigation.Logic;

public class WrapperNetz {
	long id;
	double x;
	double y;
	String status;
	int [] connectedTo;
	
	public WrapperNetz(){
		
	}
	
	public WrapperNetz(Long id, double x, double y, String status, int[] connectedTo) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.status = status;
		this.connectedTo = connectedTo;
	}

	public Long getId() {
		return id;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String getStatus() {
		return status;
	}
	public int[] getConnectedTo() {
		return connectedTo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setConnectedTo(int[] connectedTo) {
		this.connectedTo = connectedTo;
	}
	
	
}
