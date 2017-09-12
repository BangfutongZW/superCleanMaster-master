package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class NoteSetActivity extends Activity {
    private SharedPreferences sp=null;
    private TextView curr_team;
    private EditText et_setteam;
    private String username;
    private Button btn_noteset;
    private String cid;


    public Handler shandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String msgStr=null;

            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr!= null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("userData",msgStr);
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String result=obj.getString("x");

                    Toast.makeText(NoteSetActivity.this, "结果："+result, Toast.LENGTH_SHORT).show();
                    //修改存储分组名。

                }catch (JSONException e){
                    e.printStackTrace();
                }
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
        setContentView(R.layout.activity_note_set);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initDate();
    }

    private void initDate() {
        cid = PushManager.getInstance().getClientid(this);
        sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        username=sp.getString("USER_NAME","");
        String team=sp.getString("USER_TEAM","");
        curr_team.setText(team);
    }

    private void initView() {
        curr_team=(TextView)findViewById(R.id.curr_team);
        et_setteam=(EditText)findViewById(R.id.et_setteam);
        btn_noteset=(Button)findViewById(R.id.btn_noteset);


        btn_noteset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_setteam.length()>0){
                    String team=et_setteam.getText().toString();
                    String posturls = UrlStone.commmonUrl+"updateUserteamld.do";
                    posturls = posturls + "?name=" + username+"&team="+team;
                    HttpTool tol = new HttpTool(posturls);
                    tol.setHandler(shandler);
                    new Thread(tol).start();

                    String posturls1 = UrlStone.commmonUrl+"updateClientIdld.do";
                    posturls1 = posturls1 + "?name=" + username+"&cid="+cid;
                    HttpTool tol1 = new HttpTool(posturls1);
                    tol1.setHandler(phandler);
                    new Thread(tol1).start();

                }else{
                    Toast.makeText(NoteSetActivity.this, "请输入新的分组名", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
