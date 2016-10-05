package com.cornell.firstaid.dao;

import java.util.ArrayList;
import java.util.List;

import com.cornell.firstaid.model.DeveloperModel;
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
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class FeedbackDatabase extends SQLiteOpenHelper implements FirstAidDatabase {
	
	public FeedbackDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE_COMMENT_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void insertComment(FeedbackModel feedbackModel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		Log.d("Inserting comment ... with status = ", feedbackModel.getMsgStatus());
		//cv.put(OID, feedbackModel.getOid());
		cv.put(COL_NAME, feedbackModel.getMsgName());
		cv.put(COL_PHONE, feedbackModel.getMsgPhoneNumber());
		cv.put(COL_EMAIL, feedbackModel.getMsgEmail());
		cv.put(COL_COMMENT, feedbackModel.getMsgComment());
		cv.put(COL_DEV_NUMBER, feedbackModel.getMsgDevNumber());
		cv.put(COL_STATUS, feedbackModel.getMsgStatus());
		
		db.insert(TB_COMMENT, null, cv);
		db.close();
	}

	public void updateComment(FeedbackModel feedbackModel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		Log.d("Inserting comment ... with status = ", feedbackModel.getMsgStatus());
		//cv.put(OID, feedbackModel.getOid());
		cv.put(COL_NAME, feedbackModel.getMsgName());
		cv.put(COL_PHONE, feedbackModel.getMsgPhoneNumber());
		cv.put(COL_EMAIL, feedbackModel.getMsgEmail());
		cv.put(COL_COMMENT, feedbackModel.getMsgComment());
		cv.put(COL_DEV_NUMBER, feedbackModel.getMsgDevNumber());
		cv.put(COL_STATUS, feedbackModel.getMsgStatus());
		
		db.update(TB_COMMENT, cv, Integer.toString(feedbackModel.getOid()), null);
		db.close();
	}

	public List<DeveloperModel> getDeveloperDetails(){
		List<DeveloperModel> developersDetails = new ArrayList<DeveloperModel>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DATABASE_SELECT_DEVELOPER_TABLE, null);
		
		if (cursor.moveToNext()) {
			do
			{ 
				Log.d(cursor.getString(0) + cursor.getString(1) + cursor.getString(2)+ cursor.getString(3)+ cursor.getString(4), "test!");
				DeveloperModel devModel = new DeveloperModel();
				devModel.setStrDevName(cursor.getString(1));
				devModel.setiDevAddress(Integer.parseInt(cursor.getString(2)));
				devModel.setiDevEmail(Integer.parseInt(cursor.getString(3)));
				devModel.setStrDevPhone(cursor.getString(4));
				developersDetails.add(devModel);
			} while (cursor.moveToNext());
		}

		return developersDetails;
		
	}
	
	public List<FeedbackModel> getUnsentFeedbacks(){
		List<FeedbackModel> feedbacksModel = new ArrayList<FeedbackModel>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DATABASE_SELECT_COMMENTS, null);
		if (cursor.moveToNext()) {
			do
			{ 
				Log.d("Data retrieved! :: ", cursor.getString(0));
				FeedbackModel feedback = new FeedbackModel();
				feedback.setMsgName(cursor.getString(1));
				feedback.setMsgPhoneNumber(cursor.getString(2));
				feedback.setMsgEmail(cursor.getString(3));
				feedback.setMsgComment(cursor.getString(4));
				feedback.setMsgDevNumber(cursor.getString(5));
				feedback.setMsgStatus(cursor.getString(6));
				feedbacksModel.add(feedback);
			} while (cursor.moveToNext());
		}		
		return feedbacksModel;
	}
	
}
