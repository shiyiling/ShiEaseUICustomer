package com.yiling.easemob.chatrow;


import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.exceptions.EaseMobException;
import com.yiling.easemob.easeuicustomer.R;

public class ChatRowRedMoney extends EaseChatRow{

	private TextView title,price,regards;
	private ImageView mImageView;

	public ChatRowRedMoney(Context context, EMMessage message, int position,
			BaseAdapter adapter) {
		super(context, message, position, adapter);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInflatView() {
		// TODO Auto-generated method stub
		//红包加载布局
		if (message.getBody().toString().contains("[环信红包]")){
            inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ?
                    R.layout.custom_redmoney_message_receive: R.layout.custom_redmoney_message_send, this);
		}
	}

	@Override
	protected void onFindViewById() {
		// TODO Auto-generated method stub
		 price= (TextView) findViewById(R.id.price);
		 regards= (TextView) findViewById(R.id.regards);
		 mImageView = (ImageView) findViewById(R.id.trackimage);
	}

	@Override
	protected void onUpdateView() {
		// TODO Auto-generated method stub
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onSetUpView() {
		// TODO Auto-generated method stub
		if (message.getBody().toString().contains("[环信红包]")){
			try {
				price.setText(message.getStringAttribute("money"));
				regards.setText(message.getStringAttribute("regards"));
			} catch (EaseMobException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
        }
	
	} 

	@Override
	protected void onBubbleClick() {
		// TODO Auto-generated method stub
		Log.i("lynn","hongbao点击事件");
		Toast.makeText(getContext(), "点击事件", Toast.LENGTH_SHORT).show();
	}

}
