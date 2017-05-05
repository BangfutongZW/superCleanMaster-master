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
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.AlarmShowAdapter;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.widget.textcounter.CounterView;

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
    @InjectView(R.id.textCounter)
    CounterView textCounter;



    AlarmShowAdapter alarmShowAdapter;

    //List<AlarmInfo> mCacheListItem = new ArrayList<>();

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            int recovery =0;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };
            Log.v("tagzw", "handleMessage: "+msgStr);
            ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                try{
                    JSONArray array=new JSONArray(msgStr);
                    for (int i=0;i<array.length();i++){
                        AlarmInfo a=new AlarmInfo();
                        JSONObject obj=array.getJSONObject(i);
                        if(obj.getString("eventType").equals("1")){
                            recovery+=1;
                        }
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
                int size=(alist.size()-recovery*2);
                if(size<0)size=0;
                textCounter.setText(""+size);
                alarmShowAdapter = new AlarmShowAdapter(mContext,alist);
                mListView.setAdapter(alarmShowAdapter);

            }else {
                Toast.makeText(getApplicationContext(), "无记录", Toast.LENGTH_SHORT).show();
            }
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
