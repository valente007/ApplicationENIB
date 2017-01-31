package com.enib.applicationenib.notesPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.enib.applicationenib.MainActivity;
import com.enib.applicationenib.notesPackage.notesAdapter.NotesListAdapter;
import com.enib.applicationenib.R;


/**
 * Created by Valentin on 10/12/2015.
 */
public class NotesListFragment extends Fragment implements ExpandableListView.OnChildClickListener{
    private ExpandableListView mExpandableList;
    private ExpandableListView mExpandableListbloc;


    private static final String TAG = "TestFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_notes,container,false);


        if (MainActivity.state==false){
        MainActivity.app.initSemesters();
        MainActivity.state=true;
            ;}

        mExpandableList = (ExpandableListView) rootView.findViewById(R.id.expandable_list_notes);
       // mExpandableListBloc = (ExpandableListView) rootView.findViewById(R.id.expandable_list_notes_blocs);


       mExpandableList.setAdapter(new NotesListAdapter(getContext(), MainActivity.app.getSemesters()));
       // mExpandableListBloc.setAdapter(new NotesListAdapterBloclv2(getContext(), MainActivity.app.getSemester(1).getBlocs()));

        //mExpandableListbloc = (ExpandableListView) getActivity().findViewById(R.id.expandable_list_notes_blocs);

        /*mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                v.setSelected(true);

                Intent intent= new Intent(getActivity(),ProfilActivity.class );
                startActivity(intent);
                //Toast.makeText(this, "prout", Toast.LENGTH_SHORT);


                getActivity().overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);

                Log.i(TAG, String.valueOf(groupPosition));
                Log.i(TAG, String.valueOf(childPosition));
                return true;
            }
        });*/
        Log.i(TAG, "onCreateView: ");
        Log.i(TAG, "onCreateView: " + MainActivity.app.getSemester(21).getBloc("A").getSubjects());
        return rootView;

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        Log.i(TAG,"prout"+ String.valueOf(groupPosition));
        Log.i(TAG, String.valueOf(childPosition));

        return true;
    }
}
