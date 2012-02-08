package org.oetc.oetc12_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Oetc12_androidActivity extends Activity implements OnClickListener {
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
        btnContact.setOnClickListener(this);
        
        btnRss = (Button) findViewById(R.id.buttonRss);
        btnRss.setOnClickListener(this);
        
        btnWeb = (Button) findViewById(R.id.buttonWeb);
        btnWeb.setOnClickListener(this);
        
        dialog = new ProgressDialog(Oetc12_androidActivity.this);
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(true);
    }
    
    public void onClick(View v)
    {
    	switch(v.getId()){
	    	case R.id.buttonWeb:
	    		Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.guidebook.com/660/"));
	    		startActivity(web);
	    		break;
	    		
	    	case R.id.buttonContact:
		        try {
					startActivity(new Intent(this, ContactActivity.class));
				} catch (Exception e) {
					Log.e("NACSActivity","Could not start activity: "+ e);
					e.printStackTrace();
				}
				break;
				
    	case R.id.buttonRss:
			try {
				dialog.show();
				startActivity(new Intent(this, RssActivity.class));
			} catch (Exception e) {
				Log.e("NACSActivity","Could not start activity: "+ e);
				e.printStackTrace();
			}
			break;
    	}
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	dialog.dismiss();
    }
}