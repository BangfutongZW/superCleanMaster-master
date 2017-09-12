package com.yzy.supercleanmaster.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    private String[] items = {"海翔广场","鼎丰大厦","联合广场"};
    private int curr_index;
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

                try {
                    JSONArray arry=new JSONArray(msgStr);
                    JSONObject obj=arry.getJSONObject(0);
                    String name=obj.getString("userName");
                    String pass =obj.getString("passWord");
                    String power=obj.getString("power");
                    String person=obj.getString("realName");
                    String team=obj.getString("team")+"";
                    String local=obj.getString("local").trim();
                    UrlStone.local=local;
                    if("hx".equals(local)||"gl".equals(local)){
                        UrlStone.Url="http://119.23.37.145:8080/S2SH/";
                    }else if("df".equals(local)){
                        UrlStone.Url="http://119.23.37.145:8080/S2SHDF/";
                    }
                    if(password.equals(pass)){
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        proDialog.dismiss();
                        Constants.ISLOGIN=true;
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_POWER", power);
                        editor.putString("USER_PERSON", person);
                        editor.putString("USER_TEAM", team);
                        editor.commit();
                        if("gl".equals(local)){
                            showSingleAlertDialog();
                        }else {
                            Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }

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
                Toast.makeText(getApplicationContext(), "不存在的用户名或网络未连接...", Toast.LENGTH_SHORT).show();
            }


        };
    };
    private AlertDialog alertDialog2;
    public void showSingleAlertDialog(){

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
                //Toast.makeText(SettingActivity.this, arg1+"..", Toast.LENGTH_SHORT).show();
                if(curr_index==0){
                    UrlStone.local="hx";
                    UrlStone.Url="http://119.23.37.145:8080/S2SH/";
                }else if(curr_index==1){
                    UrlStone.local="df";
                    UrlStone.Url="http://119.23.37.145:8080/S2SHDF/";
                }
                // 关闭提示框
                alertDialog2.dismiss();
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
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
            String posturls =UrlStone.commmonUrl+ "loadUserld.do";
            Log.e("userDatapost",posturls);
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
