package org.oetc.oetc12_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Oetc12_androidActivity extends Activity {
	private Button btnContact;
	private Button btnRss;
	private Button btnWeb;
	
	protected ProgressDialog dialog;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnContact = (Button) findViewById(R.id.buttonContact);
        btnContact.setOnClickListener((OnClickListener) this);
        
        btnRss = (Button) findViewById(R.id.buttonRss);
        btnRss.setOnClickListener((OnClickListener) this);
        
        btnWeb = (Button) findViewById(R.id.buttonWeb);
        btnWeb.setOnClickListener((OnClickListener) this);
        
        dialog = new ProgressDialog(Oetc12_androidActivity.this);
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(true);
    }
    
    public void onClick(View v)
    {
    	switch(v.getId()){
    	case R.id.buttonWeb:
    		Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.etech.ohio.gov"));
    		startActivity(web);
    	}
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	dialog.dismiss();
    }
}