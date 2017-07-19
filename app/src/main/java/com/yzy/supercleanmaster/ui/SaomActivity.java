package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseActivity;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.zxing.android.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class SaomActivity extends BaseActivity {
    private SharedPreferences sp=null;
    TextView scan_tv;
    Button chart_btn;
    Button scan_btn;
    private static final String DECODED_CONTENT_KEY = "codedContent";

    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

            try {
                JSONObject obj = new JSONObject(msgStr);
                String result=obj.getString("x");
                if("ok".equals(result)){
                    Toast.makeText(getApplicationContext(), "开门成功", Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            finish();
            super.handleMessage(msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saom);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        scan_btn=(Button)findViewById(R.id.scan_btn);
        chart_btn=(Button)findViewById(R.id.chart_btn);
        scan_tv=(TextView)findViewById(R.id.scan_tv);
        /*scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("baoshi","start");
                Intent intent=new Intent(SaomActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        })*/;
        /*Intent intent=new Intent(SaomActivity.this, CaptureActivity.class);
        startActivityForResult(intent,0);
        chart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("baoshi","start");
                Intent intent=new Intent(SaomActivity.this, RunStateActivity.class);
                startActivity(intent);
            }
        });*/
        Intent intent=new Intent(SaomActivity.this, CaptureActivity.class);
        startActivityForResult(intent,0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                scan_tv.setText("扫码结果： \n" + content);
                Log.e("saom",content);
                update_door(content);
            }
        }
    }

    private void update_door(String content) {
        sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        String power=sp.getString("USER_POWER","");
        Log.e("saom",power);
        String door=content.substring(11);
        if("1".equals(power)&&"Lk_Econ_HX".equals(content.substring(0,10))){
            insertDoor(door);
        }else if("2".equals(power)&&"Lk_Econ_DF".equals(content.substring(0,10))){
            insertDoor(door);
        }else if("0".equals(power)&&"Lk_Econ".equals(content.substring(0,7))){
            Log.e("saom","open");
            insertDoor(door);
        }else {
            Toast.makeText(getApplicationContext(), "当前用户没有开门权限..", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void insertDoor(String door) {
        String username=sp.getString("USER_NAME","");
        String posturls = "http://119.23.37.145:8080/S2SH/opendoorld.do";
        posturls=posturls+"?door="+door+"&user="+username;
        Log.e("saom",posturls);
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(handler);
        new Thread(tol).start();
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
