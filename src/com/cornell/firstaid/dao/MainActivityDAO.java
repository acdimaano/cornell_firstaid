package com.cornell.firstaid.dao;

import java.util.List;

import android.content.Context;
import com.cornell.firstaid.model.DeveloperModel;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class MainActivityDAO implements FirstAidDAO {

	Context context;
	MainActivityDatabase db;
	
	public MainActivityDAO(Context ctx){
		db = new MainActivityDatabase(ctx);
	}
	
	public void openDB(){
		db.open();
	}
	public void insertDeveloperDetails (DeveloperModel devModel)
	{
		db.insertDeveloperDetails(devModel);
	}
	
	public boolean isTablePopulated(String strTableName){
		return db.isTablePopulated(strTableName);
	}
	
}
