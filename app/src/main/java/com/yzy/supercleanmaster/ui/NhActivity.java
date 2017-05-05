package com.yzy.supercleanmaster.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.Dianlang;
import com.yzy.supercleanmaster.model.Nownh;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class NhActivity extends Activity {
    private BarChart barChart;
    private XAxis xAxis;
    private RelativeLayout rl_nh_month;

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
            setDayData(nList);
            super.handleMessage(msg);
        }
    };
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
            msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
            ArrayList<Dianlang> nList=new ArrayList<Dianlang>();
            try {
                JSONArray array=new JSONArray(msgStr);
                for (int i=0;i<array.length();i++){
                    Dianlang n=new Dianlang();
                    JSONObject obj=array.getJSONObject(i);
                    n.setDate(obj.getString("date"));
                    n.setNh(obj.getInt("nh"));
                    n.setNhair(obj.getInt("nhair"));
                    nList.add(n);
                    /*a.setState(obj.getString("state"));
                    alist.add(a);*/
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            //setDayData(nList);
            setMonthData(nList);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nh);
        initBar();
        rl_nh_month=(RelativeLayout)findViewById(R.id.rl_nh_month);
        rl_nh_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhmonthPost();
            }
        });
        NhdayPost();
    }

    private void NhdayPost() {
        String posturls = "http://119.23.37.145:8080/S2SH/nowTotalNhld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(tHandler);
        new Thread(tol).start();
    }
    private void NhmonthPost() {
        String posturls = "http://119.23.37.145:8080/S2SH/monthNhld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }
    private void setDayData(ArrayList<Nownh> nList){
        final ArrayList<String> xValues =new ArrayList<String>();
        ArrayList<BarEntry> yValues =new ArrayList<BarEntry>();
        for(int i=0;i<nList.size()-1;i++){
            xValues.add(nList.get(i).getTime().substring(0,5));
            yValues.add(new BarEntry((float)i,(nList.get(i+1).getNh()-nList.get(i).getNh())));
        }
        BarDataSet barDataSet=new BarDataSet(yValues,"日用电量");
        barChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int)value);
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.8f);
        //barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；
        barChart.animateY(1000);
        barChart.setData(barData);
        barChart.invalidate();
    }
    private void setMonthData(ArrayList<Dianlang> nList){
        final ArrayList<String> xValues =new ArrayList<String>();
        ArrayList<BarEntry> yValues =new ArrayList<BarEntry>();
        for(int i=0;i<nList.size()-1;i++){
            xValues.add(nList.get(i).getDate().substring(5));
            yValues.add(new BarEntry((float)i,(nList.get(i+1).getNh()-nList.get(i).getNh())));
        }
        BarDataSet barDataSet=new BarDataSet(yValues,"月度用电量");
        barChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int)value);
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.8f);
        //barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；
        barChart.animateY(1000);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void initBar() {
        barChart=(BarChart)findViewById(R.id.barChart_nh);
        xAxis=barChart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        Legend legend = barChart.getLegend();//隐藏比例尺
        legend.setEnabled(true);

    }

}
