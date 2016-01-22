package com.yiling.easemob.easeuicustomer;

import android.content.Intent;
import android.os.Bundle;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseBaseFragment;
import com.easemob.easeui.ui.EaseChatFragment;

public class ChatActivity extends EaseBaseActivity{
private String tochatusername;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_chat);
		tochatusername=getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
		ChatFragment mChatFragment=new ChatFragment();
//		EaseChatFragment mChatFragment=new EaseChatFragment();
		mChatFragment.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.container, mChatFragment).commit();
		
		
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		 // 点击notification bar进入聊天页面，保证只有一个聊天页面
		if(tochatusername.equals(intent.getStringExtra(EaseConstant.EXTRA_USER_ID))){
			super.onNewIntent(intent);
		}else{
			finish();
			startActivity(intent);
		}
	}
	
	

}
