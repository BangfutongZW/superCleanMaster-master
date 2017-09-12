package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class CheckActivity extends BaseSwipeBackActivity{
    private RelativeLayout rl_xjjh;
    private RelativeLayout rl_lsrw;
    private RelativeLayout rl_bytj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
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
