package com.yiling.easemob.easeuicustomer;

import org.json.JSONObject;

import android.animation.ArgbEvaluator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.R.color;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.easeui.widget.EaseChatInputMenu;
import com.easemob.easeui.widget.EaseChatMessageList;
import com.easemob.easeui.widget.EaseChatPrimaryMenu;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.yiling.easemob.application.CustomConfig;
import com.yiling.easemob.application.CustomHelper;
import com.yiling.easemob.chatrow.ChatRowRedMoney;
import com.yiling.easemob.chatrow.ChatRowTrack;
import com.yiling.easemob.messagehelper.MessageHelper;
import com.easemob.easeui.ui.EaseChatFragment.EaseChatFragmentListener;

public class ChatFragment extends EaseChatFragment implements EaseChatFragmentListener{

	private final static int ITEM_AlAWAYS_TALK=20;
	private final static int REQUEST_CODE_SELECT_ALWAYS=21;
	private static String TAG="ChatFragment";
	private static final int RESULT_OK=-1;
	private String  tochatuserid;
	private int witchCHAT;
	private int PIC1_CHAT=1;
	private int PIC2_CHAT=2;
	private int Main_CHAT=3;
	private static int RESULT_OK_SEND_REDMONEY=31;
	private final static int ITEM_RED_MONEY=21;
	private static final int NOTIFY_GET_PAYED_MONEY=2;
	private String isFirstat;
	private Handler mHandle;
	ProgressDialog mProgressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		setChatFragmentListener(this);
        // 消息列表layout
        messageList = (EaseChatMessageList) getView().findViewById(R.id.message_list);
        messageList.setShowUserNick(true);
        messageList.setBackgroundResource(R.drawable.splash);
        
        titleBar = (EaseTitleBar) getView().findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(Color.RED);
        
