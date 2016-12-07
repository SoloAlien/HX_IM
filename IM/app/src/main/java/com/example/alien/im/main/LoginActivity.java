package com.example.alien.im.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.alien.im.R;
import com.example.alien.im.entity.Model;
import com.example.alien.im.utils.ActivityUtils;
import com.example.alien.im.databinding.ActivityLoginBinding;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ActivityUtils activityUtils;
    private String loginName;
    private String loginPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setPresenter(new Presenter());


    }
    public class Presenter{
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            loginName=binding.etName.getText().toString().trim();

            loginPw=binding.etPw.getText().toString().trim();

            Log.e("TAG", "onTextChanged-loginName: "+ loginName);
            Log.e("TAG", "onTextChanged-loginPw: "+ loginPw);
        }
        public void onBtnClick(){
            if (loginName!=null&&loginPw!=null){
                Model.getInstance().getService().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().login(loginName, loginPw, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityUtils.showToast("登陆成功!");
                                        activityUtils.startActivity(MainActivity.class);
                                    }
                                });
                            }

                            @Override
                            public void onError(int i, final String s) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                activityUtils.showToast("登陆失败!"+s);
                                            }
                                        });
                            }

                            @Override
                            public void onProgress(int i, String s) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityUtils.showToast("登陆中...");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }

    }
}
