package com.cornell.firstaid.dao;

import java.util.ArrayList;
import java.util.List;

import com.cornell.firstaid.model.DiseaseModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class SearchResultsActivityDatabase extends SQLiteOpenHelper implements FirstAidDatabase {
	SQLiteDatabase db;

	public SearchResultsActivityDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public List<DiseaseModel> getDiseaseList(String strSearchItem){
		List<DiseaseModel> diseases = null;
		String query = "SELECT * FROM " + TB_DISEASE + " WHERE " + COL_TAGS + " like '%" + strSearchItem + "%'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if (null != cursor && cursor.getCount() > 0){
			diseases = new ArrayList<DiseaseModel>();
			if (cursor.moveToNext()) {
				do
				{ 
					Log.d(cursor.getString(0) + " -- " + cursor.getString(1) + " -- " + cursor.getString(2) , "test!");
					DiseaseModel diseaseModel = new DiseaseModel();
					diseaseModel.setStrDisease(cursor.getString(1));
					diseaseModel.setiDiseaseOid(Integer.parseInt(cursor.getString(0)));
					diseaseModel.setStrClassName(cursor.getString(3));
					diseases.add(diseaseModel);
				} while (cursor.moveToNext());
			}
			db.close();
		} else {
			Log.d("No data!", " null value");
			db.close();
			return null;
		}
	    return diseases;
	}

}
