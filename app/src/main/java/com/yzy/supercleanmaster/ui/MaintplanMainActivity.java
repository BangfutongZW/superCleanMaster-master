package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.Maintenance;
import com.yzy.supercleanmaster.model.MaintenancePlan;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.zxing.android.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MaintplanMainActivity extends Activity {
    private AppAdapter mAdapter;
    private List<Maintenance> mAppList;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private Maintenance ma;

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };
            //ArrayList<AlarmInfo> alist=new ArrayList<AlarmInfo>();
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                mAppList=new ArrayList<Maintenance>();
                try{
                    JSONArray array=new JSONArray(msgStr);
                    for (int i=0;i<array.length();i++){
                        Maintenance a=new Maintenance();
                        JSONObject obj=array.getJSONObject(i);
                        a.setContent(obj.getString("content"));
                        a.setExecutiondata(obj.getString("executiondata"));
                        a.setDegree(obj.getString("degree"));
                        a.setMaintenancecategory(obj.getString("maintenancecategory"));
                        a.setUnitid(obj.getString("unitid"));
                        a.setId(obj.getInt("id"));
                        mAppList.add(a);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                if (mAppList.size()>0){
                    updateList();
                }


            }else {
                Toast.makeText(getApplicationContext(), "无待保养计划", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null&&msgStr.length()>5) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String result=obj.getString("x");
                    if("ok".equals(result)){
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(getApplicationContext(),"提交失败",Toast.LENGTH_LONG).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(getApplicationContext(),"未通讯，请检查网络",Toast.LENGTH_LONG).show();
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintplan_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initData();


    }
    private void initData() {
        String posturls = UrlStone.Url+"queryMaintld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }
    private void updateList(){
        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.Minat_listView);
        mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ma=mAppList.get(position);
                Intent intent=new Intent(MaintplanMainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                //CodeString=data.getStringExtra("scan_result");54512
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                if(content.equals(ma.getUnitid())){
                    Intent mIntent = new Intent(MaintplanMainActivity.this,MainPlanDaoActivity.class);
                    mIntent.putExtra("id", ma.getId());
                    mIntent.putExtra("content", ma.getContent());
                    startActivity(mIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"不是该台设备！！！",Toast.LENGTH_LONG).show();
                }


            }
        }
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
        public int getViewTypeCount() {
            // menu type count
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position % 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            Maintenance item = getItem(position);
            holder.tv_name.setText(item.getMaintenancecategory());
            holder.tv_time.setText(item.getExecutiondata());
            holder.tv_type.setText(item.getDegree());
            return convertView;
        }
        class ViewHolder {
            TextView tv_name;
            TextView tv_time;
            TextView tv_type;

            public ViewHolder(View view) {
                tv_name = (TextView) view.findViewById(R.id.tv_maintname);
                tv_time = (TextView) view.findViewById(R.id.tv_mainttime);
                tv_type = (TextView) view.findViewById(R.id.tv_mainttype);
                view.setTag(this);
            }
        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
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
