package com.cornell.firstaid.dao;

import com.cornell.firstaid.model.DiseaseModel;

import android.content.Context;


/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class DiseaseDAO implements FirstAidDAO {
	
	Context context;
	DiseaseDatabase db;
	
	
	public DiseaseDAO(Context ctx){
		db = new DiseaseDatabase(ctx);
	}
	
	public void insertDisease(DiseaseModel heartModel){
		db.insertDisease(heartModel);
	}
	
	public void insertDiseaseDescSympTreat(DiseaseModel heartModel){
		db.insertDiseaseDescSympTreat(heartModel);
	}
	
	public int getOidFromTable(String strItem){
		return db.getOidFromTable(strItem);
	}
	
	public DiseaseModel getDiseaseModelDetails(String strItem){
		return db.getDiseaseModelDetails(strItem);
	}
}
