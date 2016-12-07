package com.example.alien.im.main.pic;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.alien.im.R;
import com.example.alien.im.databinding.ActivityPicBinding;

import com.example.alien.im.entity.Model;
import com.example.alien.im.entity.Picture;
import com.example.alien.im.utils.net.Api;
import com.example.alien.im.utils.net.NetClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PicActivity extends AppCompatActivity {
    ActivityPicBinding binding;
    private String baseUrl="http://api.tianapi.com/meinv/";
    private ArrayList<Picture.Pic> newslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_pic);
        setSupportActionBar(binding.picToolbar);
        getSupportActionBar().setTitle("dfsgfgf");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TabLayout.Tab tab1= binding.tabLayout.newTab().setText("美图1");
        TabLayout.Tab tab2= binding.tabLayout.newTab().setText("美图2");
        TabLayout.Tab tab3= binding.tabLayout.newTab().setText("美图3");
        binding.tabLayout.addTab(tab1);
        binding.tabLayout.addTab(tab2);
        binding.tabLayout.addTab(tab3);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        binding.picRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerviewAdapter adapter=new RecyclerviewAdapter(this);
        binding.picRecyclerview.setAdapter(adapter);

        final Call<Picture> call=NetClient.getInstance(baseUrl).getApi().getPic();
        Model.getInstance().getService().execute(new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<Picture>() {
                    @Override
                    public void onResponse(Call<Picture> call, Response<Picture> response) {
                        if (response!=null&&response.isSuccessful()){
                            Picture body = response.body();
                            if (body!=null){
                                newslist = body.newslist;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.appendData(newslist);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Picture> call, Throwable t) {

                    }
                });
            }
        });
    }
}
