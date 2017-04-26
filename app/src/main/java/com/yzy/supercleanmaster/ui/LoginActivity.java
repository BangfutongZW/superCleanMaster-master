package com.yzy.supercleanmaster.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    //@InjectView(R.id.login)
    Button login;
    //@InjectView(R.id.username)
    EditText usernameEidt;
    //@InjectView(R.id.password)
    EditText passwordEdit;
    //@InjectView(R.id.check)
    CheckBox check;
    private String username;
    private String password;
    private SharedPreferences sp=null;
    private ProgressDialog proDialog;
    public Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String msgStr=null;

            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
                proDialog.dismiss();
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
                    String pass =obj.getString("passWord");

                    if(password.equals(pass)){
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        proDialog.dismiss();
                        Constants.ISLOGIN=true;
                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
                        proDialog.dismiss();
                    }


                }catch (JSONException e){
                    e.printStackTrace();
                    proDialog.dismiss();
                }
            }else {
                proDialog.dismiss();
                Toast.makeText(getApplicationContext(), "不存在的用户名", Toast.LENGTH_SHORT).show();
            }


        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
        loginAct();
    }

    private void loginAct() {
        if(sp.getBoolean("ISCHECK",false)){
            check.setChecked(true);
            username=sp.getString("USER_NAME","");
            password=sp.getString("PASSWORD","");
            usernameEidt.setText(username);
            passwordEdit.setText(password);
            getData();
        }
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (check.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
    }
    private void getData(){
        if(username==null||username.equals("")){
            Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT)
                    .show();
        }else if(password==null||password.equals("")){
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
                    .show();
        }else{
            proDialog =new ProgressDialog(LoginActivity.this);
            proDialog.setMessage("正在登录中...");
            proDialog.show();
            String posturls = "http://119.23.37.145:8080/S2SH/loadUserld.do";
            posturls=posturls+"?username="+username;
            HttpTool tol = new HttpTool(posturls);
            tol.setHandler(handler);
            new Thread(tol).start();
        }


    }

    private void init() {
        sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        login=(Button)findViewById(R.id.login);
        usernameEidt =(EditText)findViewById(R.id.username);
        passwordEdit =(EditText)findViewById(R.id.password);
        check=(CheckBox)findViewById(R.id.check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEidt.getText().toString();
                password = passwordEdit.getText().toString();
                // 登录成功和记住密码框为选中状态才保存用户信息
                if (check.isChecked()) {
                    // 记住用户名、密码、
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USER_NAME", username);
                    editor.putString("PASSWORD", password);
                    editor.commit();
                }
                getData();
            }
        });
        
    }

}
