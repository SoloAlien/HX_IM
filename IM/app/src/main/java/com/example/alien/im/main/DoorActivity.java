package com.example.alien.im.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alien.im.R;
import com.example.alien.im.utils.ActivityUtils;
import com.example.alien.im.databinding.ActivityDoorBinding;

public class DoorActivity extends AppCompatActivity {
    ActivityDoorBinding binding;
    ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_door);
//        setContentView(R.layout.activity_door);
        binding.setPresenter(new Presenter());
    }
    public class Presenter{
        public void onRegisterClick(){
            activityUtils.startActivity(RegisterActivity.class);
            finish();
        }
        public void onLoginClick(){
            activityUtils.startActivity(LoginActivity.class);
            finish();
        }
    }
}
