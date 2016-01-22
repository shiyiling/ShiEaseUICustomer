package com.yiling.easemob.application;



import android.app.Application;
import android.content.Context;
//import 

public class CustomApplication extends Application{
private static Context mContext;
private static CustomApplication mInstance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext=this;
		mInstance=this;
		CustomHelper.getInstance().init(mContext);
		
	}
	public static CustomApplication getInstance()
	{
		return mInstance;
		
	}
}
