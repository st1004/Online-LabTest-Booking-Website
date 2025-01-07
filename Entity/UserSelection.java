package com.LabTest.LabTest.entity;

import java.util.List;

public class UserSelection {   
	private String us_username;  
	private String us_labname;  
	private String us_selectedHour; 
	private List<String> us_selectedTest;
	private int us_totalPrice;
	public UserSelection(String us_username, String us_labname, String us_selectedHour, List<String> us_selectedTest,
			int us_totalPrice) {
		super();
		this.us_username = us_username;
		this.us_labname = us_labname;
		this.us_selectedHour = us_selectedHour;
		this.us_selectedTest = us_selectedTest;
		this.us_totalPrice = us_totalPrice;
	}
	public UserSelection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUs_username() {
		return us_username;
	}
	public void setUs_username(String us_username) {
		this.us_username = us_username;
	}
	public String getUs_labname() {
		return us_labname;
	}
	public void setUs_labname(String us_labname) {
		this.us_labname = us_labname;
	}
	public String getUs_selectedHour() {
		return us_selectedHour;
	}
	public void setUs_selectedHour(String us_selectedHour) {
		this.us_selectedHour = us_selectedHour;
	}
	public List<String> getUs_selectedTest() {
		return us_selectedTest;
	}
	public void setUs_selectedTest(List<String> us_selectedTest) {
		this.us_selectedTest = us_selectedTest;
	}
	public int getUs_totalPrice() {
		return us_totalPrice;
	}
	public void setUs_totalPrice(int us_totalPrice) {
		this.us_totalPrice = us_totalPrice;
	}
	
	
}