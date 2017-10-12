package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.zxing.android.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MaintainActivity extends Activity {
    private RelativeLayout rl_check;
    private Button rl_sub;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private EditText et_bz_m;
    private String content;
    private TextView tv_by_result;

    Handler sHandler=new Handler(){
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
                    tv_by_result.setVisibility(View.VISIBLE);
                    if("ok".equals(result)){
                        tv_by_result.setText("提交成功！");
                    }else {
                        tv_by_result.setText("提交失败，没有此项设备保养计划！");
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
        setContentView(R.layout.activity_maintain);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        rl_check=(RelativeLayout)findViewById(R.id.rl_check__maintain);
        tv_by_result=(TextView)findViewById(R.id.tv_by_result);

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
        et_bz_m=(EditText)v.findViewById(R.id.et_bz_m);


        AlertDialog.Builder build=new AlertDialog.Builder(MaintainActivity.this);
        build.setView(v);
        build.setTitle("填写保养参数");
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"扫码结果：" + content,Toast.LENGTH_LONG).show();
                String posturls = UrlStone.Url+"updateMaintenanceld.do";
                posturls=posturls+"?id="+content+"&completion="+et_bz_m.getText().toString();
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(sHandler);
                new Thread(tol).start();
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
