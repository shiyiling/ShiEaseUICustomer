package com.yiling.easemob.easeuicustomer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.yiling.easemob.application.CustomConfig;
import com.yiling.easemob.application.CustomConfigManager;

public class SettingActivity extends EaseBaseActivity {

    private TextView mAppkeyView;
    private TextView mIMCustomerView;
    private TextView mUserInfoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }



    /**
     * 初始化设置UI
     */
    private void initView() {
        mAppkeyView = (TextView) findViewById(R.id.text_setting_appkey);
        mIMCustomerView = (TextView) findViewById(R.id.text_setting_im_customer);
        mUserInfoView = (TextView) findViewById(R.id.text_setting_user_info);

        mAppkeyView.setText(CustomConfigManager.getInstance().getCurrentAppkey());
        mIMCustomerView.setText(CustomConfigManager.getInstance().getCurrentIM());


        mAppkeyView.setOnClickListener(viewListener);
        mIMCustomerView.setOnClickListener(viewListener);
        mUserInfoView.setOnClickListener(viewListener);
    }


    /**
     * 设置界面点击监听
     */
    private View.OnClickListener viewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_setting_appkey:
                    changeAppkey();
                    break;
                case R.id.text_setting_im_customer:
                    changeIMCustomer();
                    break;
                case R.id.text_setting_user_info:
                    changeUserInfo();
                    break;
            }
        }
    };

    /**
     * 改变Appkey
     */
    private void changeAppkey() {
//        Snackbar.make(mRootView, "EaseUI暂不支持动态设置Appkey", Snackbar.LENGTH_LONG).show();
        AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this);
        final EditText editText = new EditText(SettingActivity.this);
        editText.setHint("APPKEY");
        editText.setText(CustomConfigManager.getInstance().getCurrentAppkey());
        dialog.setTitle("更改appkey");
        dialog.setView(editText);
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	EMChat.getInstance().setAppkey(editText.getText().toString());
                // 保存新设置的IM关联号
                CustomConfigManager.getInstance().setCurrentAppkey(editText.getText().toString());
                Toast.makeText(SettingActivity.this, "保存appkey成功", Toast.LENGTH_SHORT).show();
                // 更新UI显示的appkey和IM关联号
                mAppkeyView.setText(CustomConfigManager.getInstance().getCurrentAppkey());
                mIMCustomerView.setText(CustomConfigManager.getInstance().getCurrentIM());
                EMChatManager.getInstance().logout(true);
                CustomConfigManager.getInstance().setFLAG(true);
//                System.exit(0);
            }
        });
        dialog.show();
    }

    /**
     * 改变客服关联账户
     */
    private void changeIMCustomer() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this);
        final EditText editText = new EditText(SettingActivity.this);
        editText.setHint("IM 服务号");
        editText.setText(CustomConfigManager.getInstance().getCurrentIM());
        dialog.setTitle("设置IM号");
        dialog.setView(editText);
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 保存新设置的IM关联号
                CustomConfigManager.getInstance().setCurrentIM(editText.getText().toString());
              
                Toast.makeText(SettingActivity.this, "关联客服号保存成功", Toast.LENGTH_LONG).show();

                // 更新UI显示的appkey和IM关联号
                mAppkeyView.setText(CustomConfigManager.getInstance().getCurrentAppkey());
                mIMCustomerView.setText(CustomConfigManager.getInstance().getCurrentIM());
            }
        });

        dialog.show();
    }

    /**
     * 修改账户信息
     */
    private void changeUserInfo() {
        Toast.makeText(SettingActivity.this, "暂未实现修改用户信息界面", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
