package com.example.alien.im.main;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.alien.im.R;
import com.example.alien.im.databinding.ActivityMainBinding;
import com.example.alien.im.fragment.PeopleFragment;
import com.example.alien.im.fragment.SettingFragment;
import com.example.alien.im.fragment.TalkingFragment;
import com.example.alien.im.main.pic.PicActivity;
import com.example.alien.im.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    ActionBarDrawerToggle drawerToggle;
    ActivityUtils activityUtils;
    private TalkingFragment talkingFragment;
    private PeopleFragment peopleFragment;
    private SettingFragment settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityUtils=new ActivityUtils(this);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setLogo(R.mipmap.ic_launcher);

        //设置navigationview的条目选中监听
        binding.mainNavigate.setNavigationItemSelectedListener(this);
        drawerToggle=new ActionBarDrawerToggle(this,
                binding.dw,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        binding.dw.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        initFragment();
        binding.setPresenter(new Presenter());
    }

    private void initFragment() {
        talkingFragment =new TalkingFragment();
        peopleFragment=new PeopleFragment();
        settingFragment=new SettingFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,talkingFragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pic:
                activityUtils.startActivity(PicActivity.class);
                break;
            case R.id.talk:
                break;

        }
        return false;
    }
    public class  Presenter{
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (i){
                case R.id.talking:
                    fragmentTransaction.replace(R.id.fragment_container,talkingFragment);
                    break;
                case R.id.people:
                    fragmentTransaction.replace(R.id.fragment_container,peopleFragment);
                    break;
                case R.id.setting:
                    fragmentTransaction.replace(R.id.fragment_container,settingFragment);
                    break;
            }
            fragmentTransaction.commit();
        }
    }
}
