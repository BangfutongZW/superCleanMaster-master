package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;

public class SettingActivity extends Activity {
    private RelativeLayout rl_project_check;
    private TextView tv_project;
    private int curr_index;
    private RelativeLayout rl_about;
    private RelativeLayout rl_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        rl_project_check=(RelativeLayout) findViewById(R.id.rl_project_check);
        tv_project=(TextView)findViewById(R.id.tv_project);
        rl_about=(RelativeLayout)findViewById(R.id.rl_about);
        rl_user=(RelativeLayout)findViewById(R.id.rl_user);
        rl_project_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleAlertDialog(v);
            }
        });
        rl_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        rl_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private AlertDialog alertDialog2;
    public void showSingleAlertDialog(View view){
        curr_index=0;
        final String[] items = {"海翔广场","鼎丰大厦","联合广场"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("支持项目");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int index) {
                //Toast.makeText(SettingActivity.this, items[index], Toast.LENGTH_SHORT).show();
                curr_index=index;
            }

        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //TODO 业务逻辑代码
                tv_project.setText(items[curr_index]);
                //Toast.makeText(SettingActivity.this, arg1+"..", Toast.LENGTH_SHORT).show();
                // 关闭提示框
                alertDialog2.dismiss();
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO 业务逻辑代码

                // 关闭提示框
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
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
