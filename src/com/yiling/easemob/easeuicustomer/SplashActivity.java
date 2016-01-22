package com.yiling.easemob.easeuicustomer;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_splash);
		super.onCreate(savedInstanceState);

		RelativeLayout mLayout=(RelativeLayout)findViewById(R.id.mLayout);
		AlphaAnimation mAnimation=new AlphaAnimation(1.0f, 0.0f);
		mAnimation.setDuration(2000);
		mLayout.startAnimation(mAnimation);
		
//		Threa
	}

}
