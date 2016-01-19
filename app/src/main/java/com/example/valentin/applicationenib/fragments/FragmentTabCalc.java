package com.example.valentin.applicationenib.fragments;

/**
 * Created by Valentin on 22/11/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valentin.applicationenib.R;

public class FragmentTabCalc extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmoyennetabhost, container, false);
        //TextView tv = (TextView) v.findViewById(R.id.texttab);
       // tv.setText(this.getTag() + " Content");
        return v;
    }
}