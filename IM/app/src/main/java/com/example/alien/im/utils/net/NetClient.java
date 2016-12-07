package com.example.alien.im.utils.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alien on 2016/12/2.
 */

public class NetClient{
    private static NetClient client;
    private Retrofit mRetrofit;
//    private String baseUrl="http://api.tianapi.com/meinv/";
    public static NetClient getInstance(String baseUrl){
        if (client==null){
            client=new NetClient(baseUrl);
        }
        return client;
    }
    private NetClient(String baseUrl){
        mRetrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public Api getApi(){
        return mRetrofit.create(Api.class);
    }
}
