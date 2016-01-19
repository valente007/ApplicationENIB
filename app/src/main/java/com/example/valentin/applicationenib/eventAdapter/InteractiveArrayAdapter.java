package com.example.valentin.applicationenib.eventAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentin.applicationenib.R;
import com.example.valentin.applicationenib.appCodeSource.events.Event;

import java.util.List;

/**
 * Created by Valentin on 06/12/2015.
 */

public class InteractiveArrayAdapter extends ArrayAdapter<Event> {
    private static final String TAG = "TestFragment";
    private final List<Event> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, List<Event> list) {
        super(context, R.layout.listitemevent, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text1;
        protected TextView text2;
        protected TextView text3;
        protected Switch switchbutton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.listitemevent, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) view.findViewById(R.id.textitem);
            viewHolder.text2 = (TextView) view.findViewById(R.id.textprix);
            viewHolder.text3 = (TextView) view.findViewById(R.id.textViewDescrip);

            viewHolder.switchbutton = (Switch) view.findViewById(R.id.switchtogleevent);
            viewHolder.switchbutton
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            Event element = (Event) viewHolder.switchbutton
                                    .getTag();
                            if (isChecked) {
                                //Toast.makeText(getActivity(),"Inscription enregistrée",Toast.LENGTH_SHORT);
                                Toast.makeText(context, "Inscription enregistrée", Toast.LENGTH_SHORT).show();


                                element.setInscris(true);
                                Log.i(TAG, "onCheckedChanged: is checked ");
                            } else {
                                //Toast.makeText(getActivity(),"Desinscription enregistrée",Toast.LENGTH_SHORT);
                                element.setInscris(false);
                                Toast.makeText(context ,"Désinscription enregistrée",Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onCheckedChanged: not checked ");
                            }

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.switchbutton.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).switchbutton.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text1.setText(list.get(position).getNom());
        holder.text2.setText(list.get(position).getPrix());
        holder.text3.setText(list.get(position).getDescription());
        holder.switchbutton.setChecked(list.get(position).getInscris());
        return view;
    }
}