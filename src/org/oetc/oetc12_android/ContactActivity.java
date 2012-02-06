package org.oetc.oetc12_android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ContactActivity extends Activity {
	
	private static final String[] names = {"Customer Service", 
								"Executive Director's Office",
								"Communications & Media Relations",
								"Educational Technology Conference",
								"Fiscal",
								"Human Resources",
								"Grants & Instructional Resources and Materials",
								"Procurement","Professional Development"};
	
	private static final String[] numbers = {"614-485-6650",
											 "614-485-6032",
											 "614-485-6000",
											 "614-485-6016",
											 "614-485-6000",
											 "614-485-6046",
											 "614-485-6000",
											 "614-485-6048"};
	
	ListView listContact;
	
	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	SimpleAdapter adapEntries;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		Integer n;
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contact);
		
		// Add items to the HashMap
		for(n = 0; n < names.length; n++){
			AddEntry(names[n], numbers[n]);
		}
		
		adapEntries = RefreshList();
		
		listContact = (ListView)findViewById(R.id.listContact);
		listContact.setAdapter(adapEntries);
	}
	
	public void myCallHandler(View v){
		// Get the row the clicked button is in
		LinearLayout vwParentRow = (LinearLayout)v.getParent();
		LinearLayout vwTwoRow = (LinearLayout)vwParentRow.getChildAt(1);
		TextView childText = (TextView)vwTwoRow.getChildAt(0);
		TextView childNumber = (TextView)vwTwoRow.getChildAt(1);
		
		try{
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+childNumber.getText().toString()));
			startActivity(intent);
		} catch (Exception e) {
			Log.e("ContactActivity","Calling "+ childText.getText().toString(), e);
		}
	}
	
	
	public void AddEntry(String name, String number){
		
		HashMap<String,String> newitem = new HashMap<String,String>();
		newitem.put("names", name);
		newitem.put("numbers", number);
		
		list.add(newitem);
	}
	
	
	public SimpleAdapter RefreshList(){
		SimpleAdapter adapEntries = new SimpleAdapter(
				this,
				list,
				R.layout.row,
				new String[] { "names", "numbers"},
				new int[] { R.id.textContactName, R.id.textNumber}
				);
		
		return adapEntries;
	}

}
