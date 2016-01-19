package com.example.valentin.applicationenib.profilePackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.R;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setTitle("Profil");

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


        TextView namep = (TextView) findViewById(R.id.textnameprofil);
        namep.setText(MainActivity.app.getStudent().getName() + " " + MainActivity.app.getStudent().getSurname());
        TextView surnamep = (TextView) findViewById(R.id.textnicknameprofil);
        surnamep.setText(MainActivity.app.getStudent().getNickname() );
        TextView mailp = (TextView) findViewById(R.id.textmailprofil);
        mailp.setText(MainActivity.app.getStudent().getLogin()+"@enib.fr");
        TextView phonep = (TextView) findViewById(R.id.textphoneprofil);
        phonep.setText(MainActivity.app.getStudent().getPhone());



    }

    /*public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class );
        startActivity(intent);
        overridePendingTransition(R.anim.profilclose2, R.anim.profilclose1);
       //this.finish();

    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profilmenu, menu);
        return true;
    }*/
}