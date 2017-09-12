package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.zxing.android.CaptureActivity;

public class MaintainActivity extends Activity {
    private RelativeLayout rl_check;
    private Button rl_sub;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private RelativeLayout rl_0m;
    private RelativeLayout rl_1m;
    private EditText et_0m;
    private EditText et_1m;
    private EditText et_bz_m;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        rl_check=(RelativeLayout)findViewById(R.id.rl_check__maintain);
        rl_sub=(Button) findViewById(R.id.rl_sub_maintain);

        rl_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MaintainActivity.this, CaptureActivity.class);
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
                content = data.getStringExtra(DECODED_CONTENT_KEY);
                //检查是否是保养设备。
                /*smId=content;
                //scan_tv.setText("解码结果： \n" + content);
                //Toast.makeText(getApplicationContext(),"扫码结果：" + content,Toast.LENGTH_LONG).show();

                String posturls = UrlStone.Url+"parald.do";
                posturls=posturls+"?Id="+content;
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(sHandler);
                new Thread(tol).start();*/
                startDialog();

            }
        }
    }

    private void startDialog() {
        View v=View.inflate(MaintainActivity.this,R.layout.maintain_dialog,null);
        rl_0m=(RelativeLayout)v.findViewById(R.id.rl_0m);
        rl_1m=(RelativeLayout)v.findViewById(R.id.rl_1m);
        et_0m=(EditText) v.findViewById(R.id.et_0m);
        et_1m=(EditText) v.findViewById(R.id.et_1m);
        et_bz_m=(EditText)v.findViewById(R.id.et_bz_m);

        AlertDialog.Builder build=new AlertDialog.Builder(MaintainActivity.this);
        build.setView(v);
        build.setTitle("填写保养参数");
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"保养完成：" + content,Toast.LENGTH_LONG).show();
            }
        });
        build.setNegativeButton("取消",null);
        build.show();
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
