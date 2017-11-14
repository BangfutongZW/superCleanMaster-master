package com.yzy.supercleanmaster.ui;

import android.os.Bundle;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;

public class RepotActivity extends BaseSwipeBackActivity {
    /*private  String CodeString="";
    private RelativeLayout rl_check_sm;
    private TextView tv_n;
    private TextView tv_p;
    private TextView tv_r;
    private  Button rl_sub;
    private EditText et_beizhu;
    private String  userName;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private RelativeLayout rl[]=new RelativeLayout[25];
    private EditText et[]=new EditText[25];
    private CheckBox check;
    String name="";
    String belong="";
    String id="";
    String local="";

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
                try {
                    JSONObject obj = new JSONObject(msgStr);
                    String param=obj.getString("param");
                    name=obj.getString("name");
                    String relation=obj.getString("relation");
                    belong=obj.getString("belong");
                    local=obj.getString("local");
                    belong=belong.trim();
                    if("给排水".equals(belong)){
                        rl[0].setVisibility(View.VISIBLE);
                        rl[1].setVisibility(View.VISIBLE);
                    }else if("发电机".equals(belong)){
                        rl[2].setVisibility(View.VISIBLE);
                    }else if("强电".equals(belong)){
                        rl[3].setVisibility(View.VISIBLE);
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
                        rl[15].setVisibility(View.VISIBLE);
                        rl[16].setVisibility(View.VISIBLE);
                        rl[17].setVisibility(View.VISIBLE);
                        rl[18].setVisibility(View.VISIBLE);
                        rl[19].setVisibility(View.VISIBLE);
                        rl[20].setVisibility(View.VISIBLE);
                        rl[21].setVisibility(View.VISIBLE);
                        rl[22].setVisibility(View.VISIBLE);
                    }else if("电梯".equals(belong)){
                        rl[23].setVisibility(View.VISIBLE);
                        rl[24].setVisibility(View.VISIBLE);
                    }
                    tv_n.setText(name);
                    tv_p.setText(param);
                    tv_r.setText(relation);
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
                Log.e("source",result);
                for (EditText t:et) {
                    t.setText("");
                }
                et_beizhu.setText("");
                tv_r.setText("");
                tv_p.setText("");
                tv_n.setText("");
            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
    Handler ehandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

            Log.e("task",msgStr);
            super.handleMessage(msg);
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repot);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //initView();
    }

    /*private void initView() {
        SharedPreferences sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        userName=sp.getString("USER_PERSON","");
        rl[0]=(RelativeLayout)findViewById(R.id.rl_0);
        rl[1]=(RelativeLayout)findViewById(R.id.rl_1);
        rl[2]=(RelativeLayout)findViewById(R.id.rl_2);
        rl[3]=(RelativeLayout)findViewById(R.id.rl_3);
        rl[4]=(RelativeLayout)findViewById(R.id.rl_4);
        rl[5]=(RelativeLayout)findViewById(R.id.rl_5);
        rl[6]=(RelativeLayout)findViewById(R.id.rl_6);
        rl[7]=(RelativeLayout)findViewById(R.id.rl_7);
        rl[8]=(RelativeLayout)findViewById(R.id.rl_8);
        rl[9]=(RelativeLayout)findViewById(R.id.rl_9);
        rl[10]=(RelativeLayout)findViewById(R.id.rl_10);
        rl[11]=(RelativeLayout)findViewById(R.id.rl_11);
        rl[12]=(RelativeLayout)findViewById(R.id.rl_12);
        rl[13]=(RelativeLayout)findViewById(R.id.rl_13);
        rl[14]=(RelativeLayout)findViewById(R.id.rl_14);
        rl[15]=(RelativeLayout)findViewById(R.id.rl_15);
        rl[16]=(RelativeLayout)findViewById(R.id.rl_16);
        rl[17]=(RelativeLayout)findViewById(R.id.rl_17);
        rl[18]=(RelativeLayout)findViewById(R.id.rl_18);
        rl[19]=(RelativeLayout)findViewById(R.id.rl_19);
        rl[20]=(RelativeLayout)findViewById(R.id.rl_20);
        rl[21]=(RelativeLayout)findViewById(R.id.rl_21);
        rl[22]=(RelativeLayout)findViewById(R.id.rl_22);
        rl[23]=(RelativeLayout)findViewById(R.id.rl_23);
        rl[24]=(RelativeLayout)findViewById(R.id.rl_24);
        et[0]=(EditText)findViewById(R.id.et_0);
        et[1]=(EditText)findViewById(R.id.et_1);
        et[2]=(EditText)findViewById(R.id.et_2);
        et[3]=(EditText)findViewById(R.id.et_3);
        et[4]=(EditText)findViewById(R.id.et_4);
        et[5]=(EditText)findViewById(R.id.et_5);
        et[6]=(EditText)findViewById(R.id.et_6);
        et[7]=(EditText)findViewById(R.id.et_7);
        et[8]=(EditText)findViewById(R.id.et_8);
        et[9]=(EditText)findViewById(R.id.et_9);
        et[10]=(EditText)findViewById(R.id.et_10);
        et[11]=(EditText)findViewById(R.id.et_11);
        et[12]=(EditText)findViewById(R.id.et_12);
        et[13]=(EditText)findViewById(R.id.et_13);
        et[14]=(EditText)findViewById(R.id.et_14);
        et[15]=(EditText)findViewById(R.id.et_15);
        et[16]=(EditText)findViewById(R.id.et_16);
        et[17]=(EditText)findViewById(R.id.et_17);
        et[18]=(EditText)findViewById(R.id.et_18);
        et[19]=(EditText)findViewById(R.id.et_19);
        et[20]=(EditText)findViewById(R.id.et_20);
        et[21]=(EditText)findViewById(R.id.et_21);
        et[22]=(EditText)findViewById(R.id.et_22);
        et[23]=(EditText)findViewById(R.id.et_23);
        et[24]=(EditText)findViewById(R.id.et_24);
        for (int i=0;i<25;i++){
            rl[i].setVisibility(View.GONE);
        }

        tv_n=(TextView)findViewById(R.id.xj_name);
        tv_p=(TextView)findViewById(R.id.xj_param);
        tv_r=(TextView)findViewById(R.id.xj_rela);
        et_beizhu=(EditText)findViewById(R.id.et_beizhu);
        check=(CheckBox)findViewById(R.id.check);
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
        rl_sub=(Button)findViewById(R.id.rl_sub);
        rl_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(id)){
                    Toast.makeText(getApplicationContext(),"请先扫码",Toast.LENGTH_LONG).show();
                }else{
                    JSONObject jsonParams = new JSONObject();
                    JSONObject jsontask = new JSONObject();
                    try {
                        jsonParams.put("a",local);
                        jsonParams.put("id",id);
                        jsonParams.put("name",name);
                        jsonParams.put("patrolMan",userName);
                        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        jsonParams.put("patrolTime",f.format(new Date()));

                    if(check.isChecked()){
                        jsonParams.put("abnormalNormal","不正常");
                        jsonParams.put("reason",et_beizhu.getText().toString()+"");
                        jsontask.put("content",et_beizhu.getText().toString());
                        jsontask.put("time",f.format(new Date()));
                        jsontask.put("name",name);
                        jsontask.put("id",id);
                        jsontask.put("type","巡检任务");
                        jsontask.put("person",userName);
                        jsontask.put("state","0");
                        sendTask(jsontask.toString());
                    }else{
                        jsonParams.put("abnormalNormal","正常");
                    }
                    if("给排水".equals(belong)){
                        jsonParams.put("waterlevel",et[0].getText().toString());
                        jsonParams.put("pressure",et[1].getText().toString());
                    }else if("发电机".equals(belong)){
                        jsonParams.put("battery",et[2].getText().toString());
                    }else if("强电".equals(belong)){
                        Log.e("source","intype2");
                        jsonParams.put("transformer",et[3].getText().toString());
                        jsonParams.put("voltage",et[4].getText().toString());
                        jsonParams.put("electric",et[5].getText().toString());
                        jsonParams.put("powerFactor",et[6].getText().toString());
                    }else if("空调".equals(belong)){
                        jsonParams.put("chilledWater",et[7].getText().toString());
                        jsonParams.put("chilledWaterA",et[8].getText().toString());
                        jsonParams.put("freezingWaterPressure",et[9].getText().toString());
                        jsonParams.put("freezinginletPressure",et[10].getText().toString());
                        jsonParams.put("coolingWaterTemperature",et[11].getText().toString());
                        jsonParams.put("coolingInletTemperature",et[12].getText().toString());
                        jsonParams.put("coolingWaterPressure",et[13].getText().toString());
                        jsonParams.put("coolingInletPressure",et[14].getText().toString());
                        jsonParams.put("inspiratory",et[15].getText().toString());
                        jsonParams.put("evaporating",et[16].getText().toString());
                        jsonParams.put("exhaustPressure",et[17].getText().toString());
                        jsonParams.put("condensing",et[18].getText().toString());
                        jsonParams.put("exhaustTemperature",et[19].getText().toString());
                        //jsonParams.put("superheat",et[20].getText().toString());
                        jsonParams.put("fuelPressure",et[21].getText().toString());
                        jsonParams.put("pressureDifference",et[22].getText().toString());


                    }else if("电梯".equals(belong)){
                        jsonParams.put("temperature",et[23].getText().toString());
                        jsonParams.put("humidity",et[24].getText().toString());
                    }

                        //json.put("jsonstr",jsonParams);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String posturls = "http://119.23.37.145:8080/S2SH/Inspectiold.do";
                    HttpTool tol = new HttpTool(posturls);
                    tol.setBody("jsonstr="+jsonParams.toString());
                    Log.e("check",jsonParams.toString());
                    tol.setHandler(handler);
                    new Thread(tol).start();
                }


            }
        });
        rl_check_sm=(RelativeLayout)findViewById(R.id.rl_check_sm);
        rl_check_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RepotActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    private void sendTask(String s) {
        Log.e("source",s);
        String posturls = "http://119.23.37.145:8080/S2SH/insertTaskld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setBody("jsonstr="+s);
        tol.setHandler(ehandler);
        new Thread(tol).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                //CodeString=data.getStringExtra("scan_result");
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                id=content;
                //scan_tv.setText("解码结果： \n" + content);
                Toast.makeText(getApplicationContext(),"扫码结果：" + content,Toast.LENGTH_LONG).show();

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
    }*/
}
