package com.example.alien.im;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alien on 2016/11/10.
 */

public class IMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options=new EMOptions();
        //false：设置接受好友邀请需要验证 true: 不需要验证
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this,options);
    }
}
