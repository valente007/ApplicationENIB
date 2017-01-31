package com.enib.applicationenib.pasutilisé;

/**
 * Created by Valentin on 21/11/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.enib.applicationenib.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private static final String TAG = "TestFragment";
    Context context;
    List<RowItem> rowItem;

    public CustomAdapter(Context context, List<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listitemevent, null);


        }

        //ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.textitem);


        Switch switchbutton = (Switch) convertView.findViewById(R.id.switchtogleevent);
        switchbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(getActivity(),"Inscription enregistrée",Toast.LENGTH_SHORT);
                    //Toast.makeText(,"Inscription enregistrée",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onCheckedChanged: is checked ");
                } else {
                    //Toast.makeText(getActivity(),"Desinscription enregistrée",Toast.LENGTH_SHORT);
                    //Toast.makeText(context ,"Desinscription enregistrée",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onCheckedChanged: not checked ");
                }

                    }
                });



        RowItem row_pos = rowItem.get(position);
        // setting the image resource and title
        //imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getTitle());

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
