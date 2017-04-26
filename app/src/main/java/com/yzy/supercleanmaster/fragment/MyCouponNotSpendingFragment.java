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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yzy.supercleanmaster.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 未消費券Fragment类
 */
@SuppressLint("InflateParams")
public class MyCouponNotSpendingFragment extends Fragment{

	private  LineChart lineChart;
	private XAxis xAxis;
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_peid_layout, container, false);
		ButterKnife.inject(this, view);
		initLine(view);
		return view;
	}

	private void initLine(View v) {
		//ChartUtils.initChart(chart);
		//ChartUtils.notifyDataSetChanged(chart, getData(), ChartUtils.dayValue);
		lineChart=(LineChart)v.findViewById(R.id.lineChart);
		initChart(lineChart);

	}

	public void initChart(LineChart chart) {
		// 不显示数据描述
		chart.getDescription().setEnabled(false);
		// 没有数据的时候，显示“暂无数据”
		chart.setNoDataText("暂无数据");
		// 不显示表格颜色
		chart.setDrawGridBackground(false);
		// 不可以缩放
		chart.setScaleEnabled(false);
		// 不显示y轴右边的值
		chart.getAxisRight().setEnabled(false);
		// 不显示图例
		Legend legend = chart.getLegend();
		legend.setEnabled(false);
		// 向左偏移15dp，抵消y轴向右偏移的30dp
		chart.setExtraLeftOffset(-15);

		XAxis xAxis = chart.getXAxis();
		// 不显示x轴
		xAxis.setDrawAxisLine(true);
		// 设置x轴数据的位置
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		xAxis.setTextColor(Color.GRAY);
		xAxis.setTextSize(10);
		xAxis.setGridColor(Color.parseColor("#30FFFFFF"));
		// 设置x轴数据偏移量
		xAxis.setYOffset(-12);

		YAxis yAxis = chart.getAxisLeft();
		// 不显示y轴
		yAxis.setDrawAxisLine(true);
		// 设置y轴数据的位置
		yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
		// 不从y轴发出横向直线
		yAxis.setDrawGridLines(true);
		yAxis.setTextColor(Color.GRAY);
		yAxis.setTextSize(10);
		// 设置y轴数据偏移量
		yAxis.setXOffset(15);
		yAxis.setYOffset(-3);
		yAxis.setAxisMinimum(0);

		//Matrix matrix = new Matrix();
		// x轴缩放1.5倍
		//matrix.postScale(1.5f, 1f);
		// 在图表动画显示之前进行缩放
		//chart.getViewPortHandler().refresh(matrix, chart, false);
		// x轴执行动画
		//chart.animateX(2000);
		List<Entry> yValues =new ArrayList<Entry>();
		yValues.add(new Entry(0,561));
		yValues.add(new Entry(1,746));
		yValues.add(new Entry(2,567));
		yValues.add(new Entry(3,566));
		yValues.add(new Entry(4,547));
		yValues.add(new Entry(5,569));
		yValues.add(new Entry(6,578));
		yValues.add(new Entry(7,567));
		yValues.add(new Entry(8,566));
		yValues.add(new Entry(9,547));
		yValues.add(new Entry(10,569));
		yValues.add(new Entry(11,578));


		final String[] week = {"6:30","7:00", "7:30","8:00","8:30", "9:00","9:30", "10:00", "10:30", "11:00", "11:30", "12:00"};
		chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return week[(int) value];
			}
		});
		LineDataSet lineDataSet;

		lineDataSet = new LineDataSet(yValues, "");
		// 设置曲线颜色
		lineDataSet.setColor(Color.parseColor("#36B5E5"));
		// 设置平滑曲线
		lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		// 不显示坐标点的小圆点
		lineDataSet.setDrawCircles(false);
		// 不显示坐标点的数据
		lineDataSet.setDrawValues(false);
		// 不显示定位线
		lineDataSet.setHighlightEnabled(false);

		LineData data = new LineData(lineDataSet);
		chart.setData(data);


	}




}
