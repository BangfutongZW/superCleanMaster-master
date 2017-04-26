package com.yzy.supercleanmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.CacheListItem;

import java.util.ArrayList;
import java.util.List;

public class AlarmShowAdapter extends BaseAdapter {

    public List<AlarmInfo> aiList;
    LayoutInflater infater = null;
    private Context mContext;

    public AlarmShowAdapter(Context context, List<AlarmInfo> aiList) {
        infater = LayoutInflater.from(context);
        mContext = context;
        this.aiList = aiList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return aiList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return aiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = infater.inflate(R.layout.listview_rublish_clean,
                    parent, false);
            holder = new ViewHolder();
            holder.appIcon = (ImageView) convertView
                    .findViewById(R.id.app_icon);
            holder.appName = (TextView) convertView
                    .findViewById(R.id.app_name);
            holder.size = (TextView) convertView
                    .findViewById(R.id.app_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AlarmInfo item = (AlarmInfo) getItem(position);
        if (item != null) {
            //holder.appIcon.setImageDrawable(item.getApplicationIcon());
            holder.appName.setText(item.getTagName());
            holder.size.setText(item.getEventTime());
            if(item.getEventType().equals("0")){
                holder.appIcon.setImageResource(R.drawable.warming);
            }else if(item.getEventType().equals("1")){
                holder.appIcon.setImageResource(R.drawable.unwarming);
            };
        }
        return convertView;
    }


    class ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView size;


        String packageName;
    }

}
