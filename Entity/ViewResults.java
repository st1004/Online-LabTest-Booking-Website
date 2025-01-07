package com.LabTest.LabTest.entity;

import java.util.Date;

public class ViewResults {
	
    private String vr_labName;
    private String vr_testName;
    private String vr_scheduleDate;
	public ViewResults(String vr_labName, String vr_testName, String string) {
		super();
		this.vr_labName = vr_labName;
		this.vr_testName = vr_testName;
		this.vr_scheduleDate = string;
	}
	public ViewResults() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getVr_labName() {
		return vr_labName;
	}
	public void setVr_labName(String vr_labName) {
		this.vr_labName = vr_labName;
	}
	public String getVr_testName() {
		return vr_testName;
	}
	public void setVr_testName(String vr_testName) {
		this.vr_testName = vr_testName;
	}
	public String getVr_scheduleDate() {
		return vr_scheduleDate;
	}
	public void setVr_scheduleDate(String vr_scheduleDate) {
		this.vr_scheduleDate = vr_scheduleDate;
	}
    


}
