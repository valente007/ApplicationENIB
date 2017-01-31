package com.enib.applicationenib.pasutilisé;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.enib.applicationenib.R;


/**
 * Created by Valentin on 04/12/2015.
 */
public class FragmentTabAddNote extends Fragment implements View.OnClickListener {
    private static final String TAG = "TestFragment";
    FragmentTabGererNote fragmentTabGererNote;

    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmentaddnotetabhost, container, false);
        //TextView tv = (TextView) v.findViewById(R.id.texttab);
        // tv.setText(this.getTag() + " Content");

        Spinner spinnercours = (Spinner) v.findViewById(R.id.spinnercours);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.cours_array, android.R.layout.simple_spinner_item);


// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnercours.setAdapter(adapter);

        Spinner spinnersemestres = (Spinner) v.findViewById(R.id.spinnersemestres);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adaptersemestre = ArrayAdapter.createFromResource(getActivity(),
                R.array.cours_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnersemestres.setAdapter(adapter);

        Button button = (Button) v.findViewById(R.id.buttonaddnote);
        button.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonaddnote:

                Context context = getActivity();
                CharSequence text = "Note enregistrée";
                int duration = Toast.LENGTH_SHORT;
                Log.i(TAG, "onClick: clicked");
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                
                break;
        }
    }
}
