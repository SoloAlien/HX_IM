package com.example.alien.im.main.pic;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Alien on 2016/12/4.
 */

public class ImageViewAdapter {
    @BindingAdapter({"app:imageUrl","app:placeholder"})
    public static  void LoadImageView(ImageView view, String url, Drawable drawable){
        Glide.with(view.getContext()).load(url).placeholder(drawable).into(view);
    }
}
