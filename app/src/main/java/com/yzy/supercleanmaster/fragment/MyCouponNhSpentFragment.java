package com.yzy.supercleanmaster.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yzy.supercleanmaster.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 我的Fragment类
 */
@SuppressLint("InflateParams")
public class MyCouponNhSpentFragment extends Fragment{
	private BarChart barChart;
	private XAxis xAxis;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nh_layout, container, false);
		ButterKnife.inject(this, view);
		initBar(view);
		return view;
	}
	private void initBar(View v) {
		barChart=(BarChart)v.findViewById(R.id.barChart);
		xAxis=barChart.getXAxis();
		xAxis.setDrawAxisLine(true);
		xAxis.setDrawGridLines(false);
		barChart.getAxisLeft().setDrawAxisLine(false);
		barChart.setTouchEnabled(false);
		barChart.setDragEnabled(true);
		barChart.setScaleEnabled(true);
		barChart.getDescription().setEnabled(false);
		//barChart.setDescription("四个季度");// 数据描述
		barChart.getAxisLeft().setEnabled(false);
		barChart.getAxisRight().setEnabled(false);

		Legend legend = barChart.getLegend();//隐藏比例尺
		legend.setEnabled(true);

		final ArrayList<String> xValues =new ArrayList<String>();
		xValues.add("前天");
		xValues.add("昨天");
		xValues.add("今天");

		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		ArrayList<BarEntry> yValues =new ArrayList<BarEntry>();
		yValues.add(new BarEntry(0,2688));
		yValues.add(new BarEntry(1,2746));
		yValues.add(new BarEntry(2,1567));

		//5、设置显示的数字为整形
		BarDataSet barDataSet=new BarDataSet(yValues,"日用电量");
		/*barDataSet.setValueFormatter(new IValueFormatter() {
			@Override
			public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
				int n=(int)value;
				return  n+"";
			}
		});*/
		barChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return xValues.get((int)value);
			}
		});
		//6、设置柱状图的颜色
		barDataSet.setColors(new int[]{Color.rgb(68, 85, 136), Color.rgb(153, 170, 119),
				Color.rgb(221, 187, 170)});
		//7、显示，柱状图的宽度和动画效果

		BarData barData = new BarData(barDataSet);
		barData.setBarWidth(0.8f);
		//barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；
		barChart.animateY(1000);
		barChart.setData(barData);
	}
}
