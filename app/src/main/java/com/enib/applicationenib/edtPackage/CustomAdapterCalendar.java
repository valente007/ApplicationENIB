package com.enib.applicationenib.edtPackage;

/**
 * Created by Yoann on 15/12/2015.
 */



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enib.applicationenib.R;

import java.util.List;

public class CustomAdapterCalendar extends BaseAdapter {

    Context context;
    List<String> CalendarItem;

    public CustomAdapterCalendar(Context context, List<String> rowItem) {
        this.context = context;
        this.CalendarItem = rowItem;

    }

    @Override
    public int getCount() {

        return CalendarItem.size();
    }

    @Override
    public Object getItem(int position) {

        return CalendarItem.get(position);
    }

    @Override
    public long getItemId(int position) {

       return CalendarItem.indexOf(getItem(position));

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.itemcours, null);
        }
        TextView txtMatiere = (TextView) convertView.findViewById(R.id.idMatiere);
        String row_pos = CalendarItem.get(position);
        txtMatiere.setText(row_pos);
        return convertView;

    }

}
