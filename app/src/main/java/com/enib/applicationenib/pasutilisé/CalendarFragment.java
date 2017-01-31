package com.enib.applicationenib.pasutilisé;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enib.applicationenib.MainActivity;
import com.enib.applicationenib.R;



/**
 * Created by Valentin on 16/11/2015.
 */
public class CalendarFragment extends Fragment {
    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar,container,false);
        MainActivity.app.getEdt();
        return rootView;
    }
}
