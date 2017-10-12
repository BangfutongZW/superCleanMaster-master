package com.yzy.supercleanmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.model.AlarmInfo;
import com.yzy.supercleanmaster.model.Maintenance;

import java.util.List;

public class ByplanShowAdapter extends BaseAdapter {

    public List<Maintenance> aiList;
    LayoutInflater infater = null;
    private Context mContext;

    public ByplanShowAdapter(Context context, List<Maintenance> aiList) {
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
            convertView = infater.inflate(R.layout.recycler_list,
                    parent, false);
            holder = new ViewHolder();
            holder.mainText=(TextView)convertView.findViewById(R.id.mainText);
            holder.appName = (TextView) convertView
                    .findViewById(R.id.app_name);
            holder.size = (TextView) convertView
                    .findViewById(R.id.app_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Maintenance  item = (Maintenance) getItem(position);
        if (item != null) {
            //holder.appIcon.setImageDrawable(item.getApplicationIcon());
            /*holder.appName.setText(item.getTagName());
            holder.size.setText(item.getEventTime());
            if(item.getEventType().equals("0")){
                holder.mainText.setText("报警");
            }else if(item.getEventType().equals("1")){
                holder.mainText.setText("报警恢复");
            };*/
        }
        return convertView;
    }


    class ViewHolder {
        TextView mainText;
        TextView appName;
        TextView size;


        String packageName;
    }

}
