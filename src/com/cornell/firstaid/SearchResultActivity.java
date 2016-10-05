package com.cornell.firstaid;

import java.util.Iterator;
import java.util.List;

import com.cornell.firstaid.dao.CallBackDAO;
import com.cornell.firstaid.dao.SearchResultsActivityDAO;
import com.cornell.firstaid.model.DiseaseModel;
import com.example.firstaid.R;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends ActionBarActivity {
	Button btnCancel, btnCall, btnSearch;	
	EditText edtPhoneNumber, edtSearch;
	TextView tvNoResult;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        tvNoResult = (TextView) findViewById(R.id.tvNoResult);
        
      //multi threading
        SendFeedbackMesssages sendMessage = new SendFeedbackMesssages(this.getBaseContext());
        sendMessage.start();
        
        //to go back to homepage
        getSupportActionBar().setHomeButtonEnabled(true);
        
        Intent intent = getIntent();
        String tags = intent.getStringExtra("result");
        SearchResultsActivityDAO searchDAO = new SearchResultsActivityDAO(this.getBaseContext());
        List<DiseaseModel> diseases = searchDAO.getDiseaseList(tags);
        
        if (null != diseases){
	        Iterator iter = diseases.iterator();
	        while (iter.hasNext()) {
	        	DiseaseModel disease = new DiseaseModel();
	        	disease = (DiseaseModel) iter.next();
	        	Log.d("Test -- ", disease.getStrDisease());
	        	
	        	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.result_layout);
	            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                    LinearLayout.LayoutParams.MATCH_PARENT,
	                    LinearLayout.LayoutParams.WRAP_CONTENT);
	           
	            Button btn = new Button(this);
	            btn.setId(disease.getiDiseaseOid());
	            final int id_ = btn.getId();
	            final String strClassName = disease.getStrClassName();
	            Log.d("ClassName", strClassName);
	            btn.setText(disease.getStrDisease());
	            btn.setBackgroundResource(R.drawable.btn_background);
	            btn.setOnClickListener(new View.OnClickListener() {
	                public void onClick(View v) {
	                    Log.d("ClassName -- Listener, inner class", strClassName);
	                    setView(v, strClassName);
	                }
	            });
	            linearLayout.addView(btn, params);
	        }   
	      } else {
	    	   Log.d("No data!", " null value");
	    	   tvNoResult.setText("No match found. Please try again.");
          }
        
    }
	
	public void setView(View v, String strClassName) {
    	try {
	    	Intent start = new Intent(this, Class.forName(strClassName));
	    	startActivity(start);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
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
