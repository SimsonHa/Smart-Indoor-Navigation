package com.Snoopy.SmartIndoorNavigation.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Kategorie {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String name;
	
	private String imgUrl;

	public Kategorie() {
		
	}
	
	public Kategorie(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
