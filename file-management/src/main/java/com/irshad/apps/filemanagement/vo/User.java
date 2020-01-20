package com.irshad.apps.filemanagement.vo;

import org.springframework.web.multipart.MultipartFile;

public class User {

	private int id;
	private String name;	
	private MultipartFile photo;
	
	public User() {
		
	}
	
	public User(int id, String name, MultipartFile photo) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
}
