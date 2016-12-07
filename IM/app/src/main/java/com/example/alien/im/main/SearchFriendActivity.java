package com.example.alien.im.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alien.im.R;
import com.example.alien.im.entity.Model;
import com.example.alien.im.utils.ActivityUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class SearchFriendActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private TextView resultName;
    private LinearLayout result;
    private Button btnAdd;
    private String name;
    private ActivityUtils activityUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        setContentView(R.layout.activity_search_friend);
        editText= (EditText) findViewById(R.id.et_search_contact);
        textView= (TextView) findViewById(R.id.tv_search);
        result= (LinearLayout) findViewById(R.id.ll_add);
        resultName= (TextView) findViewById(R.id.tv_result_name);
        btnAdd= (Button) findViewById(R.id.btn_add);
        textView.setOnClickListener(searchListener);
        btnAdd.setOnClickListener(addListener);
    }
    private View.OnClickListener searchListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            searchContact();
        }
    };
    //查找联系人
    public void searchContact(){
        //获取并判断要查找的用户名
        name=editText.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            activityUtils.showToast("请输入要查找的用户名");
            return;
        }
        //如果由服务器，可以去自家服务器查找
        //显示查找结果
        result.setVisibility(View.VISIBLE);
        resultName.setText(name);
    }
    private View.OnClickListener addListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addContact();
        }
    };
    //添加联系人
    private void addContact(){
        Model.getInstance().getService().execute(new Runnable() {
            @Override
            public void run() {
                String reson="我想添加你为好友";//添加对方为好友的原因
                try {
                    EMClient.getInstance().contactManager().addContact(name,reson);//发送添加好友请求
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activityUtils.showToast("添加好友请求已发送，等待对方验证...");
                        }
                    });

                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activityUtils.showToast("添加好友请求失败"+e.toString());
                        }
                    });

                }
            }
        });
    }
    public void goBack(View v){
        finish();
    }
}
