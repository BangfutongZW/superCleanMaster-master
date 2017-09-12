package com.yzy.supercleanmaster.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.StatusExpandAdapter;
import com.yzy.supercleanmaster.model.ChildStatusEntity;
import com.yzy.supercleanmaster.model.GroupStatusEntity;
import com.yzy.supercleanmaster.model.UrlStone;
import com.yzy.supercleanmaster.utils.HttpTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class checkPlanActivity extends Activity {
    private ExpandableListView expandlistView;
    private StatusExpandAdapter statusAdapter;

    private Context context;
    private String[] strname;

    private TextView time_text;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=(String) msg.obj;
            if (msgStr != null) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
            }
            if(msgStr!=null&&msgStr.length()>3){
                msgStr = msgStr.substring(1,msgStr.length()-2);
                strname=msgStr.split(",");
                Log.e("plan", Arrays.toString(strname));
                initExpandListView();
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_plan);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        expandlistView = (ExpandableListView) findViewById(R.id.expandlist);
        time_text=(TextView)findViewById(R.id.time_text);

        getData();
    }

    private void getData() {
        Date currt=new Date();
        int month=currt.getMonth()+1;
        int year=currt.getYear();
        time_text.setText(year+"年"+month+"月");
        String posturls = UrlStone.Url+"planld.do";
        posturls=posturls+"?month="+month;
        HttpTool tol = new HttpTool(posturls);

        tol.setHandler(handler);
        new Thread(tol).start();
    }

    /**
     * 初始化可拓展列表
     */
    private void initExpandListView() {
        statusAdapter = new StatusExpandAdapter(context, getListData());
        expandlistView.setAdapter(statusAdapter);
        expandlistView.setGroupIndicator(null); // 去掉默认带的箭头
        expandlistView.setSelection(0);// 设置默认选中项
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandlistView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandlistView.expandGroup(i);
        }
        expandlistView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        /*expandlistView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });*/
    }

    /*@Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }*/

    private List<GroupStatusEntity> getListData() {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        List<String> list4 = new ArrayList<String>();
        List<String> list5 = new ArrayList<String>();
        List<String> list6 = new ArrayList<String>();
        List<GroupStatusEntity> groupList;

        String[] strArray = new String[] { "-1F低压配电房", "-3F空调机房", "-3F水泵房","3F消防泵房","28F稳压泵房","发电机" };
        for (String name:strname
             ) {
            if(name.contains("进线柜")){
                list1.add(name);
            }else if(name.contains("空调")){
                list2.add(name);
            }else if(name.contains("水泵")){
                list3.add(name);
            }else if(name.contains("消火")){
                list4.add(name);
            }else if(name.contains("稳压泵")){
                list5.add(name);
            }else if(name.contains("发电机")){
                list6.add(name);
            }
        }
        String[][] childTimeArray = new String[][] {
                (String[])list1.toArray(new String[list1.size()]),
                (String[])list2.toArray(new String[list2.size()]),
                (String[])list3.toArray(new String[list3.size()]),
                (String[])list4.toArray(new String[list4.size()]),
                (String[])list5.toArray(new String[list5.size()]),
                (String[])list6.toArray(new String[list6.size()])};
        groupList = new ArrayList<GroupStatusEntity>();
        for (int i = 0; i < strArray.length; i++) {
            GroupStatusEntity groupStatusEntity = new GroupStatusEntity();
            groupStatusEntity.setGroupName(strArray[i]);

            List<ChildStatusEntity> childList = new ArrayList<ChildStatusEntity>();

            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildStatusEntity childStatusEntity = new ChildStatusEntity();
                childStatusEntity.setCompleteTime(childTimeArray[i][j]);
                childStatusEntity.setIsfinished(true);
                childList.add(childStatusEntity);
            }

            groupStatusEntity.setChildList(childList);
            groupList.add(groupStatusEntity);
        }
        return groupList;
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
