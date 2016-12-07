package com.example.alien.im.fragment;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.im.BR;
import com.example.alien.im.R;
import com.example.alien.im.entity.Model;
import com.example.alien.im.main.LoginActivity;
import com.example.alien.im.utils.ActivityUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Alien on 2016/12/6.
 */

public class SettingFragment extends Fragment {
    private ViewDataBinding binding;
    private ActivityUtils activityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.settingfragment, container, false);
        activityUtils=new ActivityUtils(getActivity());
        binding.setVariable(BR.operation,new Operation());
        return  binding.getRoot();
    }
    public class Operation{
        public void logOutClick(){
            Model.getInstance().getService().execute(new Runnable() {
                @Override
                public void run() {
                    EMClient.getInstance().logout(true, new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getActivity().finish();
                                    activityUtils.startActivity(LoginActivity.class);
                                }
                            });
                        }

                        @Override
                        public void onError(int i, final String s) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast("退出失败！"+s);
                                }
                            });
                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                }
            });
        }
    }
}
