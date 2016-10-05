package com.cornell.firstaid;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.cornell.firstaid.dao.FeedbackDAO;
import com.cornell.firstaid.model.DeveloperModel;
import com.cornell.firstaid.model.FeedbackModel;

public class SendFeedbackMesssages extends Thread {
	
	Context ctx;
	
	public SendFeedbackMesssages(Object param){
		this.ctx = (Context) param;
	}
	
	public void run(){
        FeedbackDAO feedbackDAO = new FeedbackDAO(this.ctx);
        List<FeedbackModel> feedbacks = feedbackDAO.getUnsentFeedbacks();
        Iterator iter = feedbacks.iterator();
        while (iter.hasNext()){
        	FeedbackModel feedback = (FeedbackModel) iter.next();
        	Log.d("Threading ... " + feedback.getMsgDevNumber() + " -- " + feedback.getMsgStatus(), feedback.getMsgComment());
        	sendSMSMessageViaDBUnsentMessages(feedback);
        }
		
	}

    /*
     * Sends feedback messages that were not sent. These unsent messages have a status of '0' in TB_COMMENT
     * table. Once re-sent successfully they will have a status of '1'.
     * 
     * param FeedbackModel 
     * returns void
     */
    protected void sendSMSMessageViaDBUnsentMessages(FeedbackModel feedbackModel) {
    	
    	FeedbackModel feedback = new FeedbackModel();
        FeedbackDAO feedbackDAO = new FeedbackDAO(this.ctx);
        String message = feedbackModel.getMsgComment();
        int iStatus = 0;
        Log.i("Send SMS", "");

        List<DeveloperModel> developersDetails = feedbackDAO.getDeveloperDetails();
        Iterator iter = developersDetails.iterator();
        while (iter.hasNext()) {
        	DeveloperModel devModel = (DeveloperModel) iter.next();
	        try {
	           SmsManager smsManager = SmsManager.getDefault();
	           smsManager.sendTextMessage(devModel.getStrDevPhone(), null, message, null, null);
	           iStatus = 1;
	        } catch (Exception e) {
	           e.printStackTrace();
	        }

	        //If there are no errors in sending SMS, now save the record on DB
	        feedback.setMsgComment(message);
	        feedback.setMsgName(""); //TO-DO - in the future if User's Name is indicated ... save it
	        feedback.setMsgPhoneNumber(feedbackModel.getMsgPhoneNumber());
	        feedback.setMsgEmail(feedbackModel.getMsgEmail());
	        feedback.setMsgDevNumber(devModel.getStrDevPhone());
	        feedback.setMsgStatus(Integer.toString(iStatus));
	        
	        feedbackDAO.insertComment(feedback);
        }    
    }
}
