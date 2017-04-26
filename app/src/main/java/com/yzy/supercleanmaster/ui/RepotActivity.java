package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.ui.CaptureActivity;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;

public class RepotActivity extends BaseSwipeBackActivity{
    private  String CodeString="";
    private RelativeLayout rl_check_sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repot);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
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
                Toast.makeText(getApplicationContext(),"解码结果：" + CodeString,Toast.LENGTH_LONG).show();

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
