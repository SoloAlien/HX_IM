package com.example.alien.im.fragment;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.alien.im.R;
import com.example.alien.im.main.SearchFriendActivity;
import com.example.alien.im.utils.ActivityUtils;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by Alien on 2016/12/6.
 * 继承自环信的联系人列表的 EaseContactListFragment
 */

public class PeopleFragment extends EaseContactListFragment {
    private View popView;
    private PopupWindow popMenu;
    private LinearLayout zXing;
    private LinearLayout search;
    private ActivityUtils activityUtils;
    private View.OnClickListener zXingClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    private View.OnClickListener searchClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popMenu.dismiss();
            activityUtils.startActivity(SearchFriendActivity.class);
        }
    };
    /**
     * initView 处理视图
     */
    @Override
    protected void initView() {
        super.initView();
        activityUtils=new ActivityUtils(getActivity());
        //为titlebar设置右侧的加号
        titleBar.setRightImageResource(R.drawable.ic_add_black_24dp);
        //设置头布局（好友邀请、群组等）
        View v=View.inflate(getActivity(),R.layout.headerview,null);
        listView.addHeaderView(v);
        popView=View.inflate(getActivity(),R.layout.popview,null);
        initPopWindow();

    }

    /**
     * setUpView 处理逻辑
     */
    @Override
    protected void setUpView() {
        super.setUpView();
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示poppupwindow
                popMenu.showAsDropDown(titleBar.getRightLayout());
            }
        });
    }
    //初始化poppupwindow
    public void initPopWindow(){
        popMenu=new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setOutsideTouchable(true);
        zXing= (LinearLayout) popView.findViewById(R.id.btn_sao);
        search= (LinearLayout) popView.findViewById(R.id.btn_sou);
        zXing.setOnClickListener(zXingClickListener);
        search.setOnClickListener(searchClickListener);
    }
}
