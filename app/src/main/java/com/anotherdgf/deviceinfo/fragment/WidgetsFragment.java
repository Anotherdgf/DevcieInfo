package com.anotherdgf.deviceinfo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.activity.DialogsActivity;
import com.anotherdgf.deviceinfo.activity.RecyclerViewActivity;
import com.anotherdgf.deviceinfo.adapter.WidgetsAdapter;

import java.util.ArrayList;

/**
 * Created by denggaofeng on 2018/4/19.
 */

public class WidgetsFragment extends BaseFragment {
    private final static String TAG = "WidgetsFragment";

    private ListView widgetListView;

    private static final int[] WIDGETS_STRINGS = {
            R.string.dialog_widgets,
            R.string.recyclerview_widgets,
            R.string.lbs_amap
    };

    private static final String[] ENTRY_ACTIVITYS = {
            DialogsActivity.class.getName(),
            RecyclerViewActivity.class.getName()
    };

    public static WidgetsFragment newInstance() {
        return new WidgetsFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        widgetListView = view.findViewById(R.id.widgets_listview);

        WidgetsAdapter widgetsAdapter = new WidgetsAdapter(getContext(), WIDGETS_STRINGS ,ENTRY_ACTIVITYS);
        widgetListView.setAdapter(widgetsAdapter);
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_widgets;
    }
}
