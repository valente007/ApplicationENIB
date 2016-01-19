package com.example.valentin.applicationenib.busPackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valentin.applicationenib.R;

import java.util.List;

/**
 * Created by Valentin on 15/12/2015.
 */
public class HoraireListAdapter extends BaseAdapter {

    private static final String TAG = "TestFragment";
    Context context;
    List<Integer> heure;
    List<Integer> min;

    public HoraireListAdapter(Context context, List<Integer> heure, List<Integer> min) {
        this.context = context;
        this.heure = heure;
        this.min = min;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listehoreirebus, null);


        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textViewheure);
        int row_pos = heure.get(position);
        txtTitle.setText(String.valueOf(row_pos));

        TextView txtTitle1 = (TextView) convertView.findViewById(R.id.textViewmin);
        int row_pos1 = min.get(position);
        txtTitle1.setText(String.valueOf(row_pos1));



        return convertView;

    }

    @Override
    public int getCount() {

        return heure.size();
    }

    @Override
    public Object getItem(int position) {

        return heure.get(position);
    }

    @Override
    public long getItemId(int position) {

        return heure.indexOf(getItem(position));
    }

}
