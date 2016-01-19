package com.example.valentin.applicationenib.edtPackage;

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

import com.example.valentin.applicationenib.R;

import java.util.List;

public class CustomAdapterCalendar extends BaseAdapter {

    Context context;
    List<String> rowItem;

    public CustomAdapterCalendar(Context context, List<String> rowItem) {
        this.context = context;
        this.rowItem = rowItem;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.itemcours, null);
        }
        TextView txtMatiere = (TextView) convertView.findViewById(R.id.idMatiere);
        String row_pos = rowItem.get(position);
        txtMatiere.setText(row_pos);
        return convertView;

    }

}
