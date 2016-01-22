package com.yiling.easemob.easeuicustomer;

import java.util.Random;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.exceptions.EaseMobException;
import com.yiling.easemob.application.CustomConfig;
import com.yiling.easemob.application.CustomConfigManager;

public class LoginActivity extends EaseBaseActivity{

	private String currentUsername;
	private String currentPassword;
	private String TAG="LoginActivity1";
	private creatAccroutTASK creatAccroutTASK;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_splash);
		super.onCreate(arg0);
		RelativeLayout mLayout=(RelativeLayout)findViewById(R.id.mLayout);
		AlphaAnimation mAnimation=new AlphaAnimation(0.3f, 1.0f);
		mAnimation.setDuration(1000);
		mLayout.startAnimation(mAnimation);
		 
//		login();
	}

	 @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				login();
			}
		}).start();
//		login();
	}

	private void login() {
			// TODO Auto-generated method stub
	    	if(CustomConfigManager.getInstance().getFLAG())
	    	{
	    		// 自动生成账号
	    		currentUsername = getAccount();
	    		currentPassword = "123456";
	    		CustomConfigManager.getInstance().setCurrentUsername(currentUsername);
	    		CustomConfigManager.getInstance().setCurrentpaaword(currentPassword);
	    		creatAccroutTASK=new creatAccroutTASK();
	    		creatAccroutTASK.execute(currentUsername,currentPassword);
	    		Log.i(TAG,"自动生成账号："+currentUsername+":"+currentPassword);
	    	}else{
	    		currentUsername=CustomConfigManager.getInstance().getCurrentUsername();
	    		currentPassword=CustomConfigManager.getInstance().getCurentpassword();
	    		Log.i(TAG,"上次登录账号："+currentUsername+":"+currentPassword);
			if(EMChat.getInstance().isLoggedIn())
			{
				Log.i(TAG,"已经登录");
			    new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
						EMChatManager.getInstance().loadAllConversations();
						}catch(Exception e){
							Log.i(TAG,"加载会话："+e.toString());
							e.printStackTrace();
						}
					}
				}).start();
			    try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent =new Intent(this,MainActivity.class);
				startActivity(intent);
				finish();
			}else{
				EMChatManager.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
					
					

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						Log.i(TAG,"login success");
						CustomConfigManager.getInstance().setCurrentUsername(currentUsername);
			    		CustomConfigManager.getInstance().setCurrentpaaword(currentPassword);
			    		CustomConfigManager.getInstance().setFLAG(false);
								// TODO Auto-generated method stub
			    		try{
							EMChatManager.getInstance().loadAllConversations();
							}catch(Exception e){
								Log.i(TAG,"加载会话："+e.toString());
								e.printStackTrace();
							}
			    		 try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		Intent intent = new Intent(LoginActivity.this,
			    				MainActivity.class);
						startActivity(intent);
						finish();
						} 
							
					
					@Override
					public void onProgress(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i(TAG,"login progress");
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						final String err=arg1;
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(LoginActivity.this, "登陆失败"+err, Toast.LENGTH_SHORT).show();
						}
					});
						Log.i(TAG,"login erroe");
					}
				});
			}
	    	}
		}

		 public String getAccount() {
				String val = "";
				Random random = new Random();
				for (int i = 0; i < 10; i++) {
					String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
					if ("char".equalsIgnoreCase(charOrNum)) // 字符串
					{
						int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
						val += (char) (choice + random.nextInt(26));
					} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
					{
						val += String.valueOf(random.nextInt(10));
					}
				}
				return val.toLowerCase();
			}
		public class creatAccroutTASK extends AsyncTask<String, Void, String>{

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				String userid=arg0[0];
				String pwd=arg0[1];
				
				try {
					Log.i(TAG,"注册账户："+userid+":"+pwd);
					EMChatManager.getInstance().createAccountOnServer(userid, pwd);
					Log.i(TAG,"注册账户成功");
				} catch (EaseMobException e) {
					// TODO Auto-generated catch block
					final String erro=e.toString();
                  runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(LoginActivity.this, "注册失败"+erro, Toast.LENGTH_SHORT).show();
						}
					});
					e.printStackTrace();
					Log.i(TAG,"注册账户失败");
					CustomConfigManager.getInstance().setFLAG(true);
				}
				return userid;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
	EMChatManager.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
					
					

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						Log.i(TAG,"login success");
						CustomConfigManager.getInstance().setCurrentUsername(currentUsername);
			    		CustomConfigManager.getInstance().setCurrentpaaword(currentPassword);
			    		CustomConfigManager.getInstance().setFLAG(false);
								// TODO Auto-generated method stub
			    		try{
							EMChatManager.getInstance().loadAllConversations();
							}catch(Exception e){
								Log.i(TAG,"加载会话："+e.toString());
								e.printStackTrace();
							}
			    		Intent intent = new Intent(LoginActivity.this,
			    				MainActivity.class);
						startActivity(intent);
						finish();
						} 
							
					
					@Override
					public void onProgress(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i(TAG,"login progress");
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i(TAG,"login erroe");
						final String erro=arg1;
		                  runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(LoginActivity.this, "登陆失败"+erro, Toast.LENGTH_SHORT).show();
								}
							});
					}
				});
			
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}
			
		}  
}
