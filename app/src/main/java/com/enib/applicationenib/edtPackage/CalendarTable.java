package com.enib.applicationenib.edtPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enib.applicationenib.R;

/**
 * Created by Valentin on 20/03/2016.
 */
public class CalendarTable extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View fragView = inflater.inflate(R.layout.fragment_calendar, container, false);

        return fragView;
    }



}
