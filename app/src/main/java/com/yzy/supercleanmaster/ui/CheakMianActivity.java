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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.dialogs.AlertDialogFragment;
import com.yzy.supercleanmaster.dialogs.DialogUtil;
import com.yzy.supercleanmaster.model.Inspection;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.zxing.android.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

public class CheakMianActivity extends Activity {
    private RelativeLayout rl_check_sm;
    private TextView tv_xjh;
    private TextView tv_yxj;
    private RelativeLayout rl[]=new RelativeLayout[46];
    private EditText et[]=new EditText[46];

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private ArrayList<Inspection> IList=null;
    private String smId="";
    private Button rl_sub;
    private String userName;
    private String checkId;
    private String checkTime;
    private CheckBox check;
    private EditText et_beizhu;
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
                Log.e("check",result);

            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            msgStr= msg.obj.toString();
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                Log.e("source",msgStr);
                View v=View.inflate(CheakMianActivity.this,R.layout.check_dialog,null);
                Log.e("check","111");
                rl[0]=(RelativeLayout)v.findViewById(R.id.rl_0);
                rl[1]=(RelativeLayout)v.findViewById(R.id.rl_1);
                rl[2]=(RelativeLayout)v.findViewById(R.id.rl_2);
                rl[3]=(RelativeLayout)v.findViewById(R.id.rl_3);
                rl[4]=(RelativeLayout)v.findViewById(R.id.rl_4);
                rl[5]=(RelativeLayout)v.findViewById(R.id.rl_5);
                rl[6]=(RelativeLayout)v.findViewById(R.id.rl_6);
                rl[7]=(RelativeLayout)v.findViewById(R.id.rl_7);
                rl[8]=(RelativeLayout)v.findViewById(R.id.rl_8);
                rl[9]=(RelativeLayout)v.findViewById(R.id.rl_9);
                rl[10]=(RelativeLayout)v.findViewById(R.id.rl_10);
                rl[11]=(RelativeLayout)v.findViewById(R.id.rl_11);
                rl[12]=(RelativeLayout)v.findViewById(R.id.rl_12);
                rl[13]=(RelativeLayout)v.findViewById(R.id.rl_13);
                rl[14]=(RelativeLayout)v.findViewById(R.id.rl_14);
                rl[15]=(RelativeLayout)v.findViewById(R.id.rl_15);
                rl[16]=(RelativeLayout)v.findViewById(R.id.rl_16);
                rl[17]=(RelativeLayout)v.findViewById(R.id.rl_17);
                rl[18]=(RelativeLayout)v.findViewById(R.id.rl_18);
                rl[19]=(RelativeLayout)v.findViewById(R.id.rl_19);
                rl[20]=(RelativeLayout)v.findViewById(R.id.rl_20);
                rl[21]=(RelativeLayout)v.findViewById(R.id.rl_21);
                rl[22]=(RelativeLayout)v.findViewById(R.id.rl_22);
                rl[23]=(RelativeLayout)v.findViewById(R.id.rl_23);
                rl[24]=(RelativeLayout)v.findViewById(R.id.rl_24);
                rl[25]=(RelativeLayout)v.findViewById(R.id.rl_25);
                rl[26]=(RelativeLayout)v.findViewById(R.id.rl_26);
                rl[27]=(RelativeLayout)v.findViewById(R.id.rl_27);
                rl[28]=(RelativeLayout)v.findViewById(R.id.rl_28);
                rl[29]=(RelativeLayout)v.findViewById(R.id.rl_29);
                rl[30]=(RelativeLayout)v.findViewById(R.id.rl_30);
                rl[31]=(RelativeLayout)v.findViewById(R.id.rl_31);
                rl[32]=(RelativeLayout)v.findViewById(R.id.rl_32);
                rl[33]=(RelativeLayout)v.findViewById(R.id.rl_33);
                rl[34]=(RelativeLayout)v.findViewById(R.id.rl_34);
                rl[35]=(RelativeLayout)v.findViewById(R.id.rl_35);
                rl[36]=(RelativeLayout)v.findViewById(R.id.rl_36);
                rl[37]=(RelativeLayout)v.findViewById(R.id.rl_37);
                rl[38]=(RelativeLayout)v.findViewById(R.id.rl_38);
                rl[39]=(RelativeLayout)v.findViewById(R.id.rl_39);
                rl[40]=(RelativeLayout)v.findViewById(R.id.rl_40);
                rl[41]=(RelativeLayout)v.findViewById(R.id.rl_41);
                rl[42]=(RelativeLayout)v.findViewById(R.id.rl_42);
                rl[43]=(RelativeLayout)v.findViewById(R.id.rl_43);
                rl[44]=(RelativeLayout)v.findViewById(R.id.rl_44);
                rl[45]=(RelativeLayout)v.findViewById(R.id.rl_45);
                et[0]=(EditText)v.findViewById(R.id.et_0);
                et[1]=(EditText)v.findViewById(R.id.et_1);
                et[2]=(EditText)v.findViewById(R.id.et_2);
                et[3]=(EditText)v.findViewById(R.id.et_3);
                et[4]=(EditText)v.findViewById(R.id.et_4);
                et[5]=(EditText)v.findViewById(R.id.et_5);
                et[6]=(EditText)v.findViewById(R.id.et_6);
                et[7]=(EditText)v.findViewById(R.id.et_7);
                et[8]=(EditText)v.findViewById(R.id.et_8);
                et[9]=(EditText)v.findViewById(R.id.et_9);
                et[10]=(EditText)v.findViewById(R.id.et_10);
                et[11]=(EditText)v.findViewById(R.id.et_11);
                et[12]=(EditText)v.findViewById(R.id.et_12);
                et[13]=(EditText)v.findViewById(R.id.et_13);
                et[14]=(EditText)v.findViewById(R.id.et_14);
                et[15]=(EditText)v.findViewById(R.id.et_15);
                et[16]=(EditText)v.findViewById(R.id.et_16);
                et[17]=(EditText)v.findViewById(R.id.et_17);
                et[18]=(EditText)v.findViewById(R.id.et_18);
                et[19]=(EditText)v.findViewById(R.id.et_19);
                et[20]=(EditText)v.findViewById(R.id.et_20);
                et[21]=(EditText)v.findViewById(R.id.et_21);
                et[22]=(EditText)v.findViewById(R.id.et_22);
                et[23]=(EditText)v.findViewById(R.id.et_23);
                et[24]=(EditText)v.findViewById(R.id.et_24);
                et[25]=(EditText)v.findViewById(R.id.et_25);
                et[26]=(EditText)v.findViewById(R.id.et_26);
                et[27]=(EditText)v.findViewById(R.id.et_27);
                et[28]=(EditText)v.findViewById(R.id.et_28);
                et[29]=(EditText)v.findViewById(R.id.et_29);
                et[30]=(EditText)v.findViewById(R.id.et_30);
                et[31]=(EditText)v.findViewById(R.id.et_31);
                et[32]=(EditText)v.findViewById(R.id.et_32);
                et[33]=(EditText)v.findViewById(R.id.et_33);
                et[34]=(EditText)v.findViewById(R.id.et_34);
                et[35]=(EditText)v.findViewById(R.id.et_35);
                et[36]=(EditText)v.findViewById(R.id.et_36);
                et[37]=(EditText)v.findViewById(R.id.et_37);
                et[38]=(EditText)v.findViewById(R.id.et_38);
                et[39]=(EditText)v.findViewById(R.id.et_39);
                et[40]=(EditText)v.findViewById(R.id.et_40);
                et[41]=(EditText)v.findViewById(R.id.et_41);
                et[42]=(EditText)v.findViewById(R.id.et_42);
                et[43]=(EditText)v.findViewById(R.id.et_43);
                et[44]=(EditText)v.findViewById(R.id.et_44);
                et[45]=(EditText)v.findViewById(R.id.et_45);
                check=(CheckBox)v.findViewById(R.id.check_s);
                et_beizhu=(EditText)v.findViewById(R.id.et_beizhu);
                for (int i=0;i<46;i++){
                    rl[i].setVisibility(View.GONE);
                }
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String param=obj.getString("param");
                    final String name=obj.getString("name");
                    String relation=obj.getString("relation");
                    String belong=obj.getString("belong");
                    final String local=obj.getString("local");
                    belong=belong.trim();


