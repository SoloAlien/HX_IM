package com.example.alien.im.utils.net;

import com.example.alien.im.entity.Picture;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alien on 2016/12/2.
 */

public interface Api {
    @GET("?key=c11d04d6fed739ed2db78987329e13bb&num=10")
    Call<Picture> getPic();
}
