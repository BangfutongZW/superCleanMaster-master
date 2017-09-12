package com.yzy.supercleanmaster.ui;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import java.util.Date;
import java.util.List;

public class PlanListActivity extends Activity {
    private ListView pListView;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            msgStr = msgStr.substring(msgStr.indexOf("{")+1,msgStr.lastIndexOf("}"));
            Log.e("plans",msgStr);
            if(msgStr!=null&&!"".equals(msgStr)){
                String[] strname=msgStr.split(",");
                final List list = java.util.Arrays.asList(strname);
                //initExpandListView();
                pListView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return list.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextView mText=new TextView(getApplicationContext());
                        mText.setText((String)getItem(position));
                        return mText;
                    }
                });
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        pListView=(ListView)findViewById(R.id.plistview);
        getData();
    }

    private void getData() {
        Date currt=new Date();
        String posturls = UrlStone.Url+"planld.do";
        posturls=posturls+"?month="+currt.getMonth();
        HttpTool tol = new HttpTool(posturls);

        tol.setHandler(handler);
        new Thread(tol).start();
    }

}
