package com.yiling.easemob.chatrow;


import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.exceptions.EaseMobException;
import com.yiling.easemob.application.CustomConfig;
import com.yiling.easemob.application.CustomHelper;
import com.yiling.easemob.easeuicustomer.R;
import com.yiling.easemob.messagehelper.TrackMessageEntity;

public class ChatRowTrack extends EaseChatRow{

	private TextView title,price,desc;
	private ImageView mImageView;

	public ChatRowTrack(Context context, EMMessage message, int position,
			BaseAdapter adapter) {
		super(context, message, position, adapter);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInflatView() {
		// TODO Auto-generated method stub
		if (CustomHelper.getInstance().isTRACKTxtMessage(message)){
            inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ?
                    R.layout.custom_track_message_receive: R.layout.custom_track_message_send, this);
        Log.i(TAG,"轨迹加载布局");
		}else if (CustomHelper.getInstance().isPictureTxtMessage(message)){
        }
	}

	@Override
	protected void onFindViewById() {
		// TODO Auto-generated method stub
		 title = (TextView) findViewById(R.id.mytitle);
		 price= (TextView) findViewById(R.id.price);
		 desc= (TextView) findViewById(R.id.desc);
		 mImageView = (ImageView) findViewById(R.id.trackimage);
	}

	@Override
	protected void onUpdateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSetUpView() {
		// TODO Auto-generated method stub
		
		JSONObject mjson = null;
		try {
			mjson = message.getJSONObjectAttribute(CustomConfig.MESSAGE_ATTR_MSGTYPE);
		} catch (EaseMobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (CustomHelper.getInstance().isTRACKTxtMessage(message)){
			TrackMessageEntity mTrackMessageEntity=TrackMessageEntity.getEntityFromJSONObject(mjson);
		       title.setText(mTrackMessageEntity.getTitle());	
		       price.setText(mTrackMessageEntity.getPrice());	
		       desc.setText(mTrackMessageEntity.getDesc());
		       Glide.with(context).load(mTrackMessageEntity.getImgUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.customer).into(mImageView);
//		       JingleIQProvider.with(getContext()).load(mTrackMessageEntity.getImgUrl()).placeholder(R.drawable.customer).into(mImageView);
        }else if (CustomHelper.getInstance().isPictureTxtMessage(message)){
        }
	
	} 

	@Override
	protected void onBubbleClick() {
		// TODO Auto-generated method stub
		
	}

}
