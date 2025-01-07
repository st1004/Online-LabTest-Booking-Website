package com.LabTest.LabTest.entity;
import java.util.Date;


public class ViewResult1 {
	
	private String vr_labName;
    private String vr_testName;
    private String vr_scheduleDate;
    private String vr_result;
	public ViewResult1(String vr_labName, String vr_testName, String vr_scheduleDate, String vr_result) {
		super();
		this.vr_labName = vr_labName;
		this.vr_testName = vr_testName;
		this.vr_scheduleDate = vr_scheduleDate;
		this.vr_result = vr_result;
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
	public String getVr_result() {
		return vr_result;
	}
	public void setVr_result(String vr_result) {
		this.vr_result = vr_result;
	}
	public ViewResult1() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