		super.onActivityCreated(savedInstanceState);
		tochatuserid=(String)getArguments().get(EaseConstant.EXTRA_USER_ID);
		witchCHAT =(Integer) getArguments().get(CustomConfig.FROM_WITCH_CHAT);
//		if(witchCHAT!=Main_CHAT)
//		{
//		if(savedInstanceState==null){
//			Log.i(TAG,"进入发发轨迹消息界面"+witchCHAT);
//			sendPictureTxtMessage();
//		}
//		}
	}
	
	  @Override
	protected void setUpView() {
		// TODO Auto-generated method stub
		setChatFragmentListener(this);
		super.setUpView();
	}

	/**
     * 演示功能：
     * 
     * 从商品详情界面进入会话，自动发一条订单或轨迹消息。
     * 
     * @param selectedImgIndex 选中的某个商品，用户要按照自己的需求传递。
     * 
     */
    private void sendPictureTxtMessage(){
    	
		EMMessage message = EMMessage.createTxtSendMessage("客服图文混排消息", toChatUsername);
		JSONObject jsonMsgType = MessageHelper.getMessageExtFromPicture(witchCHAT);
		if(jsonMsgType != null){
			message.setAttribute("msgtype", jsonMsgType);
			sendMessage(message);
		}
    }
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		
	}

	@Override
	protected void registerExtendMenuItem() {
		// TODO Auto-generated method stub
		super.registerExtendMenuItem();
		 inputMenu.registerExtendMenuItem("常用语", R.drawable.ease_chat_face_normal, ITEM_AlAWAYS_TALK, extendMenuItemClickListener);
		 inputMenu.registerExtendMenuItem("红包", R.drawable.ease_chat_face_normal, ITEM_RED_MONEY, extendMenuItemClickListener);
	}

	@Override
	public void onSetMessageAttributes(EMMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnterToChatDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAvatarClick(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMessageBubbleClick(EMMessage message) {
		// TODO Auto-generated method stub
		if(message.getType()==EMMessage.Type.TXT)
		{
		if(message.getBody().toString().contains("[环信红包]"))
		{
			mProgressDialog=new ProgressDialog(getActivity());
			//这个监听方法也可以写在ChatRowRedMoney中的onBubbleClick()中可以实现， 此处就写返回false
			mProgressDialog.setTitle("抢红包");
			mProgressDialog.setMessage("抢红包....");
			mProgressDialog.show();
			
			 mHandle=new Handler(){
					

					
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
						switch(msg.what) 
						{
						case NOTIFY_GET_PAYED_MONEY:
							mProgressDialog.cancel();
							Intent intent=new Intent();
							intent.setClass(getActivity(), FetchRedMoney.class);
							startActivity(intent);
							break;
						}
					}
				};
				mHandle.sendEmptyMessageDelayed(NOTIFY_GET_PAYED_MONEY, 1000);
			return true;
		}
		}
		return false;
	}

	@Override
	public void onMessageBubbleLongClick(EMMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onExtendMenuItemClick(int itemId, View view) {
		// TODO Auto-generated method stub
		Log.i(TAG,"扩展点击事件进入");
		switch(itemId)
		{ 
		case ITEM_AlAWAYS_TALK: //常用语
			Log.i(TAG,"点击常用语相应");
	         Intent intent = new Intent(getActivity(), MyAlertDialog.class);
	         startActivityForResult(intent, REQUEST_CODE_SELECT_ALWAYS);
	         break;
		case ITEM_RED_MONEY: //红包
			 Log.i(TAG,"点击红包");
	         Intent intentred = new Intent(getActivity(), SendRedMoney.class);
	         startActivityForResult(intentred, RESULT_OK_SEND_REDMONEY);
	         break;
	         }
	
		return false;
	}

	@Override
	public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
		// TODO Auto-generated method stub
		return new MyEaseCustomChatRowProvider();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		 super.onActivityResult(requestCode, resultCode, data);
	        if (requestCode == REQUEST_CODE_SELECT_ALWAYS) {
	            switch (resultCode) {
	            case RESULT_OK:
	            String talkmessage=data.getStringExtra("talkitem");
	 		   EMMessage mytalkmessage=EMMessage.createTxtSendMessage(talkmessage, toChatUsername);
	 		   sendMessage(mytalkmessage);
	 		    break;
	 		default:
	 		    break;
	            }
		  
		    }else if(requestCode==RESULT_OK_SEND_REDMONEY)
		    {//紅包判斷，發送紅包界面返回
		    	 switch (resultCode) {
		            case RESULT_OK:
		            	String money=data.getStringExtra("money");	
		            	String regards=data.getStringExtra("regards");	
		 		  
		 		   EMMessage redmoneymessage=EMMessage.createTxtSendMessage("[环信红包]", toChatUsername);
		 		   redmoneymessage.setAttribute("money", money);
		 		   redmoneymessage.setAttribute("regards", regards);
		 		   sendMessage(redmoneymessage);
		 		    break;
		 		default:
		 		    break;
		            }
		    }

	}
public class MyEaseCustomChatRowProvider implements EaseCustomChatRowProvider{

	@Override
	public int getCustomChatRowTypeCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getCustomChatRowType(EMMessage message) {
		// TODO Auto-generated method stub
		if(message.getType()==EMMessage.Type.TXT)
		{
		if(CustomHelper.getInstance().isTRACKTxtMessage(message)){
			return message.direct==EMMessage.Direct.RECEIVE?CustomConfig.CUSTOM_TRACK_ROW_RECEIVER : (CustomConfig.CUSTOM_TRACK_ROW_SEND);
		}else if(message.getBody().toString().contains("[环信红包]"))
		{
			return message.direct==EMMessage.Direct.RECEIVE?CustomConfig.CUSTOM_REDMONEY_ROW_RECEIVER : (CustomConfig.CUSTOM_REDMONEY_ROW_SEND);
		}
		}
		return 0;
	}

	@Override
	public EaseChatRow getCustomChatRow(EMMessage message, int position,
			BaseAdapter adapter) {
		// TODO Auto-generated method stub
		if(message.getType()==EMMessage.Type.TXT)
		{
		if(CustomHelper.getInstance().isTRACKTxtMessage(message)){
			return new ChatRowTrack(getActivity(), message, position, adapter);
		}else if(message.getBody().toString().contains("[环信红包]")){
			return new ChatRowRedMoney(getActivity(), message, position, adapter);
		}
		}
		return null;
	}
	
}
}
