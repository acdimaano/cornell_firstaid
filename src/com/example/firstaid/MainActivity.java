package com.example.firstaid;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;


public class MainActivity extends ActionBarActivity {
	Button Create;
	Button close;
	PopupWindow pwindow;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Create = (Button)findViewById(R.id.btnHeart);
        Create.setOnClickListener(new OnClickListener() {
			
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
				
				close = (Button)layout.findViewById(R.id.btnClose);
				close.setOnClickListener(cancel_button_click_listener);
				
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
    

        Create = (Button)findViewById(R.id.btnFracture);
        Create.setOnClickListener(new OnClickListener() {
			
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
				
				close = (Button)layout.findViewById(R.id.btnClose);
				close.setOnClickListener(cancel_button_click_listener);
				
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
        
        Create = (Button)findViewById(R.id.btnBurn);
        Create.setOnClickListener(new OnClickListener() {
			
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
				
				close = (Button)layout.findViewById(R.id.btnClose);
				close.setOnClickListener(cancel_button_click_listener);
				
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
       
        Create = (Button)findViewById(R.id.btnPoison);
        Create.setOnClickListener(new OnClickListener() {
			
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
				
				close = (Button)layout.findViewById(R.id.btnClose);
				close.setOnClickListener(cancel_button_click_listener);
				
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	switch (item.getItemId())
    	{
    	case R.id.action_phonecall:
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    
    
}

