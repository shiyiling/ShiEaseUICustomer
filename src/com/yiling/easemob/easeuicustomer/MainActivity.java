package com.yiling.easemob.easeuicustomer;

import java.util.Random;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.exceptions.EaseMobException;
import com.yiling.easemob.application.CustomConfig;
import com.yiling.easemob.application.CustomConfigManager;
import com.yiling.easemob.easeuicustomer.LoginActivity.creatAccroutTASK;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;



public class MainActivity extends Activity implements OnClickListener{
ImageButton mpicButton1,mpicButton2;
private String TAG="MainActivity";
private String tochatUserName;
private String currentUsername;
private String currentPassword;
private creatAccroutTASK creatAccroutTASK;
private Button mConnectkf;
private Button mSettingkf;
private int Main_CHAT=3;
private int PIC1_CHAT=1;
private int PIC2_CHAT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpicButton1=(ImageButton)findViewById(R.id.pic1);
        mpicButton2=(ImageButton)findViewById(R.id.pic2);
        mConnectkf=(Button)findViewById(R.id.directtochat);
        mSettingkf=(Button)findViewById(R.id.setting);
        mpicButton1.setOnClickListener(this);
        mpicButton2.setOnClickListener(this);
        mSettingkf.setOnClickListener(this);
        mConnectkf.setOnClickListener(this);
        
        if(TextUtils.isEmpty(CustomConfigManager.getInstance().getCurrentIM()))
        {
        	Log.i(TAG,"IM is null!");
        	tochatUserName=CustomConfig.DEFALUT_IM;
        	Log.i(TAG,"IM tochatUserName:"+tochatUserName);
        }else{
        	tochatUserName=CustomConfigManager.getInstance().getCurrentIM();
        	Log.i(TAG,"IM tochatUserName:"+tochatUserName);
        }
        
    }


   

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {	
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
    }


	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(featureId)
		{case R.id.action_settings:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, SettingActivity.class);
			startActivity(intent);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.pic1:
			Intent intent=new Intent();
			intent.putExtra(EaseConstant.EXTRA_USER_ID,tochatUserName);
			intent.putExtra(CustomConfig.FROM_WITCH_CHAT,PIC1_CHAT);
			intent.setClass(MainActivity.this,ChatActivity.class);
			startActivity(intent);
			
			break;
		case R.id.pic2:
			Intent intent2=new Intent();
			intent2.putExtra(EaseConstant.EXTRA_USER_ID,tochatUserName);
			intent2.putExtra(CustomConfig.FROM_WITCH_CHAT,PIC2_CHAT);
			intent2.setClass(MainActivity.this,ChatActivity.class);
			startActivity(intent2);
			break;
		case R.id.directtochat:
			Intent intent3=new Intent();
			intent3.putExtra(EaseConstant.EXTRA_USER_ID,tochatUserName);
			intent3.putExtra(CustomConfig.FROM_WITCH_CHAT,Main_CHAT);
			intent3.setClass(MainActivity.this,ChatActivity.class);
			startActivity(intent3);
			break;
		case	R.id.setting:
			Intent intent4=new Intent();
			intent4.setClass(MainActivity.this, SettingActivity.class);
			startActivity(intent4);
			break;
		}
	}

}
