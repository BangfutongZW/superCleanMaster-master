package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.OnClick;

public class CheckActivity extends BaseSwipeBackActivity{
    private RelativeLayout rl_xjjh;
    private RelativeLayout rl_lsrw;
    private RelativeLayout rl_bytj;
    private TextView tv_bjgs;
    private LinearLayout ll_wddb;

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=null;
            msgStr= (String) msg.obj;

            Log.e("fuzai",msgStr);
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

            try {
                JSONObject obj = new JSONObject(msgStr);
                String count=obj.getString("x");
                tv_bjgs.setText(count);
            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        String posturls = UrlStone.Url+"countMaintld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }

    private void initView() {
        tv_bjgs=(TextView)findViewById(R.id.tv_bjgs);
        ll_wddb=(LinearLayout)findViewById(R.id.ll_wddb);
        ll_wddb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckActivity.this, MainPlanActivity.class);
                startActivity(intent);
            }
        });
        rl_xjjh=(RelativeLayout)findViewById(R.id.rl_xjjh);
        rl_xjjh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckActivity.this, CheakMianActivity.class);
                startActivity(intent);
            }
        });
        rl_lsrw=(RelativeLayout)findViewById(R.id.rl_lsrw);
        rl_lsrw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckActivity.this, checkPlanActivity.class);
                startActivity(intent);
            }
        });
        rl_bytj=(RelativeLayout)findViewById(R.id.rl_bytj);
        rl_bytj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckActivity.this, MaintainActivity.class);
                startActivity(intent);
            }
        });

        /*iv_bs=(ImageView)findViewById(R.id.iv_bs);
        iv_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckActivity.this, RepotActivity.class);
                startActivity(intent);
            }
        });*/
    }

    /*@OnClick(R.id.tv_xj)
    public void Clickxj(){
        Intent intent =new Intent(CheckActivity.this,XjplayActivity.class);
        startActivity(intent);

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
