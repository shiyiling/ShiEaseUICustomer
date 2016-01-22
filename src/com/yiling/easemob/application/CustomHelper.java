package com.yiling.easemob.application;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.exceptions.EaseMobException;

public class CustomHelper {
	private static CustomHelper customhelper;
	private String TAG="CustomHelper";
	private CustomHelper(){
		
	}
	public synchronized static CustomHelper getInstance(){
		if(customhelper==null)
		{
			customhelper=new CustomHelper();
		}
		return customhelper;		
	}
	public void init(Context context){
		CustomConfigManager.init(context);
		String appkey=CustomConfigManager.getInstance().getCurrentAppkey().trim();
		Log.i(TAG,"APPKEY:"+appkey);
		if(appkey.equals(""))
		{
			Log.i(TAG,"the first install appkey,APPKEY is null!");
			EMChat.getInstance().setAppkey(CustomConfig.DEFALUT_APPKEY);
			Log.i(TAG,"set default appkey:!"+CustomConfig.DEFALUT_APPKEY);
		}else{
			EMChat.getInstance().setAppkey(appkey);	
			Log.i(TAG,"set appkey:!"+appkey);
		}
		EaseUI.getInstance().init(context);
		
	}
	 /**
     * 检测是否为订单消息或者为轨迹消息
     * @param message
     * @return
     */
    public boolean isPictureTxtMessage(EMMessage message){
    	JSONObject jsonObj = null;
    	try {
			jsonObj = message.getJSONObjectAttribute(CustomConfig.MESSAGE_ATTR_MSGTYPE);
		} catch (EaseMobException e) {
		}
    	if(jsonObj == null){
			return false;
		}
		if(jsonObj.has("order") || jsonObj.has("track")){
			return true;
		}
		return false;
    }
    /**
     * 检测是否为订单消息或者为轨迹消息
     * @param message
     * @return
     */
    public boolean isTRACKTxtMessage(EMMessage message){
    	JSONObject jsonObj = null;
    	try {
			jsonObj = message.getJSONObjectAttribute(CustomConfig.MESSAGE_ATTR_MSGTYPE);
		} catch (EaseMobException e) {
		}
    	if(jsonObj == null){
			return false;
		}
		if(jsonObj.has("track")){
			return true;
		}
		return false;
    }
    /**
     * 检测是否为订单消息或者为轨迹消息
     * @param message
     * @return
     */
    public boolean isORDERTxtMessage(EMMessage message){
    	JSONObject jsonObj = null;
    	try {
			jsonObj = message.getJSONObjectAttribute(CustomConfig.MESSAGE_ATTR_MSGTYPE);
		} catch (EaseMobException e) {
		}
    	if(jsonObj == null){
			return false;
		}
		if(jsonObj.has("order")){
			return true;
		}
		return false;
    }
}
