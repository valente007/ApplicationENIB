package com.enib.applicationenib;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enib.applicationenib.appCodeSource.application.Application;
import com.enib.applicationenib.busPackage.BusFragment;
import com.enib.applicationenib.edtPackage.CalendarTable;
import com.enib.applicationenib.eventPackage.EventFragment;
import com.enib.applicationenib.mainPackage.MainFragment;
import com.enib.applicationenib.notesPackage.NotesListFragment;
import com.enib.applicationenib.profilePackage.ProfilActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import static android.os.Environment.getExternalStorageDirectory;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "debug";
    public int stateco = 0;


    public static String PACKAGE_NAME;
    public static Application app;
    public static boolean state=false;
    public
    static DecimalFormat decimalFormat=new DecimalFormat("0.00");
public ProgressDialog progress;
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
                getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() );


        //app.wantsConnect("name", "pswd");
        //MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());
        MainActivity.app.wantsNews();
        //MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());
        ///////////////////////////////////demande permission///////////////////////////////


       /* // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},R );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }*/






        //////////////création des dossiers et fichiers xml//////////////////////////////////////////////////////////////


        File myDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +"Android" +File.separator+"data"+File.separator+ "com.enib.applicationenib/files/xml/");
        Log.i(TAG,Environment.getDataDirectory().getPath() + File.separator +"Android" +File.separator+"data"+File.separator+ "com.enib.applicationenib/files/xml/");//pour créer le repertoire dans lequel on va mettre notre fichier
        Boolean success=true;
        if (!myDir.exists()) {
            Log.i(TAG,"avant mkdir");
            success = myDir.mkdirs(); //On crée le répertoire (s'il n'existe pas!!)
            Log.i(TAG,"apres mkdir");
        }
        if (!success){
            Log.d(TAG,"Folder not created.");
        }
        else{
            Log.d(TAG, "Folder created!");
        }

        /*File dirtest = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +"Android" +File.separator+"data"+File.separator+ "vdwdwfw");

        Boolean bool =  false;
               bool= dirtest.exists();
        Log.d(TAG, String.valueOf(bool) +" yo");*/

        File subjectsFilexml = new File(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/subjects.xml");
        File cookiesFilexml = new File(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/cookies.xml");
        File edtFilexml = new File(getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/edtdata.xml");

  if (!subjectsFilexml.exists() && !cookiesFilexml.exists() && !edtFilexml.exists()) {
      InputStream myInput = this.getResources().openRawResource(R.raw.edtdata);
//mon fichier de sortie ici extrait sur la SD dans le répertoire download
      String outFileName = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/edtdata.xml";
      OutputStream myOutput;
      try {
          myOutput = new FileOutputStream(outFileName);
          byte[] buffer = new byte[1024];
          int length;
          while ((length = myInput.read(buffer)) > 0) {
              myOutput.write(buffer, 0, length);
          }
          myOutput.flush();
          myOutput.close();
          myInput.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }


      InputStream myInput2 = this.getResources().openRawResource(R.raw.cookies);
//mon fichier de sortie ici extrait sur la SD dans le répertoire download
      String outFileName2 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/cookies.xml";
      OutputStream myOutput2;
      try {
          myOutput2 = new FileOutputStream(outFileName2);
          byte[] buffer = new byte[1024];
          int length;
          while ((length = myInput2.read(buffer)) > 0) {
              myOutput2.write(buffer, 0, length);
          }
          myOutput2.flush();
          myOutput2.close();
          myInput2.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

      InputStream myInput3 = this.getResources().openRawResource(R.raw.subjects);
      Log.i(TAG, "openrawressource");
//mon fichier de sortie ici extrait sur la SD dans le répertoire download
      String outFileName3 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/subjects.xml";
      Log.i(TAG, "getexternal");
      OutputStream myOutput3;
      try {
          Log.i(TAG, "avantfileoutput");
          myOutput3 = new FileOutputStream(outFileName3);

          Log.i(TAG, "apresfileoutput");
          byte[] buffer = new byte[1024];
          int length;
          while ((length = myInput3.read(buffer)) > 0) {
              myOutput3.write(buffer, 0, length);
          }
          myOutput3.flush();
          myOutput3.close();
          myInput3.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

      Log.i(TAG, Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/xml/subjects.xml");
  }



        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        View headerview = navigationView.getHeaderView(0);
        RelativeLayout header = (RelativeLayout) headerview.findViewById(R.id.header);
        //navigationView.addHeaderView(header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.app.getStudent()!=null) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);
                }
                else Toast.makeText(MainActivity.this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "prout", Toast.LENGTH_SHORT);

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Log.i(TAG, "getProfil: toast");
            }
        });

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

                 progress = ProgressDialog.show(MainActivity.this, "Connection",
                        "Please wait...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        // do the thing that takes a long time
                        //Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
                        EditText et1 = (EditText) alertDialogView.findViewById(R.id.identifiant);
                        EditText et2 = (EditText) alertDialogView.findViewById(R.id.motdepasse);

                        String login=et1.getText().toString();
                        String pswd=et2.getText().toString();

                        // Log.i(TAG, ch);

                        app.wantsConnect(login, pswd);

                        if(MainActivity.app.getStudent().getName()!=null) {
                            MainActivity.app.wantsEDT(MainActivity.app.getStudent().getLogin());
                            MainActivity.app.wantsEvents();
                           // Toast.makeText(MainActivity.this, MainActivity.app.getStudent().getLogin(), Toast.LENGTH_SHORT).show();
                            stateco= 1;
                        }
                        else{
                            MainActivity.app.setStudent(null);
                            progress.dismiss();
                            //Toast.makeText(MainActivity.this, "Identifiant/Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "yolo");
                            stateco = 2;
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {

                                Log.i(TAG, Integer.toString(stateco));
                                switch(stateco){
                                    case 1 : TextView nameh = (TextView) findViewById(R.id.textnameheader);
                                        nameh.setText(MainActivity.app.getStudent().getName() + " " + MainActivity.app.getStudent().getSurname());
                                        TextView mailh = (TextView) findViewById(R.id.textmailheader);
                                        mailh.setText(MainActivity.app.getStudent().getLogin() + "@enib.fr");
                                        progress.dismiss();
                                        Toast.makeText(MainActivity.this, "Vous êtes connecté", Toast.LENGTH_SHORT).show(); Log.i(TAG, Integer.toString(1));
                                        break;

                                    case 2 : Toast.makeText(MainActivity.this, "Identifiant/Mot de passe incorrect", Toast.LENGTH_SHORT).show(); Log.i(TAG, Integer.toString(2));
                                        break;
                                }

                            }
                        });
                    }
                }).start();


                //Toast.makeText(MainActivity.this, "Vous êtes connecté", Toast.LENGTH_SHORT).show();


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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.nav_edt) {
            if(MainActivity.app.getStudent()!=null) {

            fm.beginTransaction().replace(R.id.frame,new CalendarTable()).commit();
            setTitle("Emploi du Temps");}
            else Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_notes) {
            fm.beginTransaction().replace(R.id.frame, new NotesListFragment()).commit();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*public void getProfil(View view){
        if(MainActivity.app.getStudent()!=null) {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.profil_animation1, R.anim.profil_animation2);

        }
        else Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();


        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawer.closeDrawer(GravityCompat.START);
        Log.i(TAG, "getProfil: toast");

    }*/

   /* @Override
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
    }*/




}
