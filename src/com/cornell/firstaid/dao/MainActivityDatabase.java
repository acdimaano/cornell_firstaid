package com.cornell.firstaid.dao;

import java.util.ArrayList;
import java.util.List;

import com.cornell.firstaid.model.DeveloperModel;

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

public class MainActivityDatabase extends SQLiteOpenHelper implements FirstAidDatabase {
	
	SQLiteDatabase db;

	public MainActivityDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE_MAIN_TABLE);
			db.execSQL(DATABASE_CREATE_DISEASE_TABLE);
			db.execSQL(DATABASE_CREATE_DESC_SYMP_TREAT_TABLE);
			db.execSQL(DATABASE_CREATE_CALLBACK_TABLE);
			db.execSQL(DATABASE_CREATE_DEVELOPER_TABLE);
			db.execSQL(DATABASE_CREATE_COMMENT_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void open(){
		db = this.getWritableDatabase();
	}
	
	public void insertDeveloperDetails (DeveloperModel devModel)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(COL_DEV_NAME, devModel.getStrDevName());
		cv.put(COL_DEV_ADDRESS, devModel.getiDevAddress());
		cv.put(COL_DEV_EMAIL, devModel.getiDevEmail());
		cv.put(COL_DEV_PHONE, devModel.getStrDevPhone());
		
		db.insert(TB_DEVELOPER_DETAILS, null, cv);
		db.close();
	}
	
	public boolean isTablePopulated(String strTableName){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from '"+strTableName+"'", null);
		cursor.moveToFirst();
		int iCount = cursor.getInt(0);
		if (iCount > 0)
			return true;
		else
			return false;
	}
	
}
