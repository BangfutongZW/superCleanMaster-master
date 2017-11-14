package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.MaintenancePlan;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MainHistoryActivity extends Activity {
    private List<MaintenancePlan> mAppList;
    private AppAdapter mAdapter;
    private ListView lv;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                Log.e("his", "handleMessage: "+msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                mAppList=new ArrayList<MaintenancePlan>();
                try {
                    JSONArray array=new JSONArray(msgStr);
                    for (int i=0;i<array.length();i++){
                        MaintenancePlan plan=new MaintenancePlan();
                        JSONObject obj=array.getJSONObject(i);
                        plan.setName(obj.getString("name"));
                        plan.setExecutioncycle(obj.getString("executioncycle"));
                        plan.setUnitid(obj.getString("unitid"));
                        mAppList.add(plan);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mAppList.size()>0)updateList();
            }else {
                Toast.makeText(getApplicationContext(), "无待保养计划", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_history);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData() {
        lv=(ListView)findViewById(R.id.ls_history);

        String posturls = UrlStone.Url+"paradld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(handler);
        new Thread(tol).start();
    }

    private void updateList() {
        mAdapter=new AppAdapter();
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaintenancePlan s=mAppList.get(position);
                Intent mIntent = new Intent(MainHistoryActivity.this,MainHistoryListActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("com.history", s.getUnitid());
                mIntent.putExtras(mBundle);

                startActivity(mIntent);
            }
        });
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public MaintenancePlan getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_history, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            MaintenancePlan item = getItem(position);
            holder.tv_name.setText(item.getName());
            holder.tv_cycle.setText(item.getExecutioncycle());
            return convertView;
        }
        class ViewHolder {
            TextView tv_name;
            TextView tv_cycle;

            public ViewHolder(View view) {
                tv_name = (TextView) view.findViewById(R.id.tv_history_name);
                tv_cycle=(TextView)view.findViewById(R.id.tv_history_cycle);
                view.setTag(this);
            }
        }
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
