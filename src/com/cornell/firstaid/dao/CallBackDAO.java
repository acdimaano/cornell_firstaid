package com.cornell.firstaid.dao;

import java.util.List;

import android.content.Context;

/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class CallBackDAO implements FirstAidDAO {

	Context context;
	CallBackDatabase db;
	
	public CallBackDAO(Context ctx){
		db = new CallBackDatabase(ctx);
	}

	public void insertNumber(String strNumber){
		db.insertNumber(strNumber);		
	}

	public List<String> getLastEmergencyCalls(){
		return db.getLastEmergencyCalls();
	}
}
