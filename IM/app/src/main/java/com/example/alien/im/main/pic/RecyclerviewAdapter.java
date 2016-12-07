package com.example.alien.im.main.pic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alien.im.BR;
import com.example.alien.im.R;
import com.example.alien.im.entity.Picture;

import java.util.ArrayList;

/**
 * Created by Alien on 2016/12/4.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private ViewDataBinding binding;
    private LayoutInflater inflate;
    private ArrayList<Picture.Pic> arrayList;
    public RecyclerviewAdapter(Context context){
        arrayList=new ArrayList<>();
        inflate=LayoutInflater.from(context);
    }
    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(inflate, R.layout.item,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Picture.Pic pic=arrayList.get(position);
        holder.getmBinding().setVariable(BR.item,pic);
        holder.getmBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: "+arrayList.size() );
        return arrayList.size();

    }
    public void appendData(ArrayList<Picture.Pic> list){
        arrayList.addAll(list);
        notifyDataSetChanged();
    }
    public void appendData(Picture.Pic pic){
        arrayList.add(pic);
    }
}
