package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SettingActivity extends Activity {
    private RelativeLayout rl_project_check;
    private  RelativeLayout rl_noteset;
    private TextView tv_project;

    private RelativeLayout rl_about;
    private RelativeLayout rl_user;
    private CheckBox note;
    private SharedPreferences sp=null;
    private String cid;
    private String username;
    public Handler shandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String msgStr=null;

            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr!= null&&msgStr.length()>10) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);

                try {
                    JSONArray arry=new JSONArray(msgStr);
                    JSONObject obj=arry.getJSONObject(0);
                    String clientId=obj.getString("clientId");
                    if(clientId!=null&&clientId.length()>0&&cid.equals(clientId)){
                        note.setChecked(true);
                    }else {
                        note.setChecked(false);
                    }


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(getApplicationContext(), "网络未连接...", Toast.LENGTH_SHORT).show();
            }


        };
    };
    public Handler phandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String msgStr=null;

            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr!= null&&msgStr.length()>10) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("userData",msgStr);
            }


        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        rl_noteset=(RelativeLayout)findViewById(R.id.rl_noteset);
        rl_project_check=(RelativeLayout) findViewById(R.id.rl_project_check);
        tv_project=(TextView)findViewById(R.id.tv_project);
        rl_about=(RelativeLayout)findViewById(R.id.rl_about);
        rl_user=(RelativeLayout)findViewById(R.id.rl_user);
        note=(CheckBox)findViewById(R.id.checknote);
        initDate();

        if("df".equals(UrlStone.local)){
            tv_project.setText("鼎丰大厦");
        }else {
            tv_project.setText("海翔广场");
        };


        if("gl".equals(UrlStone.local)){
            rl_project_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showSingleAlertDialog(v);
                }
            });
        };
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
        rl_noteset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,NoteSetActivity.class);
                startActivity(intent);
            }
        });
        note.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (note.isChecked()){
                    updatenote(true);
                }else {
                    updatenote(false);
                }
            }
        });
    }

    private void updatenote(boolean b) {
        String notecid;
        if(b){
            notecid=cid;
        }else{
            notecid="";
        }
        String posturls1 = UrlStone.commmonUrl+"updateClientIdld.do";
        posturls1 = posturls1 + "?name=" + username+"&cid="+notecid;
        HttpTool tol1 = new HttpTool(posturls1);
        tol1.setHandler(phandler);
        new Thread(tol1).start();
    }

    private void initDate() {
        cid = PushManager.getInstance().getClientid(this);
        sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        username=sp.getString("USER_NAME","");
        String posturls = UrlStone.commmonUrl+"loadUserld.do";
        posturls = posturls + "?username=" + username;
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(shandler);
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
