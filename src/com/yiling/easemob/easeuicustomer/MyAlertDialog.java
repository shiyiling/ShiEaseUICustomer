package com.yiling.easemob.easeuicustomer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class MyAlertDialog extends Activity {
private ArrayList<String> alwaystalk=new ArrayList<String>();
String[] mTALK={
		"多久发货？","多大号码？","从哪里发货？","能便宜吗？"
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.always_talk);
		Log.i("MyAlertDialog","go in MyAlertDialog");
		ListView listview = (ListView) this.findViewById(R.id.listview);
		for(int i=0;i<mTALK.length;i++)
		{
			alwaystalk.add(mTALK[i]);
		}
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("talkitem", alwaystalk.get(arg2));
				Log.i("lynn","talkitem:"+alwaystalk.get(arg2));
			    setResult(RESULT_OK, intent);
			    finish();
			}
			
		});
		BaseAdapter maAdapter=new BaseAdapter() {
			
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				View view = arg1;
			      ViewHolder holder ;
			      if (view == null) {
			          view = LayoutInflater.from(MyAlertDialog.this).inflate(R.layout.section_list_item1, null);
			          holder = new ViewHolder();
			          holder.tv_name = (TextView)view.findViewById(R.id.item);
			          view.setTag(holder);
			      }
			      else
			      {
			          holder = (ViewHolder)view.getTag();
			      }
			      holder.tv_name.setText(alwaystalk.get(arg0));
			      final String talkitem=alwaystalk.get(arg0);
			      holder.tv_name.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						
					}
				});
				return view;
			}
			
			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return alwaystalk.get(arg0);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return alwaystalk.size();
			}
		};
		listview.setAdapter(maAdapter);
	}
	
	private class ViewHolder{
		TextView tv_name;
	}

}
