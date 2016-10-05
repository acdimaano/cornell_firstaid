package com.cornell.firstaid.dao;

import com.cornell.firstaid.model.DiseaseModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class DiseaseDatabase extends SQLiteOpenHelper implements FirstAidDatabase {
	
	public DiseaseDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("Create disease table :: ", DATABASE_CREATE_DISEASE_TABLE);
		Log.d("Create disease desc table :: ", DATABASE_CREATE_DESC_SYMP_TREAT_TABLE);

		try {
			db.execSQL(DATABASE_CREATE_DISEASE_TABLE);
			db.execSQL(DATABASE_CREATE_DESC_SYMP_TREAT_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data", " -- Test!");
        db.execSQL("DROP TABLE IF EXISTS " + TB_DISEASE);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DESC_SYMP_TREAT);
        onCreate(db);

	}

	public void insertDisease(DiseaseModel diseaseModel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(COL_DISEASE_NAME, diseaseModel.getStrDisease());
		cv.put(COL_TAGS, diseaseModel.getStrTags());
		cv.put(COL_CLASS_NAME, diseaseModel.getStrClassName());
		
		db.insert(TB_DISEASE, null, cv);
		db.close();
	}
	
	public int getOidFromTable(String strItem){
		String query = "SELECT * FROM " + TB_DISEASE + " where " + COL_DISEASE_NAME + " = '" + strItem + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
	    if (cursor != null) {
	        cursor.moveToFirst();
	        Log.d(cursor.getString(0) + " -- " + cursor.getString(1), " :: test!");
	    }
	    
	    return Integer.parseInt(cursor.getString(0));
	}
	
	public void insertDiseaseDescSympTreat (DiseaseModel diseaseModel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(COL_DISEASE_OID, diseaseModel.getiDiseaseOid());
		cv.put(COL_DESC_ID, diseaseModel.getiDesc());
		cv.put(COL_SYMP_ID, diseaseModel.getiSymp());
		cv.put(COL_TREAT_ID, diseaseModel.getiTreat());
		cv.put(COL_TAGS, diseaseModel.getStrTags());
		
		db.insert(TB_DESC_SYMP_TREAT, null, cv);
		db.close();
		
	}
	
	public DiseaseModel getDiseaseModelDetails(String strItem){
		DiseaseModel diseaseModel = new DiseaseModel();
		int i = getOidFromTable(strItem);
		String query = "SELECT * FROM " + TB_DESC_SYMP_TREAT + " WHERE " + COL_DISEASE_OID + " = " + i;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
	    if (cursor != null) {
	        cursor.moveToFirst();
	        Log.d(cursor.getString(0) + " -- " + cursor.getString(1) + " -- " + cursor.getString(2) + " -- " + cursor.getString(3) + " -- " + cursor.getString(4) + " -- " + cursor.getString(5) , " :: test!");
	        diseaseModel.setiDiseaseOid(Integer.parseInt(cursor.getString(1)));
	        diseaseModel.setiDesc(Integer.parseInt(cursor.getString(2)));
	        diseaseModel.setiSymp(Integer.parseInt(cursor.getString(3)));
	        diseaseModel.setiTreat(Integer.parseInt(cursor.getString(4)));
	        diseaseModel.setStrTags(cursor.getString(5));
	        
	    }
		
		return diseaseModel;
	}
}
