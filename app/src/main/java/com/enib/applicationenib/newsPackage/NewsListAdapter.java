package com.enib.applicationenib.newsPackage;

/**
 * Created by Valentin on 21/11/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enib.applicationenib.appCodeSource.application.News;
import com.enib.applicationenib.R;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {
    private static final String TAG = "TestFragment";
    Context context;
    List<News> rowItem;

    public NewsListAdapter(Context context, List<News> rowItem) {
        this.context = context;
        this.rowItem = rowItem;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.newslistitem, null);


        }

        //ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.textViewnews);
        TextView txtTitle1 = (TextView) convertView.findViewById(R.id.textViewinfo);

        News row_pos = rowItem.get(position);
        // setting the image resource and title
        //imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getName());
        txtTitle1.setText(row_pos.getNews());
        return convertView;

    }

    @Override
    public int getCount() {

        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }

}
