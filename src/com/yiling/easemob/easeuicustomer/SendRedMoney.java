package com.yiling.easemob.easeuicustomer;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SendRedMoney extends Activity implements OnClickListener{
	EditText moneyEditText,regardsEditText;
	Button sendredmoney;
	ProgressDialog mDialog;
	Handler mHandle;
	private static final int NOTIFY_HAS_PAYED_MONEY=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendred);
		
		moneyEditText=(EditText)findViewById(R.id.money);
		regardsEditText=(EditText)findViewById(R.id.regards);
		sendredmoney=(Button)findViewById(R.id.sendredmoney);
		sendredmoney.setOnClickListener(this);
		
		mDialog=new ProgressDialog(SendRedMoney.this);
		mDialog.setTitle("发送红包");
		mDialog.setMessage("判断支付宝是否登录，若登陆打开填写支付密码界面，点击确定，将调用接口红包转账到支付宝账户");
		
              mHandle=new Handler(){
			

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what) 
				{
				case NOTIFY_HAS_PAYED_MONEY:
					Intent intent=new Intent();
					intent.putExtra("money", moneyEditText.getText().toString().trim());
					intent.putExtra("regards", regardsEditText.getText().toString().trim());
					setResult(RESULT_OK,intent);
					finish();
					break;
				}
			}
		};
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.sendredmoney:
			if(TextUtils.isEmpty(moneyEditText.getText().toString().trim())||TextUtils.isEmpty(regardsEditText.getText().toString().trim()))
			{
				Toast.makeText(SendRedMoney.this, "不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			mDialog.show();
			mHandle.sendEmptyMessageDelayed(NOTIFY_HAS_PAYED_MONEY, 2500);
			
			
			break;
		}
		 
	}

}
