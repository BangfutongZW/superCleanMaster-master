package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.AlarmShowAdapter;
import com.yzy.supercleanmaster.adapter.MyListViewAdapter1;
import com.yzy.supercleanmaster.adapter.MyListViewAdapter2;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class CheckDayActivity extends Activity {
    private int selectIndex=0;
    private String[] mMenus;
    private String[][] allData;
    private ListView mListView1,mListView2;
    private MyListViewAdapter1 adapter1;
    private MyListViewAdapter2 adapter2;

    Handler dhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            int recovery =0;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };
            ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                try{
                    JSONArray array=new JSONArray(msgStr);
                    mMenus=new String[array.length()];
                    allData=new String[array.length()][];
                    for (int i=0;i<array.length();i++){
                        AlarmInfo a=new AlarmInfo();
                        JSONObject obj=array.getJSONObject(i);
                        String pro=obj.getString("data");
                        String next=obj.getString("endata");
                        mMenus[i]=pro+"--"+next;
                        String name=obj.getString("name");
                        allData[i]=name.split(",");
                    }
                    initView();
                }catch (JSONException e){
                    e.printStackTrace();
                }


            }else {
                Toast.makeText(getApplicationContext(), "无记录", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_day);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getData();

    }

    private void getData() {
        String posturls = UrlStone.Url+"queryAllplanld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(dhandler);
        new Thread(tol).start();
    }

    private void initView() {
        mListView1= (ListView) findViewById(R.id.list_item_1);
        mListView2= (ListView) findViewById(R.id.list_item_2);

        adapter1=new MyListViewAdapter1(mMenus,this,selectIndex);
        adapter2=new MyListViewAdapter2(allData,this,selectIndex);
        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);

        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectIndex=position;
                //把下标传过去，然后刷新adapter
                adapter1.setIndex(position);
                adapter1.notifyDataSetChanged();
                //当点击某个item的时候让这个item自动滑动到listview的顶部(下面item够多，如果点击的是最后一个就不能到达顶部了)
                mListView1.smoothScrollToPositionFromTop(position,0);

                adapter2.setIndex(position);
                mListView2.setAdapter(adapter2);
            }
        });

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(CheckDayActivity.this,allData[selectIndex][position]);
            }
        });
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
