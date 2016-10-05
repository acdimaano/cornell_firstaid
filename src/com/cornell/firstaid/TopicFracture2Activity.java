package com.cornell.firstaid;

import java.util.Iterator;
import java.util.List;

import com.cornell.firstaid.dao.CallBackDAO;
import com.cornell.firstaid.dao.DiseaseDAO;
import com.cornell.firstaid.model.DiseaseModel;
import com.example.firstaid.R;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
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


/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class TopicFracture2Activity extends ActionBarActivity 
	{
	TextView txtMuscleCramp;
	Button btnCancel, btnCall, btnSearch;
	EditText edtPhoneNumber, edtSearch;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_topic_fracture2);
	        
	      //to go back to homepage
	        getSupportActionBar().setHomeButtonEnabled(true);
	        
	        txtMuscleCramp = (TextView) findViewById(R.id.txtMuscleCramp);
	        txtMuscleCramp.setMovementMethod(new ScrollingMovementMethod());

	        DiseaseModel diseaseModel = new DiseaseModel();
	        DiseaseDAO diseaseDAO = new DiseaseDAO(this.getBaseContext());
	        String[] diseaseArray= getResources().getStringArray(R.array.muscle_cramp_array);
	        diseaseModel = diseaseDAO.getDiseaseModelDetails(diseaseArray[0]);
	        txtMuscleCramp.setText(Html.fromHtml(diseaseArray[diseaseModel.getiDesc()] + diseaseArray[diseaseModel.getiSymp()] + diseaseArray[diseaseModel.getiTreat()]));
	        txtMuscleCramp.setMovementMethod(new ScrollingMovementMethod());
	        
	        
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