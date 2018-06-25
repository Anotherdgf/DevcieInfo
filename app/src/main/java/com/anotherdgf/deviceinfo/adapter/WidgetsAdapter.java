package com.anotherdgf.deviceinfo.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;

/**
 * Created by denggaofeng on 2018/6/25.
 */

public class WidgetsAdapter extends BaseAdapter {
    private final static String TAG = "WidgetsAdapter";
    private Context mContext;
    private int[] widgetList;
    private String[] entries;

    public WidgetsAdapter(Context mContext,int[] array ,String[] entries){
        this.mContext = mContext;
        this.widgetList = array;
        this.entries = entries;
        notifyDataSetChanged();
    }

    public void setData(){
        notifyDataSetChanged();
    }

    public void addData(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return widgetList.length;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        Log.d(TAG,"position"+position);
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_widget_holder, parent,false);
            viewHolder.tv_widget = convertView.findViewById(R.id.widget_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_widget.setText(widgetList[position]);
        viewHolder.tv_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent();
                    intent.setClassName(mContext,entries[position]);
//                    Toast.makeText(mContext,"点击"+entries[position],Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(mContext,"Package not found!",Toast.LENGTH_SHORT).show();
                }catch (ArrayIndexOutOfBoundsException e){
                    Toast.makeText(mContext,"开发中...",Toast.LENGTH_SHORT).show();
                }
            }}
        );
        return convertView;
    }

    @Override
    public String getItem(int position){
//        return ;
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    public class ViewHolder{
        TextView tv_widget;
    }
}
