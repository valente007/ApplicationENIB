package com.enib.applicationenib.pasutilisé;

/**
 * Created by Valentin on 22/11/2015.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.enib.applicationenib.R;

import java.util.ArrayList;

public class FragmentTabGererNote extends ListFragment {



   ArrayList values = new ArrayList();


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // values.add("ROP");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listnotetab, R.id.textCours,values);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
    }


}


/* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragaddnotetabhost, container, false);
        //TextView tv = (TextView) v.findViewById(R.id.texttab);
        // tv.setText(this.getTag() + " Content");
        //return v;

    }*/
   /*String[] values;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        values = new String[] { "MIP","Statistique","asn","rop" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listnotetab,R.id.textitemNote,values);
        setListAdapter(adapter);

    }*/