                    if("给排水".equals(belong)){
                        rl[0].setVisibility(View.VISIBLE);
                        rl[1].setVisibility(View.VISIBLE);
                    }else if("发电机".equals(belong)){
                        rl[2].setVisibility(View.VISIBLE);
                    }else if("强电".equals(belong)){
                        rl[3].setVisibility(View.VISIBLE);
                        Log.e("check","333");
                        rl[4].setVisibility(View.VISIBLE);
                        rl[5].setVisibility(View.VISIBLE);
                        rl[6].setVisibility(View.VISIBLE);
                    }else if("空调".equals(belong)){
                        rl[7].setVisibility(View.VISIBLE);
                        rl[8].setVisibility(View.VISIBLE);
                        rl[9].setVisibility(View.VISIBLE);
                        rl[10].setVisibility(View.VISIBLE);
                        rl[11].setVisibility(View.VISIBLE);
                        rl[12].setVisibility(View.VISIBLE);
                        rl[13].setVisibility(View.VISIBLE);
                        rl[14].setVisibility(View.VISIBLE);
                        rl[22].setVisibility(View.VISIBLE);
                        rl[25].setVisibility(View.VISIBLE);
                        rl[28].setVisibility(View.VISIBLE);
                        rl[31].setVisibility(View.VISIBLE);
                        rl[34].setVisibility(View.VISIBLE);
                        rl[37].setVisibility(View.VISIBLE);
                        rl[40].setVisibility(View.VISIBLE);
                        if("DF-KT-40-003".equals(smId)) {
                            rl[23].setVisibility(View.VISIBLE);
                            rl[24].setVisibility(View.VISIBLE);
                            rl[26].setVisibility(View.VISIBLE);
                            rl[27].setVisibility(View.VISIBLE);
                            rl[29].setVisibility(View.VISIBLE);
                            rl[30].setVisibility(View.VISIBLE);
                            rl[32].setVisibility(View.VISIBLE);
                            rl[33].setVisibility(View.VISIBLE);
                            rl[35].setVisibility(View.VISIBLE);
                            rl[36].setVisibility(View.VISIBLE);
                            rl[38].setVisibility(View.VISIBLE);
                            rl[39].setVisibility(View.VISIBLE);
                            rl[41].setVisibility(View.VISIBLE);
                            rl[42].setVisibility(View.VISIBLE);
                        }else{
                            rl[15].setVisibility(View.VISIBLE);
                            rl[16].setVisibility(View.VISIBLE);
                            rl[17].setVisibility(View.VISIBLE);
                            rl[18].setVisibility(View.VISIBLE);
                            rl[19].setVisibility(View.VISIBLE);
                            rl[20].setVisibility(View.VISIBLE);
                            rl[21].setVisibility(View.VISIBLE);
                        }
                    }else if("电梯".equals(belong)){
                        rl[43].setVisibility(View.VISIBLE);
                        rl[44].setVisibility(View.VISIBLE);
                    }else if("计量柜".equals(belong)){
                        rl[45].setVisibility(View.VISIBLE);
                    }
                    check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                et_beizhu.setVisibility(View.VISIBLE);
                            }else{
                                et_beizhu.setVisibility(View.GONE);
                            }
                        }
                    });
                    final String finalBelong = belong;
                    AlertDialog.Builder build=new AlertDialog.Builder(CheakMianActivity.this);
                    build.setView(v);
                    build.setTitle(belong);
                    build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Inspection i=new Inspection();
                            i.setName(name);
                            i.setA(local);
                            i.setId(smId);
                            i.setPatrolTime(checkTime);
                            i.setB(checkId);
                            i.setPatrolMan(userName);
                            if(check.isChecked()){
                                i.setAbnormalNormal("不正常");
                                i.setReason(et_beizhu.getText().toString());
                            }else {
                                i.setAbnormalNormal("正常");
                            }
                            if("给排水".equals(finalBelong)){
                            i.setWaterlevel(et[0].getText().toString());
                            i.setPressure(et[1].getText().toString());
                        }else if("发电机".equals(finalBelong)){
                            i.setBattery(et[2].getText().toString());
                        }else if("强电".equals(finalBelong)){
                            Log.e("check","333");
                            i.setTransformer(et[3].getText().toString());
                            i.setVoltage(et[4].getText().toString());
                            i.setElectric(et[5].getText().toString());
                            i.setPowerFactor(et[6].getText().toString());
                        }else if("空调".equals(finalBelong)){
                            i.setChilledWater(et[7].getText().toString());
                            i.setChilledWaterA(et[8].getText().toString());
                            i.setFreezingWaterPressure(et[9].getText().toString());
                            i.setFreezinginletPressure(et[10].getText().toString());
                            i.setCoolingWaterTemperature(et[11].getText().toString());
                            i.setCoolingInletTemperature(et[12].getText().toString());
                            i.setCoolingWaterPressure(et[13].getText().toString());
                            i.setCoolingInletPressure(et[14].getText().toString());
                                i.setInspiratory(et[22].getText().toString());
                                i.setEvaporating(et[25].getText().toString());
                                i.setExhaustPressure(et[28].getText().toString());
                                i.setCondensing(et[31].getText().toString());
                                i.setExhaustTemperature(et[34].getText().toString());
                                i.setFuelPressure(et[37].getText().toString());
                                i.setPressureDifference(et[40].getText().toString());
                            if("DF-KT-40-003".equals(smId)) {

                                i.setInspiratory1(et[23].getText().toString());
                                i.setInspiratory2(et[24].getText().toString());
                                i.setEvaporating1(et[26].getText().toString());
                                i.setEvaporating2(et[27].getText().toString());
                                i.setExhaustPressure1(et[29].getText().toString());
                                i.setExhaustPressure2(et[30].getText().toString());
                                i.setCondensing1(et[32].getText().toString());
                                i.setCondensing2(et[33].getText().toString());
                                i.setExhaustTemperature1(et[35].getText().toString());
                                i.setExhaustTemperature2(et[36].getText().toString());
                                i.setFuelPressure1(et[38].getText().toString());
                                i.setFuelPressure2(et[39].getText().toString());
                                i.setPressureDifference1(et[41].getText().toString());
                                i.setPressureDifference2(et[42].getText().toString());
                            }else{
                                i.setLineVoltage(et[15].getText().toString());
                                i.setLineCurrent(et[16].getText().toString());
                                i.setMotorTemperature(et[17].getText().toString());
                                i.setGuideVane(et[18].getText().toString());
                                i.setOilTemperature(et[19].getText().toString());
                                i.setLeaveOil(et[20].getText().toString());
                                i.setLeaveExhaustPressure(et[21].getText().toString());
                            }
                        }else if("电梯".equals(finalBelong)){
                            i.setTemperature(et[43].getText().toString());
                            i.setHumidity(et[44].getText().toString());
                        }else if("计量柜".equals(finalBelong)){
                                i.setElectricAA(et[45].getText().toString());
                            }
                            String temp="";
                            IList.add(i);
                            for (Inspection il:IList) {
                                temp+=il.getName();
                            }
                            tv_yxj.setText(temp);

                    }
                });
                build.setNegativeButton("取消",null);
                build.show();


            }catch (JSONException e){
                e.printStackTrace();
            }
            //updateView(a);
        }else {
            Toast.makeText(getApplicationContext(), "网络连接出错...", Toast.LENGTH_SHORT).show();
        }
        super.handleMessage(msg);
    }
};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheak_mian);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initview();
    }

    private void initview() {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        checkTime=f.format(new Date());
        checkId="xj"+System.currentTimeMillis();
        SharedPreferences sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        userName=sp.getString("USER_PERSON","");
        IList=new ArrayList<Inspection>();
        rl_check_sm=(RelativeLayout)findViewById(R.id.rl_check_sm);
        tv_xjh=(TextView)findViewById(R.id.tv_xjh);
        tv_xjh.setText(checkId);
        tv_yxj=(TextView)findViewById(R.id.tv_yxj);
        rl_sub=(Button)findViewById(R.id.rl_sub);
        rl_check_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheakMianActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
        rl_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gs=new Gson();
                for (Inspection i:IList) {
                    String posturls = "http://119.23.37.145:8080/S2SH/Inspectiold.do";
                    HttpTool tol = new HttpTool(posturls);
                    tol.setBody("jsonstr="+gs.toJson(i));
                    //tol.setBody("jsonstr="+jsonParams.toString());
                    Log.e("check",gs.toJson(gs.toJson(i)));
                    tol.setHandler(handler);
                    new Thread(tol).start();
                    finish();
                }


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                //CodeString=data.getStringExtra("scan_result");54512
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                smId=content;
                //scan_tv.setText("解码结果： \n" + content);
                //Toast.makeText(getApplicationContext(),"扫码结果：" + content,Toast.LENGTH_LONG).show();

                String posturls = "http://119.23.37.145:8080/S2SH/parald.do";
                posturls=posturls+"?Id="+content;
                HttpTool tol = new HttpTool(posturls);
                tol.setHandler(sHandler);
                new Thread(tol).start();

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
