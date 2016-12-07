package com.example.alien.im.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.alien.im.R;
import com.example.alien.im.databinding.ActivityRegisterBinding;
import com.example.alien.im.entity.Model;
import com.example.alien.im.utils.ActivityUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.adapter.EMAChatClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private String name;
    private String pw;
    private String mPw;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        binding.setPresenter(new Presenter());
    }
    public class Presenter{
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            name=binding.rgUsername.getText().toString();

            pw=binding.rgUserpw.getText().toString();

            mPw=binding.rgUserconfirmpw.getText().toString();

        }
        public void registerOnclick(){
            if (verifyName()==true&&verifyPw()==true){
                Model.getInstance().getService().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(name,pw);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast("注册成功！");
                                    activityUtils.startActivity(LoginActivity.class);
                                }
                            });
                        } catch (final HyphenateException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast("注册失败！"+e.toString());
                                }
                            });
                        }
                    }
                });
            }
        }
        public boolean verifyName(){
            boolean flag = false;
            if (name!=null&&name.length()>0)
                flag= true;
            return  flag;
        }
        public boolean verifyPw(){
            boolean flag=false;
            if (pw!=null&&pw.length()>0&&mPw.equals(pw)==true)
                flag=true;
            return flag;
        }
    }
}
