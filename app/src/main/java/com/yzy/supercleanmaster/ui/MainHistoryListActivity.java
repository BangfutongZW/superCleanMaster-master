package com.yzy.supercleanmaster.ui;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.Maintenance;
import com.yzy.supercleanmaster.model.MaintenancePlan;
import com.yzy.supercleanmaster.model.Source;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainHistoryListActivity extends Activity {
    private String mId;
    private List<Maintenance> mAppList;
    private AppAdapter mAdapter;
    private ListView ls_history_list;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("his", "handler");
            String msgStr=null;
            msgStr=(String) msg.obj;
            Log.e("his", "msgStr"+msgStr);
            //ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                try{
                    JSONObject o=new JSONObject(msgStr);
                    //mId=o.getString("unitId");
                    tv1.setText(o.getString("name"));
                    tv2.setText(o.getString("belong"));
                    tv3.setText(o.getString("model"));
                    tv4.setText(o.getString("dateOfUse"));
                    tv5.setText(o.getString("usefulLife"));
                    tv6.setText(o.getString("runState"));
                    tv7.setText(o.getString("relation"));
                    tv8.setText(o.getString("personLiable"));
                    tv9.setText(o.getString("param"));

                    getByData();

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }else {
                Toast.makeText(getApplicationContext(), "无待保养计划", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    Handler dHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            msgStr=(String) msg.obj;
            Log.e("his", "handleMessage: "+msgStr);
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                mAppList=new ArrayList<Maintenance>();
                try{
                    JSONArray array=new JSONArray(msgStr);
                    for (int i=0;i<array.length();i++){
                        Maintenance m=new Maintenance();
                        JSONObject obj=array.getJSONObject(i);
                        m.setExecutor(obj.getString("executor"));
                        m.setDateofexecution(obj.getString("dateofexecution"));
                        //String cont=obj.getString("implementation");
                        //cont=cont.replace("","");
                        m.setImplementation(obj.getString("implementation"));
                        Log.e("his", "DATE"+obj.getString("dateofexecution"));
                        mAppList.add(m);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                Log.e("his", "size"+mAppList.size());
                if (mAppList.size()>0){
                    initList();
                }


            }else {
                Toast.makeText(getApplicationContext(), "无待保养计划", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_history_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        mId=getIntent().getStringExtra("com.history");
        tv1=(TextView)findViewById(R.id.tv_h_1);
        tv2=(TextView)findViewById(R.id.tv_h_2);
        tv3=(TextView)findViewById(R.id.tv_h_3);
        tv4=(TextView)findViewById(R.id.tv_h_4);
        tv5=(TextView)findViewById(R.id.tv_h_5);
        tv6=(TextView)findViewById(R.id.tv_h_6);
        tv7=(TextView)findViewById(R.id.tv_h_7);
        tv8=(TextView)findViewById(R.id.tv_h_8);
        tv9=(TextView)findViewById(R.id.tv_h_9);
        ls_history_list=(ListView)findViewById(R.id.ls_history_list);

        String posturls = UrlStone.Url+"parald.do";
        posturls=posturls+"?Id="+mId;
        Log.e("his", "posturls"+posturls);
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }
    private void getByData() {
        Log.e("his", "handler");
        String posturls = UrlStone.Url+"queryMaintedld.do";
        posturls=posturls+"?id="+mId;
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(dHandler);
        new Thread(tol).start();
    }

    private void initList() {
        //ListView ls_history_list=(ListView)findViewById(R.id.ls_history_list);
        mAdapter=new AppAdapter();
        ls_history_list.setAdapter(mAdapter);

    }
    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public Maintenance getItem(int position) {
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
                        R.layout.item_history_list, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder)convertView.getTag();
            Maintenance item = getItem(position);
            holder.tv_h_time.setText(item.getExecutiondata());
            holder.tv_h_content.setText(item.getImplementation());
            holder.tv_h_person.setText(item.getExecutor());
            return convertView;
        }
        class ViewHolder {
            TextView tv_h_time;
            TextView tv_h_content;
            TextView tv_h_person;

            public ViewHolder(View view) {
                tv_h_time = (TextView) view.findViewById(R.id.tv_h_time);
                tv_h_content = (TextView) view.findViewById(R.id.tv_h_content);
                tv_h_person = (TextView) view.findViewById(R.id.tv_h_person);
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
