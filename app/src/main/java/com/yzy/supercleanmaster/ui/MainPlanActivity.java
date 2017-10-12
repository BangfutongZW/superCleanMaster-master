package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.AlarmShowAdapter;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class MainPlanActivity extends Activity {
    private ListView lv_by;

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
            //ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();
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
                        a.setID(obj.getInt("ID"));
                        a.setTagName(obj.getString("tagName"));
                        a.setAlarmValue(obj.getString("alarmValue"));
                        a.setEventTime(obj.getString("eventTime"));
                        a.setEventType(obj.getString("eventType"));
                        a.setAlarmText(obj.getString("alarmText"));
                        a.setLocation(obj.getString("location"));
                        a.setState(obj.getString("state"));
                        //alist.add(a);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                //int size=(alist.size()-recovery*2);
                //if(size<0)size=0;
                //textCounter.setText(""+size);
                //alarmShowAdapter = new AlarmShowAdapter(mContext,alist);
                //mListView.setAdapter(alarmShowAdapter);

                //SharedPreferences sp=mContext.getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
                //SharedPreferences.Editor editor = sp.edit();
                //int webID=alist.get(0).getID();
                //editor.putString("ID", webID+"");
                //editor.commit();

            }else {
                Toast.makeText(getApplicationContext(), "无记录", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plan);
        initData();
    }

    private void initData() {
        lv_by=(ListView)findViewById(R.id.lv_by);
        String posturls = UrlStone.Url+"queryMaintld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }

}
