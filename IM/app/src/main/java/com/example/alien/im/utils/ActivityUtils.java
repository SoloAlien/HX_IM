package com.example.alien.im.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Alien on 2016/7/11.
 */
public class ActivityUtils {
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;
    private Toast toast;
    public ActivityUtils(Activity activity) {
        activityWeakReference=new WeakReference<Activity>(activity);
    }
    public ActivityUtils(Fragment fragment){
        fragmentWeakReference=new WeakReference<Fragment>(fragment);
    }
    private @Nullable Activity getActivity(){
        if (activityWeakReference!=null)
            return activityWeakReference.get();
        if (fragmentWeakReference!=null){
            Fragment fragment=fragmentWeakReference.get();
            //如果fragment为空就返回空否则返回fragment所在的activity
            return fragment==null?null:fragmentWeakReference.get().getActivity();
        }
        return null;
    }
    public void showToast(String msg){
        Activity activity=getActivity();
        if (activity!=null){
            if (toast==null)
                toast=Toast.makeText(activity,msg,Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        }
    }
    public void  showToast(int resId){
        Activity activity=getActivity();
        if (activity!=null){
            String msg=activity.getString(resId);
            Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
        }
    }
    public void startActivity(Class<?extends Activity>  clazz){
        Activity activity=getActivity();
        if (activity==null)return;
        Intent intent=new Intent(activity,clazz);
        activity.startActivity(intent);
    }
    public int getStatusBarHeight(){
        Activity activity=getActivity();
        if (activity==null)return 0;
        Resources resources=activity.getResources();
        int height=0;
        /**获取各应用包下的指定资源ID
         * params1：id名称
         * params2：资源文件夹名
         *  params3：应用包名
         */
        int  resoureceId=resources.getIdentifier("status_bar_height","dimen","android");
        if (resoureceId>0){
            height=resources.getDimensionPixelSize(resoureceId);
        }
        return height;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getScreenWidth(){
        Activity activity=getActivity();
        if (activity==null)return 0;
        DisplayMetrics metrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    public int getScreenHeight(){
        Activity activity=getActivity();
        if (activity==null)return 0;
        DisplayMetrics metrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard(){
        Activity activity=getActivity();
        if (activity==null)return;
        View view=activity.getCurrentFocus();
        if (view!=null){
            InputMethodManager inputMethodManager= (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

    }

}
