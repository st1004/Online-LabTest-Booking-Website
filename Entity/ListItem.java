package com.LabTest.LabTest.entity;

public class ListItem {
	
	    private String column1;
	    private int column2;

	    public ListItem(String column1, int price) {
	        this.column1 = column1;
	        this.column2 = price;
	    }

	    // Getters and setters
	    public String getColumn1() {
	        return column1;
	    }

	    public void setColumn1(String column1) {
	        this.column1 = column1;
	    }

	    public int getColumn2() {
	        return column2;
	    }

	    public void setColumn2(int column2) {
	        this.column2 = column2;
	    }
	}



