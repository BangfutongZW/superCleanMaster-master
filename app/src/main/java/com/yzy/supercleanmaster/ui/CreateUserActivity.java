package com.yzy.supercleanmaster.ui;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.model.User;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateUserActivity extends Activity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_aginepass;
    private Button btn_save;
    private EditText et_reallname;

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
                Toast.makeText(CreateUserActivity.this, "结果："+result, Toast.LENGTH_SHORT).show();
                CreateUserActivity.this.finish();
            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        et_username=(EditText)findViewById(R.id.et_cu_username);
        et_password=(EditText)findViewById(R.id.et_cu_password);
        et_aginepass=(EditText)findViewById(R.id.et_cu_aginpass);
        btn_save=(Button)findViewById(R.id.btn_createuser);
        et_reallname=(EditText)findViewById(R.id.et_cu_reallname);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_username.getText().toString();
                String pass=et_password.getText().toString();
                String apass=et_aginepass.getText().toString();
                String reallname=et_reallname.getText().toString();
                SimpleDateFormat format=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                if(name==null||pass==null||apass==null||reallname==null){
                    Toast.makeText(CreateUserActivity.this, "请完善用户信息", Toast.LENGTH_SHORT).show();
                }else {
                    User u=new User();
                    u.setLocal(UrlStone.local);
                    u.setPassWord(pass);
                    u.setUserName(name);
                    u.setPower(2);
                    u.setRealName(reallname);
                    u.setTeam("");
                    u.setTimeTag(format.format(new Date()));

                    Gson gs=new Gson();
                    String posturls = UrlStone.commmonUrl+"createUserld.do";
                    HttpTool tol = new HttpTool(posturls);
                    Log.e("createu",gs.toJson(u));
                    tol.setBody("jsonstr="+gs.toJson(u));
                    tol.setHandler(handler);
                    new Thread(tol).start();
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
