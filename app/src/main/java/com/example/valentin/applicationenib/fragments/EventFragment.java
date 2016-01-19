package com.example.valentin.applicationenib.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import com.example.valentin.applicationenib.MainActivity;
import com.example.valentin.applicationenib.appCodeSource.events.Event;
import com.example.valentin.applicationenib.eventAdapter.InteractiveArrayAdapter;

/**
 * Created by Valentin on 17/11/2015.
 */
public class EventFragment extends ListFragment {
    private static final String TAG = "TestFragment";
    // @Override

    /*public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: in oncreate view");
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        Log.i(TAG, "onCreateView: create view");
        //Switch Button = (Switch) getListView().findViewById(R.id.switchtogleevent);
        Log.i(TAG, "onCreateView: create button");
        //Button.setOnCheckedChangeListener(this);
        Log.i(TAG, "onCreateView: apres seton...");
        return view;
    }*/

   /* public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: before button");
        Switch switchbutton = (Switch) EventFragment.this.findViewById(R.id.switchtogleevent);
        Log.i(TAG, "onCreate: setonchecked button");
        switchbutton.setOnCheckedChangeListener(this);
    }*/

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // ListView lv = getListView();
        String[] values = new String[] { "Oenololo","OB" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.listitemevent,R.id.textitem,values);
        setListAdapter(adapter);
        /*Log.i(TAG, "onCreateView: before");
        Switch switchbutton = (Switch) getView().findViewById(R.id.switchtogleevent);
       Log.i(TAG, "onCreateView: after");
       switchbutton.setOnCheckedChangeListener(this);*/


    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
    }*/

    /*@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //Toast.makeText(getActivity(),"Inscription enregistrée",Toast.LENGTH_SHORT);
        } else {
            //Toast.makeText(getActivity(),"Desinscription enregistrée",Toast.LENGTH_SHORT);
        }
    }*/
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // create an array of Strings, that will be put to our ListActivity

        ArrayAdapter<Event> adapter = new InteractiveArrayAdapter(getActivity(), MainActivity.app.getEvents());
        setListAdapter(adapter);
    }

    /*private List<EventModel> getModel() {
        List<EventModel> list = new ArrayList<EventModel>();
        list.add(get("OB"));
        list.add(get("Oenololo"));
        // Initially select one of the items
        list.get(1).setSelected(true);
        return list;
    }
    private EventModel get(String s) {
        return new EventModel(s);
    }*/


}
