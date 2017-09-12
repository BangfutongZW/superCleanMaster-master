package com.yzy.supercleanmaster.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.Surveyelect;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


public class PeidianActionActivity extends Activity {
    private LineChart lineChart;
    private ProgressDialog proDialog;

    //public TextView tv_pd_dy;

    public TextView tv_pd_dlA;

    public TextView tv_pd_wd;

    public TextView tv_pd_dd;

    public TextView tv_pd_jx;
    Handler sHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=null;
            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                proDialog.dismiss();
                Log.e("speid",msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };
            if (msgStr != null&&msgStr.length()>0) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("["),msgStr.lastIndexOf("]")+1);
                ArrayList<Surveyelect> list=new ArrayList<Surveyelect>();
                try {
                    JSONArray array=new JSONArray(msgStr);
                    for (int i=0;i<array.length();i++){
                        Surveyelect s=new Surveyelect();
                        JSONObject obj=array.getJSONObject(i);
                        s.setWenduB(obj.getString("wenduB"));
                        s.setDianliuB(obj.getString("dianliuB"));
                        s.setDianyaB(obj.getString("dianyaB"));
                        s.setDianliang(obj.getInt("dianliang"));
                        s.setTime(obj.getString("time"));
                        list.add(s);
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "数据库解析出错...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                initChart(lineChart,list);
                initSummary(list);
            }else {
                Toast.makeText(getApplicationContext(), "网络连接出错...", Toast.LENGTH_SHORT).show();
            }




            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peidian_action);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        String index="";
        index=getIntent().getStringExtra("index");
        lineChart=(LineChart)findViewById(R.id.lineChart_dl);
        getData(index);

    }
    public void getData(String index){
        proDialog =new ProgressDialog(PeidianActionActivity.this);
        proDialog.setMessage("正在加载中...");
        proDialog.show();

        String posturls = UrlStone.Url+"surveyelectld.do";
        posturls=posturls+"?index="+index;
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(sHandler);
        new Thread(tol).start();
    }

    public void initSummary(ArrayList<Surveyelect> list){
        //tv_pd_dy=(TextView)findViewById(R.id.tv_pd_dy);
        tv_pd_dlA=(TextView)findViewById(R.id.tv_pd_dlA);
        tv_pd_wd=(TextView)findViewById(R.id.tv_pd_wd);
        tv_pd_dd=(TextView)findViewById(R.id.tv_pd_dd);
        tv_pd_jx=(TextView)findViewById(R.id.tv_pd_jx);

        Surveyelect s=list.get(list.size()-1);
        //tv_pd_dy.setText(s.getDianyaB());
        tv_pd_dlA.setText(s.getDianliuB());
        tv_pd_wd.setText((Double.valueOf(s.getWenduB()))+"");
        tv_pd_dd.setText((int)s.getDianliang()/10+"");
        tv_pd_jx.setText(s.getMachine());
    }

    public void initChart(LineChart chart,ArrayList<Surveyelect> list) {
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
        /*yValues.add(new Entry(0,561));
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
        yValues.add(new Entry(11,578));*/
        final String[] week=new String[list.size()];
        for(int i=0;i<list.size();i++){
            yValues.add(new Entry((float)i,Float.parseFloat(list.get(i).getDianliuB())));
            week[i]=list.get(i).getTime().substring(0,5);
        }
        //final String[] week = {"6:30","7:00", "7:30","8:00","8:30", "9:00","9:30", "10:00", "10:30", "11:00", "11:30", "12:00"};
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
