package com.example.valentin.applicationenib.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.NewsListAdapter;
import com.example.valentin.applicationenib.R;

/**
 * Created by Valentin on 17/11/2015.
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment,container,false);

        if(MainActivity.app.getNews()!=null){
        ListView list = (ListView) rootView.findViewById(R.id.listViewNews);
        list.setAdapter(new NewsListAdapter(getContext(), MainActivity.app.getNews()));}

        else Toast.makeText(getActivity(), "Veuillez vous connecter ", Toast.LENGTH_SHORT).show();


        return rootView;
    }


}
