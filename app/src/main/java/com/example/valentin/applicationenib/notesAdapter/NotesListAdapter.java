package com.example.valentin.applicationenib.notesAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.R;
import com.example.valentin.applicationenib.appCodeSource.notes.Semester;
import com.example.valentin.applicationenib.notesPackage.NotesListActivity;

import java.util.List;

/**
 * Created by Valentin on 10/12/2015.
 */
public class NotesListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private List<Semester> msemester;
    private ExpandableListView mExpandableListBloc;
    ViewHolder holder;
    private Context contexts;

    public int getNbSemester() {
        return nbSemester;
    }

    private int nbSemester;

    public NotesListAdapter(Context context, List<Semester> semester){
        msemester = semester;
        inflater = LayoutInflater.from(context);
        contexts=context;
    }


    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return msemester.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return 1/*msemester.get(i).getBlocs().size()*/;
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return msemester.get(i).getSemesterNbr();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return msemester.get(i).getBlocs().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_semester, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view_semester);
        textView.setText(getGroup(groupPosition).toString());
        Log.i(MainActivity.TAG,getGroup(groupPosition).toString() );
        TextView textViewaverage = (TextView) view.findViewById(R.id.textViewaveragesemester);
        textViewaverage.setText(String.valueOf(MainActivity.decimalFormat.format(msemester.get(groupPosition).getAverage())));

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        final ViewHolder holder1 = new ViewHolder();
        holder1.childPosition = childPosition;
        holder1.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_notes_lv2, viewGroup,false);
        }
        Log.i(MainActivity.TAG, "getChildView: enter");

        mExpandableListBloc = (ExpandableListView) view.findViewById(R.id.expandable_list_notes_blocs);
        mExpandableListBloc.setAdapter(new NotesListAdapterBloclv2(view.getContext(), MainActivity.app.getSemester(msemester.get(groupPosition).getSemesterNbr()).getBlocs()));

        mExpandableListBloc.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Log.i(MainActivity.TAG, "semestre"+String.valueOf(getGroup(holder1.groupPosition)));
                //Log.i(MainActivity.TAG, "bloc"+String.valueOf(groupPosition));
                //Log.i(MainActivity.TAG, "cours"+String.valueOf(childPosition));
                //Log.i(MainActivity.TAG, "onChildClick: ");

                /*NotesListActivity noteslist = new NotesListActivity();
                noteslist.setnameSemester((int) getGroup(holder1.groupPosition));
                noteslist.setPosBloc(groupPosition);
                noteslist.setPosCours(childPosition);*/

                nbSemester= (int) getGroup(holder1.groupPosition);


                Intent intent=new Intent(contexts,NotesListActivity.class);
                intent.putExtra("nbsemester", nbSemester);
                intent.putExtra("nomBloc",MainActivity.app.getSemester(nbSemester).getBlocs().get(groupPosition).getName());
                intent.putExtra("nomCours",MainActivity.app.getSemester(nbSemester).getBlocs().get(groupPosition).getSubjects().get(childPosition).getName());

                contexts.startActivity(intent);
                ((Activity) contexts).overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);


                return true;
            }
        });

       // TextView textView = (TextView) view.findViewById(R.id.list_item_text_bloc);
       // textView.setText(msemester.get(groupPosition).getBlocs().get(childPosition).getName());

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

// Intentionally put on comment, if you need on click deactivate it
/*  @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder)view.getTag();
        if (view.getId() == holder.button.getId()){

           // DO YOUR ACTION
        }
    }*/


    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }
}
