package com.yzy.supercleanmaster.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.model.Water;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class WaterDfActivity extends Activity {
    //@InjectView(R.id.iv_water_11)
    public ImageView iv_water_11;
    //@InjectView(R.id.iv_water_12)
    public ImageView iv_water_12;
    //@InjectView(R.id.iv_water_13)
    public ImageView iv_water_13;
    //@InjectView(R.id.iv_water_21)
    public ImageView iv_water_21;
    //@InjectView(R.id.iv_water_22)
    public ImageView iv_water_22;
    //@InjectView(R.id.iv_water_23)
    public ImageView iv_water_23;
    //@InjectView(R.id.iv_water_31)
    public ImageView iv_water_31;
    //@InjectView(R.id.iv_water_32)
    public ImageView iv_water_32;
    //@InjectView(R.id.iv_water_33)
    public ImageView iv_water_33;
    //@InjectView(R.id.iv_water_41)
    public ImageView iv_water_41;
    //@InjectView(R.id.iv_water_42)
    public ImageView iv_water_42;
    //@InjectView(R.id.iv_water_43)
    public ImageView iv_water_43;
    //@InjectView(R.id.tv_life_level)
    public TextView tv_life_level;
    public TextView tv_life;
    //@InjectView(R.id.tv_life_pa)
    public TextView tv_life_pa;
    //@InjectView(R.id.tv_fire_level)
    public TextView tv_fire_level;
    public TextView tv_fire;

    public  TextView tv_fire_pa;
    public  TextView tv_penl_pa;

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
                Log.e("dfwater",msgStr);
                //Water w=new Water();
                try {
                    JSONObject obj=new JSONObject(msgStr);
                    /*w.setTime(obj.getString("time"));
                    w.setDate(obj.getString("date"));
                    w.setLifePa((float) obj.getDouble("lifePa"));
                    w.setLifePump1(obj.getString("lifePump1"));
                    w.setLifePump2(obj.getString("lifePump2"));
                    w.setLifePump3(obj.getString("lifePump3"));
                    w.setLifePump4(obj.getString("lifePump4"));
                    w.setFirewaterLevel((float) obj.getDouble("firewaterLevel"));
                    w.setLifewaterLevel((float) obj.getDouble("lifewaterLevel"));*/
                    if("1".equals(obj.getString("lifePump1"))){
                        iv_water_11.setVisibility(View.VISIBLE);
                    }else if("2".equals(obj.getString("lifePump1"))){
                        iv_water_12.setVisibility(View.VISIBLE);
                    }else if("3".equals(obj.getString("lifePump1"))){
                        iv_water_13.setVisibility(View.VISIBLE);
                    }
                    //}
                    //if(!"".equals(w.getLifePump2())&&w.getLifePump2()!=null) {
                    if ("1".equals(obj.getString("lifePump2"))) {
                        iv_water_21.setVisibility(View.VISIBLE);
                    } else if ("2".equals(obj.getString("lifePump2"))) {
                        iv_water_22.setVisibility(View.VISIBLE);
                    } else if ("3".equals(obj.getString("lifePump2"))) {
                        iv_water_23.setVisibility(View.VISIBLE);
                    }
                    //}
                    //if(!"".equals(w.getLifePump3())&&w.getLifePump3()!=null){
                    if("1".equals(obj.getString("firePump"))){
                        iv_water_31.setVisibility(View.VISIBLE);
                    }else if("2".equals(obj.getString("firePump"))){
                        iv_water_32.setVisibility(View.VISIBLE);
                    }else if("3".equals(obj.getString("firePump"))){
                        iv_water_33.setVisibility(View.VISIBLE);
                    }
                    //}
                    //if(!"".equals(w.getLifePump4())&&w.getLifePump4()!=null){
                    if("1".equals(obj.getString("penglin"))){
                        iv_water_41.setVisibility(View.VISIBLE);
                    }else if("2".equals(obj.getString("penglin"))){
                        iv_water_42.setVisibility(View.VISIBLE);
                    }else if("3".equals(obj.getString("penglin"))){
                        iv_water_43.setVisibility(View.VISIBLE);
                    }
                    //}
                    tv_life_level.setText(obj.getDouble("lifewaterLevel")+"m");
                    tv_fire_level.setText(obj.getDouble("firewaterLevel")+"m");
                    tv_life_pa.setText(obj.getDouble("lifePa")+"mPa");
                    tv_fire_pa.setText(obj.getDouble("firePa")+"mPa");
                    tv_penl_pa.setText(obj.getDouble("plPa")+"mPa");
                    tv_fire.setText(obj.getDouble("fireLevel")+"m");
                    tv_life.setText(obj.getDouble("lifeLevel")+"m");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //updateView(w);
            }else {
                Toast.makeText(getApplicationContext(), "读取数据库失败", Toast.LENGTH_SHORT).show();
            }


        };
    };

    private void updateView(Water w) {
        Log.e("dfwater","view1");
       // if(!"".equals(w.getLifePump1())&&w.getLifePump1()!=null){
        if("1".equals(w.getLifePump1())){
            iv_water_11.setVisibility(View.VISIBLE);
        }else if("2".equals(w.getLifePump1())){
            iv_water_12.setVisibility(View.VISIBLE);
        }else if("3".equals(w.getLifePump1())){
            iv_water_13.setVisibility(View.VISIBLE);
        }
        //}
        //if(!"".equals(w.getLifePump2())&&w.getLifePump2()!=null) {
            if ("1".equals(w.getLifePump2())) {
                iv_water_21.setVisibility(View.VISIBLE);
            } else if ("2".equals(w.getLifePump2())) {
                iv_water_22.setVisibility(View.VISIBLE);
            } else if ("3".equals(w.getLifePump2())) {
                iv_water_23.setVisibility(View.VISIBLE);
            }
        //}
        //if(!"".equals(w.getLifePump3())&&w.getLifePump3()!=null){
        /*if("1".equals(w.getLifePump3())){
            iv_water_31.setVisibility(View.VISIBLE);
        }else if("2".equals(w.getLifePump3())){
            iv_water_32.setVisibility(View.VISIBLE);
        }else if("3".equals(w.getLifePump3())){
            iv_water_33.setVisibility(View.VISIBLE);
        }*/
        //}
        //if(!"".equals(w.getLifePump4())&&w.getLifePump4()!=null){
        /*if("1".equals(w.getLifePump4())){
            iv_water_41.setVisibility(View.VISIBLE);
        }else if("2".equals(w.getLifePump4())){
            iv_water_42.setVisibility(View.VISIBLE);
        }else if("3".equals(w.getLifePump4())){
            iv_water_43.setVisibility(View.VISIBLE);
        }*/
        //}
        Log.e("dfwater","view2");
        tv_life_level.setText(w.getLifewaterLevel()+"m");
        tv_fire_level.setText(w.getFirewaterLevel()+"m");
        tv_life_pa.setText(w.getLifePa()+"mPa");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_df);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData() {
        Log.e("dfwater","initview");
        iv_water_11=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_12=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_13=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_21=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_22=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_23=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_31=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_32=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_33=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_41=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_42=(ImageView) findViewById(R.id.iv_water_11);
        iv_water_43=(ImageView) findViewById(R.id.iv_water_11);
        tv_life_pa=(TextView)findViewById(R.id.tv_life_pa);
        tv_life_level=(TextView)findViewById(R.id.tv_life_level);
        tv_fire_level=(TextView)findViewById(R.id.tv_fire_level);
        tv_fire_pa=(TextView)findViewById(R.id.tv_fire_pa);
        tv_penl_pa=(TextView)findViewById(R.id.tv_penl_pa);
        tv_life=(TextView)findViewById(R.id.tv_28life_level);
        tv_fire=(TextView)findViewById(R.id.tv_28fire_level);
        Log.e("dfwater","initData");

        String posturls = UrlStone.Url+"waterld.do";
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
