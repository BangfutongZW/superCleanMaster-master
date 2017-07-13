package com.yzy.supercleanmaster.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by lkzw on 2017/6/20.zhangwei
 */

public class UpdateUtil {
    private Context mContext=null;
    public UpdateUtil(Context context){
        this.mContext=context;
    }
    public int getAppVersion(){
        int versionCode=0;
        try {
            versionCode=mContext.getPackageManager().getPackageInfo("com.yzy.supercleanmaster",0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public String getValue(String key)
    {
        SharedPreferences mySharedPreferences= mContext.getSharedPreferences("version",
                Activity.MODE_PRIVATE);
        String value = "";
        value =  mySharedPreferences.getString(key, "");
        if(value == null){
            value = "";
        }
        return value;
    }

    public void setValue(String key, String value){
        SharedPreferences mySharedPreferences= mContext.getSharedPreferences("version",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value).commit();
    }




}
