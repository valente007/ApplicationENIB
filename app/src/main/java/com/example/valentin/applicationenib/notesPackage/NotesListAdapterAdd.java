package com.example.valentin.applicationenib.notesPackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valentin.applicationenib.R;

import java.util.List;

/**
 * Created by Valentin on 15/12/2015.
 */
public class NotesListAdapterAdd extends BaseAdapter {

    private static final String TAG = "TestFragment";
    Context context;
    List<Double> marks;
    String nameCours;
    String nameBloc;
    int nbSemester;
    String categorieN;

    public NotesListAdapterAdd(Context context, List<Double> rowItem, String nameCours, String nameBloc,int nbSemester,String categorie) {
        this.context = context;
        this.marks = rowItem;
        this.nameCours = nameCours;
        this.nameBloc = nameBloc;
        this.nbSemester = nbSemester;
        this.categorieN = categorie;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listnotescategorie, null);


        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textnotelistcoursadd);
        double row_pos = marks.get(position);
        txtTitle.setText(String.valueOf(row_pos));

        TextView txtTitle1 = (TextView) convertView.findViewById(R.id.textcours);
        txtTitle1.setText(nameCours);

        TextView txtTitle2 = (TextView) convertView.findViewById(R.id.textcategorie);
        txtTitle2.setText(categorieN);
        TextView txtTitle3 = (TextView) convertView.findViewById(R.id.textnbsemesterlistadd);
        txtTitle3.setText(String.valueOf(nbSemester));



        return convertView;

    }

    @Override
    public int getCount() {

        return marks.size();
    }

    @Override
    public Object getItem(int position) {

        return marks.get(position);
    }

    @Override
    public long getItemId(int position) {

        return marks.indexOf(getItem(position));
    }

}
