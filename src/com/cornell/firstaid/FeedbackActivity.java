package com.cornell.firstaid;

import java.util.Iterator;
import java.util.List;

import com.cornell.firstaid.dao.CallBackDAO;
import com.cornell.firstaid.dao.FeedbackDAO;
import com.cornell.firstaid.model.FeedbackModel;
import com.cornell.firstaid.model.DeveloperModel;
import com.example.firstaid.R;
import com.example.firstaid.R.layout;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class FeedbackActivity extends ActionBarActivity 
	{
		EditText edtComment, edtPhone, edtEmail, edtPhoneNumber, edtSearch;
		Button btnSend, btnCancel, btnCall, btnSearch;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_feedback);
	        
	        //multi threading
	        SendFeedbackMesssages sendMessage = new SendFeedbackMesssages(this.getBaseContext());
	        sendMessage.start();
	        
	        //to go back to homepage
	        getSupportActionBar().setHomeButtonEnabled(true);
	        
	        edtComment = (EditText) findViewById(R.id.edtComment);
	        edtPhone = (EditText) findViewById(R.id.edtPhone);
	        //edtEmail = (EditText) findViewById(R.id.edtEmail);
	        btnSend = (Button) findViewById(R.id.btnSend);

	        btnSend.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	               sendSMSMessage();
	            }
	         });        
	        
	    }

	    protected void sendSMSMessage() {
	    	
	    	FeedbackModel feedback = new FeedbackModel();
	        FeedbackDAO feedbackDAO = new FeedbackDAO(this.getBaseContext());
	        String message = edtComment.getText().toString();
	        int iStatus = 0;
	        Log.i("Send SMS", "");

	        List<DeveloperModel> developersDetails = feedbackDAO.getDeveloperDetails();
	        Iterator iter = developersDetails.iterator();
	        while (iter.hasNext()) {
	        	DeveloperModel devModel = (DeveloperModel) iter.next();
		        try {
		           SmsManager smsManager = SmsManager.getDefault();
		           smsManager.sendTextMessage(devModel.getStrDevPhone(), null, message, null, null);
		           Toast.makeText(getApplicationContext(), "SMS sent.",
		           Toast.LENGTH_LONG).show();
		           iStatus = 1;
		        } catch (Exception e) {
		           Toast.makeText(getApplicationContext(),
		           "SMS faild, please try again.",
		           Toast.LENGTH_LONG).show();
		           e.printStackTrace();
		        }
	
		        //If there are no errors in sending SMS, now save the record on DB
		        feedback.setMsgComment(message);
		        feedback.setMsgName(""); //TO-DO - in the future if User's Name is indicated ... save it
		        feedback.setMsgPhoneNumber(edtPhone.getText().toString());
		        //feedback.setMsgEmail(edtEmail.getText().toString());
		        feedback.setMsgDevNumber(devModel.getStrDevPhone());
		        feedback.setMsgStatus(Integer.toString(iStatus));
		        
		        feedbackDAO.insertComment(feedback);
	        }    
	        edtComment.setText("");
	        edtPhone.setText("");
	           
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu items for use in the action bar
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main_topicpages, menu);
	        return super.onCreateOptionsMenu(menu);
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) 
	    {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	    	
	    	switch (item.getItemId()){	
	    	case R.id.action_phonecall:
	    		final Dialog dialog = new Dialog(this);
	    		dialog.setContentView(R.layout.activity_phone_call);
	    		dialog.setTitle("Emergency Call");
	    	
	    		btnCancel = (Button)dialog.findViewById(R.id.btnCallCancel);
	    		btnCancel.setOnClickListener(new OnClickListener()
	    		{	
					@Override
					public void onClick(View v)
					{	
						dialog.dismiss();
					}
				});
	    		btnCall = (Button)dialog.findViewById(R.id.btnCall);
	    		edtPhoneNumber = (EditText)dialog.findViewById(R.id.edtPhoneNumber);
	    		btnCall.setOnClickListener(new OnClickListener()
	    		{	
					@Override
					public void onClick(View arg0)
					{		
						makeCall();
						edtPhoneNumber.setText("");
						dialog.dismiss();
					}
					protected void makeCall()
		        	{
		        		Intent callIntent = new Intent (Intent.ACTION_CALL);
		        		String strPhoneNumber = edtPhoneNumber.getText().toString().trim();
		        		callIntent.setData(Uri.parse("tel:" + strPhoneNumber));
		        		try
		        		{
			        		startActivity(callIntent);
			        		//insert number to DB
			        		saveCallBackNumber(strPhoneNumber);
		        		}
		        		catch (ActivityNotFoundException ntfnd)
		        		{
		        			ntfnd.printStackTrace();
		        		}
		        	}
				});
	    	
	    		//Callback Code Start
	    		CallBackDAO calls = new CallBackDAO(this.getBaseContext());
	    		List<String> listCalls = calls.getLastEmergencyCalls();
	    		Iterator iter = listCalls.iterator();
	    		int iCounter = 1;

	    		while (iter.hasNext()){
	    			final String callNumber = (String) iter.next();
	    			Log.d("Call number :: ", callNumber);
	    			
	        		//setContentView(R.layout.activity_phone_call);
	            	LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.popup_element);
	                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                        LinearLayout.LayoutParams.MATCH_PARENT,
	                        LinearLayout.LayoutParams.WRAP_CONTENT);
	                Button btn = new Button(this);
	                btn.setId(iCounter);              
	                btn.setBackgroundResource(R.drawable.btn_background);
	                
	                iCounter++;
	                final int id_ = btn.getId();
	                btn.setText("Call :: " + callNumber);
	                Log.d("Button ID :: ", "" + btn.getId());
	                btn.setOnClickListener(new View.OnClickListener() {
	                    public void onClick(View v) {
	    					makeCall();
	    				}
	    				protected void makeCall()
	    	        	{
	    	        		Intent callIntent = new Intent (Intent.ACTION_CALL);
	    	        		String strPhoneNumber = callNumber.trim();
	    	        		callIntent.setData(Uri.parse("tel:" + strPhoneNumber));
	    	        		try
	    	        		{
	    		        		startActivity(callIntent);
	    		        		//insert number to DB
	    	        		}
	    	        		catch (ActivityNotFoundException ntfnd)
	    	        		{
	    	        			ntfnd.printStackTrace();
	    	        		}
	    	        	}
	                });
	                try { linearLayout.addView(btn, params); } catch (Exception e) { e.printStackTrace(); }
	    			
	    		}
	    		//Callback Code End
	    		
	    		dialog.show();
	    		return true;
	        }
	    	switch (item.getItemId())
	    	{
	    		case R.id.action_search:
	    		final Dialog dialog = new Dialog(this);
	    		dialog.setContentView(R.layout.activity_search);
	    		dialog.setTitle("Search by Symptoms");
	    		
	    		btnCancel = (Button)dialog.findViewById(R.id.btnSearchCancel);
	    		btnCancel.setOnClickListener(new OnClickListener()
	    		{	
					@Override
					public void onClick(View v)
					{
						dialog.dismiss();
					}
				});
	    		btnSearch = (Button)dialog.findViewById(R.id.btnSearch);
	    		edtSearch = (EditText)dialog.findViewById(R.id.edtSearch);
	    		btnSearch.setOnClickListener(new OnClickListener()
	    		{	
					@Override
					public void onClick(View v)
					{
					
						display();
					}
				});
	    		dialog.show();
	    		return true;	
	    	}
	        return super.onOptionsItemSelected(item);
	    }   
	    public void display()
	    {		
	    	Intent searchResult = new Intent(this, SearchResultActivity.class);
			searchResult.putExtra("result", edtSearch.getText().toString());
			startActivity(searchResult);
	    }
	    public void saveCallBackNumber(String strNumber){
			CallBackDAO callBackDAO = new CallBackDAO(this.getBaseContext());
			callBackDAO.insertNumber(strNumber);
	    }
	}