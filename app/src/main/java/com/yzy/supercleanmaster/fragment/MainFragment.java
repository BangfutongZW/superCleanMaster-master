package com.yzy.supercleanmaster.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jauker.widget.BadgeView;
import com.umeng.update.UmengUpdateAgent;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseFragment;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.Nownh;
import com.yzy.supercleanmaster.model.SDCardInfo;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.ui.AlarmListActivity;
import com.yzy.supercleanmaster.ui.ApdActivity;
import com.yzy.supercleanmaster.ui.CheckActivity;
import com.yzy.supercleanmaster.ui.DefineActivity;
import com.yzy.supercleanmaster.ui.GpdActivity;
import com.yzy.supercleanmaster.ui.LoginActivity;
import com.yzy.supercleanmaster.ui.NhActivity;
import com.yzy.supercleanmaster.ui.NhBasicActivity;
import com.yzy.supercleanmaster.ui.RepotActivity;
import com.yzy.supercleanmaster.ui.SaomActivity;
import com.yzy.supercleanmaster.ui.WaterActivity;
import com.yzy.supercleanmaster.ui.WaterDfActivity;
import com.yzy.supercleanmaster.utils.AppUtil;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.utils.StorageUtil;
import com.yzy.supercleanmaster.widget.circleprogress.ArcProgress;
import com.zjf.rieffect.RieffectLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainFragment extends BaseFragment {


    @InjectView(R.id.arc_process)
    ArcProgress arcProcess;
    @InjectView(R.id.btn_go_wash_main)
    Button btn_go_wash_main;
    @InjectView(R.id.ll_demand)
    LinearLayout ll_demand;
    @InjectView(R.id.ll_question)
    LinearLayout ll_question;
    @InjectView(R.id.card1)
    RelativeLayout card1;
    @InjectView(R.id.card2)
    RelativeLayout card2;
    @InjectView(R.id.card3)
    RelativeLayout card3;
    @InjectView(R.id.card4)
    RelativeLayout card4;
    @InjectView(R.id.tv_nh)
    TextView tv_nh;
    @InjectView(R.id.tv_airnh)
    TextView tv_airnh;
    @InjectView(R.id.pd_main_1)
    TextView pd_main_1;
    @InjectView(R.id.pd_main_2)
    TextView pd_main_2;
    @InjectView(R.id.pd_main_3)
    TextView pd_main_3;
    @InjectView(R.id.pd_main_4)
    TextView pd_main_4;
    @InjectView(R.id.pd_main_5)
    TextView pd_main_5;
    @InjectView(R.id.iv_redbag)
    ImageView iv_redbag;
    @InjectView(R.id.iv_car_logo)
    ImageView iv_car_logo;
    @InjectView(R.id.tv_kt_mian1)
    TextView tv_kt_mian1;
    @InjectView(R.id.tv_kt_mian2)
    TextView tv_kt_mian2;
    @InjectView(R.id.tv_kt_mian3)
    TextView tv_kt_mian3;
    @InjectView(R.id.tv_kt_mian4)
    TextView tv_kt_mian4;
    @InjectView(R.id.tv_kt_mian5)
    TextView tv_kt_mian5;

    Context mContext;
    BadgeView badgeView1;
    BadgeView badgeView2;

    private Timer timer;

    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=null;
            msgStr= (String) msg.obj;

            Log.e("fuzai",msgStr);
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

            try {
                JSONObject obj = new JSONObject(msgStr);
                String fuzai=obj.getString("x");
                Log.e("fuzaiData",fuzai);
                fillData(Double.parseDouble(fuzai));
            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
    Handler tHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=null;
            msgStr= (String) msg.obj;

            Log.e("fuzai",msgStr);
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
            ArrayList<Nownh> nList=new ArrayList<Nownh>();
            try {
                JSONArray array=new JSONArray(msgStr);
                for (int i=0;i<array.length();i++){
                    Nownh n=new Nownh();
                    JSONObject obj=array.getJSONObject(i);
                    n.setDate(obj.getString("date"));
                    n.setNh(obj.getInt("nh"));
                    n.setNhair(obj.getInt("nhair"));
                    n.setTime(obj.getString("time"));
                    nList.add(n);
                    /*a.setState(obj.getString("state"));
                    alist.add(a);*/
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            fillNh(nList);
            super.handleMessage(msg);
        }
    };
    Handler rHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                Log.e("smac",msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr != null&&msgStr.length()>0) {

                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                Log.e("smac",msgStr);
                String[] sa=msgStr.split(",");
                for(String a:sa){
                    updateView(a.trim());
                }
            }else {
                Toast.makeText(getActivity(), "网络连接出错...", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    Handler yHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                Log.e("smac",msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr != null&&msgStr.length()>0) {

                msgStr = msgStr.substring(msgStr.indexOf("[")+1,msgStr.lastIndexOf("]"));
                Log.e("amac",msgStr);
                String[] sa=msgStr.split(",");
                for(String a:sa){
                    updateAirView(a.trim());
                }
            }else {
                Toast.makeText(getActivity(), "无运行主机...", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    Handler oHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("baojcount","hand");
            String msgStr=null;
            msgStr= (String) msg.obj;

            Log.e("baojcount",msgStr);
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);

            try {
                JSONObject obj = new JSONObject(msgStr);
                String count=obj.getString("x");
                Log.e("baojcount",count);
                badgeView1.setBadgeCount(Integer.parseInt(count));
                //fillData(Double.parseDouble(fuzai));
            }catch (JSONException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        mContext = getActivity();

        badgeView1 = new com.jauker.widget.BadgeView(mContext);
        badgeView1.setTargetView(iv_redbag);
        badgeView2 = new com.jauker.widget.BadgeView(mContext);
        badgeView2.setTargetView(iv_car_logo);
        badgeView2.setBadgeCount(1);

        List<View> lists = new ArrayList<View>();
        lists.add(btn_go_wash_main);
        lists.add(ll_demand);
        lists.add(ll_question);
        lists.add(card1);
        lists.add(card2);
        lists.add(card3);
        lists.add(card4);
        initViewListener(lists);
        return view;
    }
    private void initViewListener(List<View> lists)
    {
        for(int i = 0; i<lists.size(); ++i){
            RieffectLayout.on(lists.get(i))
                    .rippleColor(Color.parseColor("#D3D3D3"))
                    .rippleAlpha(1.0f)
                    .rippleHover(true)
                    .create();
            lists.get(i).setOnLongClickListener(new myLongClickListener());
            lists.get(i).setOnClickListener(new myClickListener());
        }
    }
    class myLongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {

            return false;
        }
    }
    /*class myClickListener implements View.OnClickListener{

    }*/
    class  myClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_go_wash_main:
                    //startActivity(DefineActivity.class);
                    startActivity(NhBasicActivity.class);
                    break;
                case R.id.ll_demand:
                    startActivity(AlarmListActivity.class);
                    break;
                case R.id.ll_question:
                    startActivity(CheckActivity.class);
                    break;
                case R.id.card1:
                    startActivity(GpdActivity.class);
                    break;
                case R.id.card2:
                    startActivity(ApdActivity.class);
                    break;
                case R.id.card3:
                    //Toast.makeText(mContext, "正在开发中...", Toast.LENGTH_SHORT).show();
                    if("df".equals(UrlStone.local)){
                        startActivity(WaterDfActivity.class);
                    }else{
                        startActivity(WaterActivity.class);
                    }
                    break;
                case R.id.card4:
                    startActivity(SaomActivity.class);
                    break;
                default:
                    break;
            }

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        initData();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        UmengUpdateAgent.update(getActivity());
    }
    private void initData() {
        String posturls = UrlStone.Url+"nowfuzaild.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();

        String posturls1 = UrlStone.Url+"nowTotalNhld.do";
        HttpTool tol1 = new HttpTool(posturls1);
        tol1.setHandler(tHandler);
        new Thread(tol1).start();

        String posturls2 = UrlStone.Url+"checkMacld.do";
        HttpTool tol2 = new HttpTool(posturls2);
        tol2.setHandler(rHandler);
        new Thread(tol2).start();

        String posturls3 = UrlStone.Url+"checkAirMacld.do";
        HttpTool tol3 = new HttpTool(posturls3);
        tol3.setHandler(yHandler);
        new Thread(tol3).start();


        SharedPreferences sp=mContext.getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
        String id=sp.getString("ID","");
        if(id==null||id.equals("")){
            id="2548";
        }
        String posturls4 = UrlStone.commmonUrl+"alarmcountld.do";
        posturls4 =posturls4+"?ID="+id+"&local="+UrlStone.local;
        Log.e("paoturl",posturls4);
        HttpTool tol4 = new HttpTool(posturls4);
        tol4.setHandler(oHandler);
        new Thread(tol4).start();
    }

    private void fillData(double fuzai) {
        // TODO Auto-generated method stub
        timer = null;
        timer = new Timer();


        long l = AppUtil.getAvailMemory(mContext);
        long y = AppUtil.getTotalMemory(mContext);
        //final double x = (((y - l) / (double) y) * 100);
        final double x=fuzai;
        //   arcProcess.setProgress((int) x);

        arcProcess.setProgress(0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (arcProcess.getProgress() >= (int) x) {
                            timer.cancel();
                        } else {
                            arcProcess.setProgress(arcProcess.getProgress() + 1);
                        }

                    }
                });
            }
        }, 50, 20);


    }
    private void fillNh(ArrayList<Nownh> nList) {
        int size =nList.size();
        if(size>0){
            float nh=nList.get(size-1).getNh()-nList.get(0).getNh();
            float airnh=nList.get(size-1).getNhair()-nList.get(0).getNhair();
            tv_nh.setText(""+(int)nh);
            tv_airnh.setText(""+(int)airnh);
        }
    }
    private void updateView(String a){
        switch (a){
            case "1":
                pd_main_1.setVisibility(View.VISIBLE);
                break;
            case "2":
                pd_main_2.setVisibility(View.VISIBLE);
                break;
            case "3":
                pd_main_3.setVisibility(View.VISIBLE);
                break;
            case "4":
                pd_main_4.setVisibility(View.VISIBLE);
            case "5":
                pd_main_5.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void updateAirView(String a){
        switch (a){
            case "1":
                tv_kt_mian1.setVisibility(View.VISIBLE);
                break;
            case "2":
                tv_kt_mian2.setVisibility(View.VISIBLE);
                break;
            case "3":
                tv_kt_mian3.setVisibility(View.VISIBLE);
                break;
            case "4":
                tv_kt_mian4.setVisibility(View.VISIBLE);
                break;
            case "5":
                tv_kt_mian5.setVisibility(View.VISIBLE);
                break;
        }
    }

    /*@OnClick(R.id.card1)
    void speedUp() {
        if(Constants.ISLOGIN){
            startActivity(DefineActivity.class);
        }else {
            startActivity(LoginActivity.class);
        }
    }

    @OnClick(R.id.card2)
    void rubbishClean() {
        startActivity(AlarmListActivity.class);
    }


    @OnClick(R.id.card3)
    void AutoStartManage() {
        startActivity(CheckActivity.class);
    }

    @OnClick(R.id.card4)
    void SoftwareManage() {
        startActivity(LoginActivity.class);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
