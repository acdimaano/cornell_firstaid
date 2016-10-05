package com.cornell.firstaid;

import java.util.Iterator;
import java.util.List;

import com.cornell.firstaid.dao.CallBackDAO;
import com.cornell.firstaid.dao.DiseaseDAO;
import com.cornell.firstaid.dao.MainActivityDAO;
import com.cornell.firstaid.model.DeveloperModel;
import com.cornell.firstaid.model.DiseaseModel;
import com.example.firstaid.R;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


/*
 * Developed by : Alan Dimaano
 *                Rhiza Roque
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class MainActivity extends ActionBarActivity {
	Button btnCategory, btnClose, btnCancel, btnCall, btnSearch;	
	PopupWindow pwindow;
	EditText edtPhoneNumber, edtSearch;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadDBAndData();
        SendFeedbackMesssages sendMessage = new SendFeedbackMesssages(this.getBaseContext());
        sendMessage.start();
   	
        btnCategory = (Button)findViewById(R.id.btnHeart);
        btnCategory.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();
				
			}
			
		private void initiatePopupWindow(){
			try {
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.activity_popup_heart,(ViewGroup)findViewById(R.id.popup_element));
				
				pwindow = new PopupWindow(layout, 350, 400, true);
				pwindow.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				pwindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
				
				btnClose = (Button)layout.findViewById(R.id.btnClose);
				btnClose.setOnClickListener(cancel_button_click_listener);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		

		private OnClickListener cancel_button_click_listener = new OnClickListener() {
			
			public void onClick(View v) {
			pwindow.dismiss();	
			}
		};
      });                	
    

        btnCategory = (Button)findViewById(R.id.btnFracture);
        btnCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();
				
			}
		private void initiatePopupWindow(){
			try {
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.activity_popup_fracture,(ViewGroup)findViewById(R.id.popup_element));
				
				pwindow = new PopupWindow(layout, 350, 400, true);
				pwindow.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				pwindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
				
				btnClose = (Button)layout.findViewById(R.id.btnClose);
				btnClose.setOnClickListener(cancel_button_click_listener);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		private OnClickListener cancel_button_click_listener = new OnClickListener() {
			
			public void onClick(View v) {
			pwindow.dismiss();	
			}
		  };
      });    
        
        btnCategory = (Button)findViewById(R.id.btnBurn);
        btnCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();
				
			}
		private void initiatePopupWindow(){
			try {
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.activity_popup_burn,(ViewGroup)findViewById(R.id.popup_element));
				
				pwindow = new PopupWindow(layout, 350, 400, true);
				pwindow.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				pwindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
				
				btnClose = (Button)layout.findViewById(R.id.btnClose);
				btnClose.setOnClickListener(cancel_button_click_listener);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		private OnClickListener cancel_button_click_listener = new OnClickListener() {
			
			public void onClick(View v) {
			pwindow.dismiss();	
			}
		  };
      });   
       
        btnCategory = (Button)findViewById(R.id.btnPoison);
        btnCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();
				
			}
		private void initiatePopupWindow(){
			try {
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.activity_popup_poison,(ViewGroup)findViewById(R.id.popup_element));
				
				pwindow = new PopupWindow(layout, 350, 400, true);
				pwindow.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				pwindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
				
				btnClose = (Button)layout.findViewById(R.id.btnClose);
				btnClose.setOnClickListener(cancel_button_click_listener);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		private OnClickListener cancel_button_click_listener = new OnClickListener() {
			
			public void onClick(View v) {
			pwindow.dismiss();	
			}
		  };
      });          
    }
    public void btnHeart1 (View Heart1)
    {
    	Intent TopicHeart1 = new Intent (this, TopicHeart1Activity.class);
    	startActivity(TopicHeart1);
    }
    public void btnHeart2 (View Heart2)
    {
    	Intent TopicHeart2 = new Intent (this, TopicHeart2Activity.class);
    	startActivity(TopicHeart2);
    }
    public void btnHeart3 (View Heart3)
    {
    	Intent TopicHeart3 = new Intent (this, TopicHeart3Activity.class);
    	startActivity(TopicHeart3);
    }
    public void btnFracture1 (View Fracture1)
    {
    	Intent TopicFracture1 = new Intent (this, TopicFracture1Activity.class);
    	startActivity(TopicFracture1);
    }
    public void btnFracture2 (View Fracture2)
    {
    	Intent TopicFracture2 = new Intent (this, TopicFracture2Activity.class);
    	startActivity(TopicFracture2);
    }
    public void btnFracture3 (View Fracture3)
    {
    	Intent TopicFracture3 = new Intent (this, TopicFracture3Activity.class);
    	startActivity(TopicFracture3);
    }
    public void btnBurn1 (View Burn1)
    {
    	Intent TopicBurn1 = new Intent (this, TopicBurn1Activity.class);
    	startActivity(TopicBurn1);
    }
    public void btnBurn2 (View Burn2)
    {
    	Intent TopicBurn2 = new Intent (this, TopicBurn2Activity.class);
    	startActivity(TopicBurn2);
    }
    public void btnBurn3 (View Burn3)
    {
    	Intent TopicBurn3 = new Intent (this, TopicBurn3Activity.class);
    	startActivity(TopicBurn3);
    }
    public void btnPoison1 (View Poison1)
    {
    	Intent TopicPoison1 = new Intent (this, TopicPoison1Activity.class);
    	startActivity(TopicPoison1);
    }
    public void btnPoison2 (View Poison1)
    {
    	Intent TopicPoison2 = new Intent (this, TopicPoison2Activity.class);
    	startActivity(TopicPoison2);
    }
    public void btnPoison3 (View Poison1)
    {
    	Intent TopicPoison3 = new Intent (this, TopicPoison3Activity.class);
    	startActivity(TopicPoison3);
    }
    public void btnFeedback (View Feedback)
    {
    	Intent FeedbackPage = new Intent (this, FeedbackActivity.class);
    	startActivity(FeedbackPage);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
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
    
    private void loadDBAndData(){
    	MainActivityDAO mainActivityDAO = new MainActivityDAO(this.getBaseContext());
        mainActivityDAO.openDB();
    	
        //Check if table already exists. If it does not, then create it.
        if (!mainActivityDAO.isTablePopulated("TB_DISEASE")){
	        //If there are no errors in sending SMS, now save the record on DB
	        DiseaseModel diseaseModel = new DiseaseModel();
	        DiseaseDAO diseaseDAO = new DiseaseDAO(this.getBaseContext());
	        
	        //Heart Attack
	        String[] diseaseArray= getResources().getStringArray(R.array.heart_attack_array);
	        
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicHeart1Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("heart,disease,attack,heart attack");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        int i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Heart -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        Log.d("Heart OID :: ", i + "");
	        
	        //Hypertension
	        diseaseArray = getResources().getStringArray(R.array.hypertension_array);
	        
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicHeart2Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("hypertension,high blood,high,blood");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("HB -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Asthma
	        diseaseArray = getResources().getStringArray(R.array.asthma_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicHeart3Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("asthma,wheezing,asthma attack");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Asthma -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Sprain
	        diseaseArray = getResources().getStringArray(R.array.sprain_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicFracture1Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("sprain,pain,foot");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Sprain -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Muscle Cramp
	        diseaseArray = getResources().getStringArray(R.array.muscle_cramp_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicFracture2Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("pain,stiff,stiffness");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Muscle Cramp -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Fractured Bone
	        diseaseArray = getResources().getStringArray(R.array.fractured_bone_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicFracture3Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("pain,break,broken,bone,fracture");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Fractured Bone -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Burns
	        diseaseArray = getResources().getStringArray(R.array.burn_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicBurn1Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("pain,burns,burn,hot,boil,boiling");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Fractured Bone -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Sunburn
	        diseaseArray = getResources().getStringArray(R.array.sunburn_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicBurn2Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("pain,burn,burns,sunburn");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Fractured Bone -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Heat Stroke
	        diseaseArray = getResources().getStringArray(R.array.heat_stroke_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicBurn3Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("heat,stroke,heat stroke,hot,pain,breathing,breath");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Fractured Bone -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Food Poisoning
	        diseaseArray = getResources().getStringArray(R.array.food_poisoning_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicPoison1Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("pain,poison,food,food poison,food poisoning");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("FFood Poisoning -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Overdose
	        diseaseArray = getResources().getStringArray(R.array.overdose_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicPoison2Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("drug,overdose,drug overdose");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Overdose -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
	        
	        //Toxin
	        diseaseArray = getResources().getStringArray(R.array.toxin_array);
	    	
	        diseaseModel.setStrDisease(diseaseArray[0]);
	        diseaseModel.setStrClassName("com.cornell.firstaid.TopicPoison3Activity");
	        diseaseModel.setiDesc(1);
	        diseaseModel.setiSymp(2);
	        diseaseModel.setiTreat(3);
	        diseaseModel.setStrTags("vomit,pain,toxic,toxin,chemical");
	        
	        diseaseDAO.insertDisease(diseaseModel);
	        i = diseaseDAO.getOidFromTable(diseaseArray[0]);
	        Log.d("Toxin -- ", diseaseArray[0]);
	        diseaseModel.setiDiseaseOid(i);
	        diseaseDAO.insertDiseaseDescSympTreat(diseaseModel);
        }
        
        //Check if table already exists. If it does not, then create it.
        if (!mainActivityDAO.isTablePopulated("TB_DEVELOPER_DETAILS")){
       
	        //Developer Details
	        String[] developerArray = getResources().getStringArray(R.array.alan_developer_array);
	        DeveloperModel devModel = new DeveloperModel();
	        devModel.setStrDevName(developerArray[0]);
	        devModel.setiDevAddress(1);
	        devModel.setiDevEmail(2);
	        devModel.setStrDevPhone(developerArray[3]);
	        mainActivityDAO.insertDeveloperDetails(devModel);
	
	        developerArray = getResources().getStringArray(R.array.rhiza_developer_array);
	        devModel.setStrDevName(developerArray[0]);
	        devModel.setiDevAddress(1);
	        devModel.setiDevEmail(2);
	        devModel.setStrDevPhone(developerArray[3]);
	        mainActivityDAO.insertDeveloperDetails(devModel);
        }
    }
}

