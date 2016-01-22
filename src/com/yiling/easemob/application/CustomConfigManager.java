package com.yiling.easemob.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class CustomConfigManager{
	

//	public String customCurentPassword;
//	public String easemobAppkey,easemobIM;
//	public String customnickname;
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor mSharedEditor;
	private String TAG="CustomConfigManager";
	public static CustomConfigManager mCustomConfigManager;
		
	public  CustomConfigManager(Context context){
		mSharedPreferences=context.getSharedPreferences(CustomConfig.MPreferencceName,Context.MODE_PRIVATE);
		mSharedEditor=mSharedPreferences.edit();
	}
	
	public synchronized static void init(Context mConetx){
		mCustomConfigManager=new CustomConfigManager(mConetx);
	}
	
	public synchronized static CustomConfigManager getInstance(){	
		if(mCustomConfigManager==null)
		{
			throw new RuntimeException("please init first");
		}
		return mCustomConfigManager;
	}
	
	public void setCurrentUsername(String customCurrentName){
		mSharedEditor.putString(CustomConfig.CUSTOM_CURRENT_NAME, customCurrentName);
		Log.i(TAG,"set customCurrentName:"+customCurrentName);
		mSharedEditor.commit();
	}
	public String getCurrentUsername(){	
		Log.i(TAG,"get customCurrentName:"+mSharedPreferences.getString(CustomConfig.CUSTOM_CURRENT_NAME, ""));
		return mSharedPreferences.getString(CustomConfig.CUSTOM_CURRENT_NAME, "");
	}
	public void setCurrentAppkey(String customappkey){
		mSharedEditor.putString(CustomConfig.EASEMOB_APPKEY, customappkey);
		mSharedEditor.commit();
	}
	public String getCurrentAppkey(){	
		return mSharedPreferences.getString(CustomConfig.EASEMOB_APPKEY, "");
	}
	public void setCurrentIM(String customim){
		mSharedEditor.putString(CustomConfig.EASEMOB_IM, customim);
		mSharedEditor.commit();
	}
	public String getCurrentIM(){	
		return mSharedPreferences.getString(CustomConfig.EASEMOB_IM, "");
	}
	public void setFLAG(Boolean flag){
		mSharedEditor.putBoolean(CustomConfig.FLAG_, flag);
		mSharedEditor.commit();
	}
	public Boolean getFLAG(){	
		return mSharedPreferences.getBoolean(CustomConfig.FLAG_, true);
	}
	public void setCurrentpaaword(String customim){
		mSharedEditor.putString(CustomConfig.CURRENT_USER_PASSWORD, customim);
		mSharedEditor.commit();
	}
	public String getCurentpassword(){	
		return mSharedPreferences.getString(CustomConfig.CURRENT_USER_PASSWORD, "");
	}
}
