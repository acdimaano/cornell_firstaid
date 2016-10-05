package com.cornell.firstaid.model;

/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class DeveloperModel extends FirstAidModel {
	
	String strDevName;
	String strDevPhone;

	int iDevAddress;
	int iDevEmail;
	
	public String getStrDevName() {
		return strDevName;
	}
	public void setStrDevName(String strDevName) {
		this.strDevName = strDevName;
	}
	public int getiDevAddress() {
		return iDevAddress;
	}
	public void setiDevAddress(int iDevAddress) {
		this.iDevAddress = iDevAddress;
	}
	public int getiDevEmail() {
		return iDevEmail;
	}
	public void setiDevEmail(int iDevEmail) {
		this.iDevEmail = iDevEmail;
	}
	public String getStrDevPhone() {
		return strDevPhone;
	}
	public void setStrDevPhone(String strDevPhone) {
		this.strDevPhone = strDevPhone;
	}
}
