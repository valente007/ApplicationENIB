package com.example.valentin.applicationenib.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.R;

import java.util.ArrayList;


/**
 * Created by Valentin on 16/11/2015.
 */
public class NotesFragment extends Fragment {

    private FragmentTabHost mTabHost;


    private ArrayList notes = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_notes,container,false);


        MainActivity.app.initSemesters();
        Log.d("here", MainActivity.app.getSemester(1).getBloc("A").getSubject("anglais").getName());

        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragtabmoy);

        mTabHost.addTab(
                mTabHost.newTabSpec("Calcul de la Moyenne").setIndicator("Calculer", null),
                FragmentTabCalc.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("gerer les Note").setIndicator("Gérer", null),
                FragmentTabGererNote.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Ajouter une Note").setIndicator("Ajouter", null),
                FragmentTabAddNote.class, null);


        // return rootView;
        mTabHost.setBackgroundColor(getResources().getColor(R.color.colorgrey));

        return mTabHost;
    }


    public ArrayList getNotes() {
        return notes;
    }

    public void setNotes(ArrayList notes) {
        this.notes = notes;
    }
}
