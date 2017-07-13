package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ui.CaptureActivity;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class RepotActivity extends BaseSwipeBackActivity{
    private  String CodeString="";
    private RelativeLayout rl_check_sm;
    private TextView tv_n;
    private TextView tv_p;
    private TextView tv_r;

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                Log.e("source","888");
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");

            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr != null&&msgStr.length()>0) {

                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                Log.e("source",msgStr);
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String param=obj.getString("param");
                    String name=obj.getString("name");
                    String relation=obj.getString("relation");
                    tv_n.setText(name);
                    tv_p.setText(param);
                    tv_r.setText(relation);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                //updateView(a);
            }else {
                Toast.makeText(getApplicationContext(), "网络连接出错...", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repot);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        tv_n=(TextView)findViewById(R.id.xj_name);
        tv_p=(TextView)findViewById(R.id.xj_param);
        tv_r=(TextView)findViewById(R.id.xj_rela);
        rl_check_sm=(RelativeLayout)findViewById(R.id.rl_check_sm);
        rl_check_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RepotActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                CodeString=data.getStringExtra("scan_result");

                //scan_tv.setText("解码结果： \n" + content);
                Toast.makeText(getApplicationContext(),"扫码结果：" + CodeString,Toast.LENGTH_LONG).show();

                String posturls = "http://119.23.37.145:8080/S2SH/parald.do";
                posturls=posturls+"?Id="+CodeString;
                Log.e("source",posturls);
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(sHandler);
                new Thread(tol).start();

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
