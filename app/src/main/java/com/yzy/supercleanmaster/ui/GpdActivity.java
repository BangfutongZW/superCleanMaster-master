package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.Surveyelect;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.views.ItemCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.InjectView;
import butterknife.OnClick;


public class GpdActivity extends Activity {

    RelativeLayout pd_card1;
    RelativeLayout pd_card2;
    RelativeLayout pd_card3;
    RelativeLayout pd_card4;
    LinearLayout cv_card1;
    LinearLayout cv_card2;
    LinearLayout cv_card3;
    LinearLayout cv_card4;
    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                Log.e("smac",msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr != null&&msgStr.length()>0) {

                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                Log.e("smac",msgStr);
                String[] sa=msgStr.split(",");
                for(String a:sa){
                    updateView(a);
                }
            }else {
                Toast.makeText(getApplicationContext(), "网络连接出错...", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_pd_card);
        initData();
    }
    private void initData() {
        cv_card1=(LinearLayout)findViewById(R.id.cv_card1);
        cv_card2=(LinearLayout)findViewById(R.id.cv_card2);
        cv_card3=(LinearLayout)findViewById(R.id.cv_card3);
        cv_card4=(LinearLayout)findViewById(R.id.cv_card4);
        pd_card1=(RelativeLayout)findViewById(R.id.pd_card1);
        pd_card2=(RelativeLayout)findViewById(R.id.pd_card2);
        pd_card3=(RelativeLayout)findViewById(R.id.pd_card3);
        pd_card4=(RelativeLayout)findViewById(R.id.pd_card4);

        String posturls = "http://119.23.37.145:8080/S2SH/checkMacld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }
    private void updateView(String index){
        switch (index){
            case "1":
                Log.e("smac","ok1");
                cv_card1.setVisibility(View.GONE);
                pd_card1.setOnClickListener(new myClickListener());
                break;
            case "2":
                cv_card2.setVisibility(View.GONE);
                pd_card2.setOnClickListener(new myClickListener());
                break;
            case "3":
                cv_card3.setVisibility(View.GONE);
                pd_card3.setOnClickListener(new myClickListener());
                break;
            case "4":
                cv_card4.setVisibility(View.GONE);
                pd_card4.setOnClickListener(new myClickListener());
                break;
            default:
                break;
        }
    }
    class  myClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String index="";
            switch (v.getId()){
                case R.id.pd_card1:
                    index="1";
                    break;
                case R.id.pd_card2:
                    index="2";
                    break;
                case R.id.pd_card3:
                    index="3";
                    break;
                case R.id.pd_card4:
                    index="4";
                    break;
                default:
                    break;
            }
            Intent intent=new Intent(GpdActivity.this,PeidianActionActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }

}
