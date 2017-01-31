package com.enib.applicationenib.notesPackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.enib.applicationenib.MainActivity;
import com.enib.applicationenib.R;

public class NotesListActivity extends AppCompatActivity {


    private int nbSemester;
    private String nameBloc;
    private String nameCours;
    private double newnotevalue;
    private String categorie="cat";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Intent intent= getIntent();
        nbSemester = intent.getIntExtra("nbsemester", 0);
       nameBloc = intent.getStringExtra("nomBloc");
        nameCours = intent.getStringExtra("nomCours");

        Log.i(MainActivity.TAG, String.valueOf(nbSemester));
        Log.i(MainActivity.TAG, nameBloc);
        Log.i(MainActivity.TAG, nameCours);

        /*EditText newnote= (EditText) findViewById(R.id.editTextnewnote);
        String newnotevaluestg= newnote.getText().toString();
        newnotevalue = Double.parseDouble(newnotevaluestg);*/

        Spinner spinnercours = (Spinner) findViewById(R.id.spinnercategorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categorie, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercours.setAdapter(adapter);
        spinnercours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorie = (String) parent.getItemAtPosition(position);
                Log.i(MainActivity.TAG, categorie);
                ListView listnotes = (ListView) findViewById(R.id.listViewcoursnote);
                switch (categorie) {

                    case "ds":
                        //listnotes.setAdapter(new NotesListAdapterAdd(this,MainActivity.app.getSemester(nbSemester).getBloc(nameBloc).getSubject(nameCours).getNotesTD(),nameCours,nameBloc,nbSemester,categorie));

                        break;
                    case "td":
                        listnotes.setAdapter(new NotesListAdapterAdd(NotesListActivity.this,MainActivity.app.getSemester(nbSemester).getBloc(nameBloc).getSubject(nameCours).getNotesTD(),nameCours,nameBloc,nbSemester,categorie));


                        break;
                    case "tp":
                        listnotes.setAdapter(new NotesListAdapterAdd(NotesListActivity.this,MainActivity.app.getSemester(nbSemester).getBloc(nameBloc).getSubject(nameCours).getNotesTP(),nameCours,nameBloc,nbSemester,categorie));
                        break;
                }

                listnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //On instancie notre layout en tant que View
                        LayoutInflater factory = LayoutInflater.from(NotesListActivity.this);
                        final View alertDialogView = factory.inflate(R.layout.alertsupprimernote, null);

                        //Création de l'AlertDialog
                        AlertDialog.Builder adb = new AlertDialog.Builder(NotesListActivity.this);

                        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
                        adb.setView(alertDialogView);

                        //On donne un titre à l'AlertDialog
                        adb.setTitle("Supprimer une note");

                        //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
                        adb.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });

                        //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
                        adb.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Lorsque l'on cliquera sur annuler on retourne a la page précedente
                                dialog.cancel();
                            }
                        });
                        adb.show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button = (Button) findViewById(R.id.buttonaddnewnote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newnote= (EditText) findViewById(R.id.editTextnewnote);
                String newnotevaluestg= newnote.getText().toString();
                newnotevalue = Double.parseDouble(newnotevaluestg);
                Log.i(MainActivity.TAG, "note" + newnotevalue);
                MainActivity.app.addNote(nbSemester, nameBloc, nameCours, categorie, newnotevalue);
                Log.i(MainActivity.TAG, MainActivity.app.getSemester(nbSemester).getBloc(nameBloc).getSubject(nameCours).getNotesTD().toString());
            }
        });

        //MainActivity.app.getSemester().getBloc().getSubject().getNotesTD();
        //ArrayAdapter<Double> adapterlist = new ArrayAdapter<Double>(this, R.layout.listnotescategorie, R.id.textnotelistcoursadd, MainActivity.app.getSemester(nbSemester).getBloc(nameBloc).getSubject(nameCours).getNotesTD());
        //TextView nbsem = (TextView) findViewById(R.id.textnbsemesterlistadd);
        //nbsem.setText(nbSemester);
       //listnotes.setAdapter(adapterlist);



        setTitle(nameCours);
    }


}
