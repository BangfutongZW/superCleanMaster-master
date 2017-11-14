package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class MainPlanDaoActivity extends Activity {
    private Button bt_by_s,bt_by_d;
    private TextView tv_by_content;
    private TextView tv[]=new TextView[7];
    private LinearLayout ll[]=new LinearLayout[7];
    private EditText et[]=new EditText[7];


    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null&&msgStr.length()>5) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("Mainplan","msgStr"+msgStr);
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String result=obj.getString("x");
                    if("ok".equals(result)){
                        Toast.makeText(getApplicationContext(),"开始计时",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(getApplicationContext(),"开始失败",Toast.LENGTH_LONG).show();
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
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null&&msgStr.length()>5) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("Mainplan","msgStr"+msgStr);
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String result=obj.getString("x");
                    if("ok".equals(result)){
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"提交失败",Toast.LENGTH_LONG).show();
                    }
                    MainPlanDaoActivity.this.finish();

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
        setContentView(R.layout.activity_main_plan_dao);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        Bundle extras = getIntent().getExtras();
        final int id=extras.getInt("id");
        final String content=extras.getString("content");
        Log.e("Mainplan","content"+content);
        SharedPreferences sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        final String userName = sp.getString("USER_PERSON", "");
        tv[0]=(TextView)findViewById(R.id.tv_by_c0);
        tv[1]=(TextView)findViewById(R.id.tv_by_c1);
        tv[2]=(TextView)findViewById(R.id.tv_by_c2);
        tv[3]=(TextView)findViewById(R.id.tv_by_c3);
        tv[4]=(TextView)findViewById(R.id.tv_by_c4);
        tv[5]=(TextView)findViewById(R.id.tv_by_c5);
        tv[6]=(TextView)findViewById(R.id.tv_by_c6);
        ll[0]=(LinearLayout)findViewById(R.id.ll_by0);
        ll[1]=(LinearLayout)findViewById(R.id.ll_by1);
        ll[2]=(LinearLayout)findViewById(R.id.ll_by2);
        ll[3]=(LinearLayout)findViewById(R.id.ll_by3);
        ll[4]=(LinearLayout)findViewById(R.id.ll_by4);
        ll[5]=(LinearLayout)findViewById(R.id.ll_by5);
        ll[6]=(LinearLayout)findViewById(R.id.ll_by6);
        et[0]=(EditText)findViewById(R.id.et_by_m0);
        et[1]=(EditText)findViewById(R.id.et_by_m1);
        et[2]=(EditText)findViewById(R.id.et_by_m2);
        et[3]=(EditText)findViewById(R.id.et_by_m3);
        et[4]=(EditText)findViewById(R.id.et_by_m4);
        et[5]=(EditText)findViewById(R.id.et_by_m5);
        et[6]=(EditText)findViewById(R.id.et_by_m6);
        bt_by_s=(Button)findViewById(R.id.rl_by_start);
        bt_by_d=(Button)findViewById(R.id.rl_by_done);
        Log.e("Mainplan","split");
        final String[] s=content.split("。");
        Log.e("Mainplan","split size"+s.length);
        for (int i=0;i<s.length;i++){
            ll[i].setVisibility(View.VISIBLE);
            tv[i].setText(s[i]);
        }

        bt_by_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bt_by_s.setClickable(false);
                bt_by_s.setEnabled(false);
                String posturls = UrlStone.Url+"updateMaintTimeld.do";
                posturls=posturls+"?id="+id;
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(sHandler);
                new Thread(tol).start();
            }
        });
        bt_by_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出保养参数填写
                bt_by_d.setEnabled(false);
                String ettext = "";
                for (int i=0;i<s.length;i++){
                    ettext+=(i+1)+"、"+et[i].getText().toString()+"。"+"\n";
                }
                String posturls = UrlStone.Url+"updateMaintenanceld.do";
                posturls=posturls+"?id="+id+"&completion="+URLEncoder.encode(ettext)+"&user="+ URLEncoder.encode(userName);
                Log.e("Mainplan","posturls"+posturls);
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(mHandler);
                new Thread(tol).start();


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
