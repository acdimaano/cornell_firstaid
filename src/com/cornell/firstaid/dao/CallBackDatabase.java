package com.cornell.firstaid.dao;

import java.util.ArrayList;
import java.util.List;

import com.cornell.firstaid.model.FeedbackModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class CallBackDatabase extends SQLiteOpenHelper implements FirstAidDatabase {

	public CallBackDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE_CALLBACK_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void insertNumber (String strNumber)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(COL_CALLBACK_NUMBER, strNumber);
		
		db.insert(TB_CALLBACK_FUNC, null, cv);
		db.close();
	}

	public List<String> getLastEmergencyCalls(){
		List<String> lastCalls = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DATABASE_SELECT_LAST_CALLS, null);
		
		int iCounter = 1;
		if (cursor.moveToNext()) {
			do
			{ 
				Log.d("Data retrieved! :: ", cursor.getString(0));
				lastCalls.add(cursor.getString(1));
				iCounter++;
				
				if (iCounter == 4) break;
			} while (cursor.moveToNext());
		}		
		return lastCalls;
	}

}
