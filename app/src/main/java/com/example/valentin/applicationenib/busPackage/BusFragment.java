package com.example.valentin.applicationenib.busPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.valentin.applicationenib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentin on 16/12/2015.
 */
public class BusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bus,container,false);


        List<Integer> horaireHeuredf=new ArrayList<Integer>();

        int[] h = new  int[]{6,7,7,7,7,8,8,8,8,8,9,9,9,10,10,10,11,11,11,12,12,13,13,13,14,14,14,15,15,16,16,16,16,17,17,17,17,17,18,18,18,19,19,19,20,21,22,23,24};

        for (int i : h){
            horaireHeuredf.add(i);
        }

        List<Integer> horaireMindf=new ArrayList<Integer>();

        int[] m = new  int[]{18,8,28,39,49,5,18,31,43,57,14,27,45,8,31,53,13,34,57,20,42,4,25,47,9,30,54,17,37,1,21,38,51,4,17,30,42,54,6,18,38,0,23,52,42,32,17,11,47};

        for (int i : m){
            horaireMindf.add(i);
        }

        ListView listdf = (ListView) rootView.findViewById(R.id.listViewdf);
        listdf.setAdapter(new HoraireListAdapter(getContext(),horaireHeuredf,horaireMindf));





        return rootView;
    }
}
