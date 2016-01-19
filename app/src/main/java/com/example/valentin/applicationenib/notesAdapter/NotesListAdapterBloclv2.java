package com.example.valentin.applicationenib.notesAdapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.R;
import com.example.valentin.applicationenib.appCodeSource.notes.Bloc;

import java.util.List;

/**
 * Created by Valentin on 10/12/2015.
 */
public class NotesListAdapterBloclv2 extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private List<Bloc> mBloc;

    public NotesListAdapterBloclv2(Context context, List<Bloc> bloc){
        mBloc = bloc;
        inflater = LayoutInflater.from(context);
    }


    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mBloc.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mBloc.get(i).getSubjects().size();
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mBloc.get(i).getName();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mBloc.get(i).getSubjects().get(i1);
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

        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_bloc_adapter, viewGroup,false);
        }
        //Log.i(MainActivity.TAG, "getChildView: adapterlv2 enter");

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view_bloc);
        //Log.i(MainActivity.TAG, "getChildView: adapterlv2 enter");
        textView.setText(getGroup(groupPosition).toString());

        TextView textViewaveragebloc = (TextView) view.findViewById(R.id.textViewaveragebloc);
        textViewaveragebloc.setText(String.valueOf(MainActivity.decimalFormat.format(mBloc.get(groupPosition).getAverage())));

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_cours, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_cours);
        textView.setText(mBloc.get(groupPosition).getSubjects().get(childPosition).getName());

        TextView textViewaveragecours = (TextView) view.findViewById(R.id.textViewaveragecours);
        textViewaveragecours.setText(String.valueOf(MainActivity.decimalFormat.format(mBloc.get(groupPosition).getSubject(mBloc.get(groupPosition).getSubjects().get(childPosition).getName()).getAverage())));

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
  /*@Override
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
