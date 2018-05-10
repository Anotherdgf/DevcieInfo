package com.anotherdgf.deviceinfo.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.bean.ApplicationBean;

import java.util.ArrayList;

/**
 * Created by denggaofeng on 2018/4/20.
 */

public class ApplicationAdapter extends BaseAdapter{

    private final static String TAG = "ApplicationAdapter";
    private Context mContext;
    private ArrayList<ApplicationBean> mApplicationList;

    public ApplicationAdapter(Context mContext, ArrayList<ApplicationBean> mApplicationList){
        this.mContext = mContext;
        this.mApplicationList = mApplicationList;
        notifyDataSetChanged();
    }

    public void setData(ArrayList<ApplicationBean> mApplicationList){
        this.mApplicationList = mApplicationList;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<ApplicationBean> mApplicationList){
        this.mApplicationList = mApplicationList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return mApplicationList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        Log.d(TAG,"position"+position);
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app_holder, parent,false);
            viewHolder.iv_apkIcon = convertView.findViewById(R.id.app_icon);
            viewHolder.tv_appName = convertView.findViewById(R.id.app_name);
            viewHolder.tv_applicationName = convertView.findViewById(R.id.package_name);
            viewHolder.iv_app_open = convertView.findViewById(R.id.app_open);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.iv_apkIcon.setImageDrawable(mApplicationList.get(position).getApkIcon());
        viewHolder.tv_appName.setText(mApplicationList.get(position).getAppName());
        viewHolder.tv_applicationName.setText(mApplicationList.get(position).getPackageName());
        viewHolder.iv_app_open.setImageResource(R.drawable.ic_exit_to_app);
        viewHolder.iv_app_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(viewHolder.tv_applicationName.getText().toString());
                try{
                    mContext.startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(mContext,"Package not found!",Toast.LENGTH_SHORT);
                }
            }
        });
        return convertView;
    }

    @Override
    public ApplicationBean getItem(int position){
        return mApplicationList.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    public class ViewHolder{
        TextView tv_appName,tv_applicationName;
        ImageView iv_apkIcon,iv_app_open;
    }
}
