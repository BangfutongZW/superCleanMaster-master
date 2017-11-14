package com.yzy.supercleanmaster.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseFragment;
import com.yzy.supercleanmaster.model.Nownh;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;


@SuppressLint("InflateParams")
public class DayNhSpentFragment extends BaseFragment{
	private BarChart barChart;
	private XAxis xAxis;

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

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_daynh_layout, container, false);
		ButterKnife.inject(this, view);
		initBar(view);
		NhdayPost();
		return view;
	}
	private void NhdayPost() {
		String posturls = UrlStone.Url+"nowTotalNhld.do";
		HttpTool tol = new HttpTool(posturls);
		tol.setHandler(tHandler);
		new Thread(tol).start();
	}
	private void initBar(View v) {
		barChart=(BarChart)v.findViewById(R.id.barChart_daynh);
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
		Legend legend = barChart.getLegend();
		legend.setEnabled(true);

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
		barDataSet.setColor(Color.rgb(235, 74, 75));
		BarData barData = new BarData(barDataSet);
		barData.setBarWidth(0.8f);
		//barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；
		barChart.animateY(1000);
		barChart.setData(barData);
		barChart.invalidate();
	}
}
