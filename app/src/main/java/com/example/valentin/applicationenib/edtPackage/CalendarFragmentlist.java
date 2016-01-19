package com.example.valentin.applicationenib.edtPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.R;
import com.example.valentin.applicationenib.appCodeSource.edt.SchoolClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarFragmentlist extends Fragment {

    CalendarView calendar;
    private View fragView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        fragView = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendar = (CalendarView) fragView.findViewById(R.id.calendarView1);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c;
                month = month + 1;
                // Toast.makeText(getActivity(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                boolean empty = true;

                List<String> values = new ArrayList<String>();
                for (SchoolClass cours : MainActivity.app.getEdt().getClasses()) {

                    if ((dayOfMonth == cours.getDtstart().getDay()) && (month == cours.getDtstart().getMonth()) && (year == cours.getDtstart().getYear())) {//si j'ai quelque chose
                        empty = false;
                        cours.getDescription();

                        //Log.d("matiere", cours.getSummary());
                        values.add("Matière : " + cours.getSummary() + "   Salle : " + cours.getLocation() + "                          Début : " + cours.getDtstart().getHour() + "h" + cours.getDtstart().getMinutes() +
                                "   Fin : " + cours.getDtend().getHour() + "h" + cours.getDtend().getMinutes() + "       Professeur : " + cours.getTeacher());


                    } else if (empty) {       //si j'ai rien dans cette journée
                    }


                }
                ListView listView = (ListView) fragView.findViewById(R.id.cours);
                listView.setAdapter(new CustomAdapterCalendar(getContext(), values));
            }
        });

        return fragView;
    }
}
