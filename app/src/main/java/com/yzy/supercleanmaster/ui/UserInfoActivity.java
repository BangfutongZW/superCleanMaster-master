package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
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
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UserInfoActivity extends Activity {
    private SharedPreferences sp=null;
    private TextView tv_username;
    private TextView tv_proname;
    private TextView usr_type;
    private EditText et_ypass;
    private EditText et_xpass;
    private EditText et_qpass;
    private Button btn_update;
    private String pass="";
    private RelativeLayout rl_create_user;

    public Handler handler = new Handler(){
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
                Log.e("userData",msgStr);
                try {
                    JSONArray arry=new JSONArray(msgStr);
                    JSONObject obj=arry.getJSONObject(0);
                    String name=obj.getString("userName");
                    pass =obj.getString("passWord");
                    String power=obj.getString("power");
                    tv_username.setText(name);
                    if("0".equals(power)){
                        usr_type.setText("全局管理员");
                        seeCreateUser();
                    }else if("1".equals(power)){
                        usr_type.setText("项目管理员");
                        seeCreateUser();
                    }else {
                        usr_type.setText("操作员");
                    }


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }


        };
    };
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
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("userData",msgStr);
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String result=obj.getString("x");

                    Toast.makeText(UserInfoActivity.this, "结果："+result, Toast.LENGTH_SHORT).show();
                    //修改存储用户名。

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }


        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initData() {
        sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        String posturls = UrlStone.commmonUrl+"loadUserld.do";
        String username=sp.getString("USER_NAME","");
        if (username!=null) {
            posturls = posturls + "?username=" + username;
            HttpTool tol = new HttpTool(posturls);
            tol.setHandler(handler);
            new Thread(tol).start();
        }

    }
    private  void seeCreateUser(){
        rl_create_user.setVisibility(View.VISIBLE);
        rl_create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserInfoActivity.this,CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        tv_username=(TextView)findViewById(R.id.tv_username);
        tv_proname=(TextView)findViewById(R.id.tv_proname);
        usr_type=(TextView)findViewById(R.id.usr_type);
        et_ypass=(EditText)findViewById(R.id.et_ypass);
        et_xpass=(EditText)findViewById(R.id.et_xpass);
        et_qpass=(EditText)findViewById(R.id.et_qpass);
        btn_update=(Button)findViewById(R.id.btn_update);
        rl_create_user=(RelativeLayout)findViewById(R.id.rl_create_user);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_ypass.getText().toString().length() == 0){
                    Toast.makeText(UserInfoActivity.this, "请输入原密码", Toast.LENGTH_SHORT).show();
                }else if(et_xpass.getText().toString().length() == 0){
                    Toast.makeText(UserInfoActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else if (et_qpass.getText().toString().length() == 0){
                    Toast.makeText(UserInfoActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                }else if (!et_qpass.getText().toString().equals(et_xpass.getText().toString())){
                    Toast.makeText(UserInfoActivity.this, "新密码确认错误", Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(et_ypass.getText().toString())){
                    Toast.makeText(UserInfoActivity.this, "原密码输入错误", Toast.LENGTH_SHORT).show();
                }else{
                    updateUserpass();
                }
            }
        });
    }

    private void updateUserpass() {
        String posturls = UrlStone.commmonUrl+"updateUserpassld.do";
        String username=tv_username.getText().toString();
        String password=et_xpass.getText().toString();
        if (username!=null) {
            posturls = posturls + "?name=" + username+"&pass="+password;
            HttpTool tol = new HttpTool(posturls);
            tol.setHandler(shandler);
            new Thread(tol).start();
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
