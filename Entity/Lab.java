package com.LabTest.LabTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lab {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	//private String reason;
	private String price;
	public Lab(int id, String name, String reason, String price) {
		super();
		this.id = id;
		this.name = name;
		//this.reason = reason;
		this.price = price;
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
	//public String getReason() {
	//	return reason;
	//}
	//public void setReason(String reason) {
	//	this.reason = reason;
	//}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Lab() {
		super();
		// TODO Auto-generated constructor stub
	}

}
