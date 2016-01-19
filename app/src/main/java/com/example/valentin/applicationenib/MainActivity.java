package com.example.valentin.applicationenib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentin.applicationenib.appCodeSource.application.Application;
import com.example.valentin.applicationenib.busPackage.BusFragment;
import com.example.valentin.applicationenib.edtPackage.CalendarFragmentlist;
import com.example.valentin.applicationenib.fragments.EventFragment;
import com.example.valentin.applicationenib.fragments.MainFragment;
import com.example.valentin.applicationenib.fragments.NotesListFragment;
import com.example.valentin.applicationenib.profilePackage.ProfilActivity;

import java.io.File;
import java.text.DecimalFormat;

import static android.os.Environment.getExternalStorageDirectory;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static final String TAG = "debug";


    public static String PACKAGE_NAME;
    public static Application app;
    public static boolean state=false;
    public
    static DecimalFormat decimalFormat=new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        File subjectsFile = new File(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/subjects.xml");
        File cookiesFile = new File(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/cookies.xml");

        this.app = new Application(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/edtdata.xml",
                subjectsFile,cookiesFile,
                getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files");


        //app.wantsConnect("name", "pswd");
        //MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());
        MainActivity.app.wantsNews();
        //MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setBackground(R.drawable.side_nav_bar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*View header = (View) findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "prout", Toast.LENGTH_SHORT);


                overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);
                //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                // drawer.closeDrawer(GravityCompat.START);
                Log.i(TAG, "getProfil: toast");
            }
        });*/

        showConnectDialog();

        FragmentManager fm= getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame,new MainFragment()).commit();
        setTitle("Actualités");

        //init();




    }

    public void showConnectDialog(){
        //On instancie notre layout en tant que View
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alertdialog, null);

        //Création de l'AlertDialog
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        //On donne un titre à l'AlertDialog
        adb.setTitle("Connexion");

        //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
                EditText et1 = (EditText) alertDialogView.findViewById(R.id.identifiant);
                EditText et2 = (EditText) alertDialogView.findViewById(R.id.motdepasse);

                String login=et1.getText().toString();
                String pswd=et2.getText().toString();

                app.wantsConnect(login, pswd);
                MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());
                MainActivity.app.wantsEvents();


                TextView nameh = (TextView) findViewById(R.id.textnameheader);
                nameh.setText(MainActivity.app.getStudent().getName() + " " + MainActivity.app.getStudent().getSurname());
                TextView mailh = (TextView) findViewById(R.id.textmailheader);
                mailh.setText(MainActivity.app.getStudent().getLogin()+"@enib.fr");
                MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());

                Toast.makeText(MainActivity.this, "Vous êtes connecté", Toast.LENGTH_SHORT).show();





                //mainUser.setLogin(login);
               // modifTextView();
            }

        });

        //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Lorsque l'on cliquera sur annuler on retourne a la page précedente
                dialog.cancel();
            }
        });
        adb.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_connect) {
            showConnectDialog();

        }

        return super.onOptionsItemSelected(item);
    }


   // public void init(){
   //     Application app= new Application();
    //    User user = new User("a2guilli");
   //     app.wantsNews();
    //    app.wantsEDT(user.getLogin());
    //    app.initSemesters();
   // }

   /* public void modifTextView(){
        TextView surname=(TextView) findViewById(R.id.surnom);

        surname.setText(mainUser.getLogin());

    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //FragmentManager fm1= getSupportFragmentManager();
       FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.nav_edt) {
            if(MainActivity.app.getStudent()!=null) {

            fm.beginTransaction().replace(R.id.frame,new CalendarFragmentlist()).commit();
            setTitle("Emploi du Temps");}
            else Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_notes) {
            //fm.beginTransaction().replace(R.id.frame, new NotesFragment()).commit();
            fm.beginTransaction().replace(R.id.frame, new NotesListFragment()).commit();
            //fm.beginTransaction().replace(R.id.frame, new Home()).commit();
            setTitle("Notes");
        } else if (id == R.id.nav_events) {
            if(MainActivity.app.getStudent()!=null) {
            fm.beginTransaction().replace(R.id.frame, new EventFragment()).commit();
            setTitle("Evénements");}
            else Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_bus) {
            setTitle("Horaires de Bus");
            fm.beginTransaction().replace(R.id.frame, new BusFragment()).commit();
        } else if (id == R.id.nav_accueil) {
            fm.beginTransaction().replace(R.id.frame,new MainFragment()).commit();
            setTitle("Actualités");
        }
       /*else if (id == R.id.nav_connexion) {
            showConnectDialog();
        }*/
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getProfil(View view){
        if(MainActivity.app.getStudent()!=null) {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);

        }
        else Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();


        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawer.closeDrawer(GravityCompat.START);
        Log.i(TAG, "getProfil: toast");

    }

    @Override
    public void onClick(View v) {
        // getId() returns this view's identifier.
        Log.i(TAG, String.valueOf(v.getId()));
        if(v.getId() == R.id.header){
            Intent intent= new Intent(this,ProfilActivity.class );
            startActivity(intent);
            //Toast.makeText(this, "prout", Toast.LENGTH_SHORT);


            overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);
            //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            // drawer.closeDrawer(GravityCompat.START);
            Log.i(TAG, "getProfil: toast");


        }
    }




}
