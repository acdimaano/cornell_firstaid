package com.cornell.firstaid.dao;

import java.util.List;

import com.cornell.firstaid.model.DeveloperModel;
import com.cornell.firstaid.model.FeedbackModel;

import android.content.Context;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class FeedbackDAO implements FirstAidDAO {
	
	Context context;
	FeedbackDatabase db;
	
	public FeedbackDAO(Context ctx){
		db = new FeedbackDatabase(ctx);
	}

	public void insertComment(FeedbackModel feedback){
		db.insertComment(feedback);
	}
	
	public List<DeveloperModel> getDeveloperDetails(){
		return db.getDeveloperDetails();
	}
	
	public List<FeedbackModel> getUnsentFeedbacks(){
		return db.getUnsentFeedbacks();
	}

}
