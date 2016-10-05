package com.cornell.firstaid.model;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class DiseaseModel extends FirstAidModel {
	
	public String strDisease;
	public String strTags;
	public String strClassName;

	public int iDesc;
	public int iSymp;
	public int iTreat;
	public int iDiseaseOid;
	
	
	public int getiDiseaseOid() {
		return iDiseaseOid;
	}
	public void setiDiseaseOid(int iDiseaseOid) {
		this.iDiseaseOid = iDiseaseOid;
	}
	public String getStrDisease() {
		return strDisease;
	}
	public void setStrDisease(String strDisease) {
		this.strDisease = strDisease;
	}
	public String getStrTags() {
		return strTags;
	}
	public void setStrTags(String strTags) {
		this.strTags = strTags;
	}
	public int getiDesc() {
		return iDesc;
	}
	public void setiDesc(int iDesc) {
		this.iDesc = iDesc;
	}
	public int getiSymp() {
		return iSymp;
	}
	public void setiSymp(int iSymp) {
		this.iSymp = iSymp;
	}
	public int getiTreat() {
		return iTreat;
	}
	public void setiTreat(int iTreat) {
		this.iTreat = iTreat;
	}
	
	public String getStrClassName() {
		return strClassName;
	}
	public void setStrClassName(String strClassName) {
		this.strClassName = strClassName;
	}

}
