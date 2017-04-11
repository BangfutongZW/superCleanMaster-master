package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.AlarmShowAdapter;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import butterknife.InjectView;

/**
 * Created by lkzw on 2017/3/28.
 */

public class AlarmListActivity extends BaseSwipeBackActivity {

    @InjectView(R.id.alistview)
    ListView mListView;



    AlarmShowAdapter alarmShowAdapter;

    //List<AlarmInfo> mCacheListItem = new ArrayList<>();

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };
            Log.v("tagzw", "handleMessage: "+msgStr);
            ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();

            String msgStr1="[{\"ID\":220,\"alarmText\":\"\",\"alarmValue\":\"true\",\"eventTime\":\"2017-3-28 22:31:49\",\"eventType\":\"0\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":221,\"alarmText\":\"\",\"alarmValue\":\"false\",\"eventTime\":\"2017-3-28 22:31:50\",\"eventType\":\"1\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":222,\"alarmText\":\"\",\"alarmValue\":\"true\",\"eventTime\":\"2017-3-28 22:31:54\",\"eventType\":\"0\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":223,\"alarmText\":\"\",\"alarmValue\":\"false\",\"eventTime\":\"2017-3-28 22:33:6\",\"eventType\":\"1\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":224,\"alarmText\":\"\",\"alarmValue\":\"true\",\"eventTime\":\"2017-3-29 19:27:9\",\"eventType\":\"0\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":225,\"alarmText\":\"\",\"alarmValue\":\"false\",\"eventTime\":\"2017-3-29 19:27:10\",\"eventType\":\"1\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":226,\"alarmText\":\"\",\"alarmValue\":\"true\",\"eventTime\":\"2017-3-29 19:27:17\",\"eventType\":\"0\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"},{\"ID\":227,\"alarmText\":\"\",\"alarmValue\":\"false\",\"eventTime\":\"2017-3-29 19:28:33\",\"eventType\":\"1\",\"location\":\"\",\"state\":\"1\",\"tagName\":\"东面隔油池高水位19_2\"}]";

            try{
                JSONArray array=new JSONArray(msgStr1);
                for (int i=0;i<array.length();i++){
                    AlarmInfo a=new AlarmInfo();
                    JSONObject obj=array.getJSONObject(i);
                    a.setTagName(obj.getString("tagName"));
                    a.setAlarmValue(obj.getString("alarmValue"));
                    a.setEventTime(obj.getString("eventTime"));
                    a.setEventType(obj.getString("eventType"));
                    a.setAlarmText(obj.getString("alarmText"));
                    a.setLocation(obj.getString("location"));
                    a.setState(obj.getString("state"));
                    alist.add(a);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            alarmShowAdapter = new AlarmShowAdapter(mContext,alist);
            mListView.setAdapter(alarmShowAdapter);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getListData();
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        Log.e("tagzw", "oncreate ");
        setContentView(R.layout.activity_alarm_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getListData();
        //onScanStarted(this);



    }*/

    private void getListData() {
        //String posturls = Constant.TestRUL+"allCust.do";
        String posturls = "http://119.23.37.145:8080/S2SH/todayalarmld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
