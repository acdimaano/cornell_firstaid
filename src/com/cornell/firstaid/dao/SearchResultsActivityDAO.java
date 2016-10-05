package com.cornell.firstaid.dao;

import java.util.List;

import android.content.Context;

import com.cornell.firstaid.model.DiseaseModel;



/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class SearchResultsActivityDAO implements FirstAidDAO {
	Context context;
	SearchResultsActivityDatabase db;
	
	public SearchResultsActivityDAO(Context ctx){
		db = new SearchResultsActivityDatabase(ctx);
	}
	
	public List<DiseaseModel> getDiseaseList(String strSearchItem){
		return db.getDiseaseList(strSearchItem);
	}

}
