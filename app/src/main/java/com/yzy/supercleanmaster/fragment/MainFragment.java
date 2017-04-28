package com.yzy.supercleanmaster.fragment;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.update.UmengUpdateAgent;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseFragment;
import com.yzy.supercleanmaster.model.SDCardInfo;
import com.yzy.supercleanmaster.ui.AlarmListActivity;
import com.yzy.supercleanmaster.ui.CheckActivity;
import com.yzy.supercleanmaster.ui.DefineActivity;
import com.yzy.supercleanmaster.ui.LoginActivity;
import com.yzy.supercleanmaster.utils.AppUtil;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.utils.StorageUtil;
import com.yzy.supercleanmaster.widget.circleprogress.ArcProgress;
import com.zjf.rieffect.RieffectLayout;

import org.json.JSONException;
import org.json.JSONObject;

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


    Context mContext;

    private Timer timer;
    private Timer timer2;

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


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        mContext = getActivity();

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
                    startActivity(DefineActivity.class);
                    break;
                case R.id.ll_demand:
                    startActivity(AlarmListActivity.class);
                    break;
                case R.id.ll_question:
                    startActivity(CheckActivity.class);
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
        String posturls = "http://119.23.37.145:8080/S2SH/nowfuzaild.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }

    private void fillData(double fuzai) {
        // TODO Auto-generated method stub
        timer = null;
        timer2 = null;
        timer = new Timer();
        timer2 = new Timer();


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
        timer2.cancel();
        super.onDestroy();
    }
}
