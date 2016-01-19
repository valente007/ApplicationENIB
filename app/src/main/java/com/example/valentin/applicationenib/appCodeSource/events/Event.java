package com.example.valentin.applicationenib.appCodeSource.events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Event {

    private int id;
    private String nom;
    private String location;
    private String description;
    private String prix;
    private Boolean inscris;
    private String fin;
    private List<Event> events = new ArrayList<Event>();

    public Event(int id, String nom, String Location, String Description, String Prix, Boolean Inscris) {
        this.id = id;
        this.nom = nom;
        this.location = Location;
        this.description = Description;
        this.prix = Prix;
        this.inscris = Inscris;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getPrix() {
        return prix;
    }

    public Boolean getInscris() {
        return inscris;
    }

    public void setInscris(Boolean inscris) {
        this.inscris = inscris;
    }

    // Request HTTP GET
    public List<Event> getEvents(String authorization) {

        String url = "https://enib.net/api/events/?format=json";

        URL obj = null;
        HttpsURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpsURLConnection) obj.openConnection();
            con.setConnectTimeout(8000);
            con.setRequestMethod("GET");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Setting request

        con.setRequestProperty("Authorization", authorization);
        con.setRequestProperty("Accept", "application/json");


        BufferedReader br = null;
        JSONArray infos = null;
        try {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            String data = "";
            while ((input = br.readLine()) != null) {
                data += input;
            }
            br.close();

            infos = new JSONArray(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Parsing events on enib.net

        int[] tmpList = {};
        JSONObject[] tmpJSON = {null};
        int countEvent = 0;
        for (int i = 0; i < infos.length(); i++) {
            JSONObject event = null;
            try {
                event = infos.getJSONObject(i);
                this.fin = event.getString("end_inscriptions");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Date : 2015-12-18T10:10:00Z

            long time = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date actualdate = new Date(time);

            Date eventdate = null;
            try {
                eventdate = sdf.parse(this.fin);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Checking the event happen after today and instantiate

            if (actualdate.before(eventdate)) {
                Boolean a = true;
                Event b = null;
                HttpsURLConnection con2 = null;
                try {
                    b = new Event(event.getInt("id"), event.getString("name"), event.getString("location"), event.getString("description"),
                            event.getString("price"), a);
                    this.events.add(b);
                    // Getting registration of the event and setting it
                    String url2 = "https://enib.net//api/events/" + event.getInt("id") + "/get_registration/";

                    URL obj2 = new URL(url2);
                    con2 = (HttpsURLConnection) obj2.openConnection();
                    con2.setRequestMethod("GET");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                con2.setRequestProperty("Authorization", authorization);
                con2.setRequestProperty("Accept", "application/json");

                BufferedReader br2 = null;
                try {
                    br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
                    String input2;
                    String data2 = "";
                    while ((input2 = br2.readLine()) != null) {
                        data2 += input2;
                    }
                    br2.close();

                    JSONObject infos2 = new JSONObject(data2);
                    this.events.get(countEvent).setInscris(infos2.getBoolean("user_is_registered"));
                    countEvent++;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.events;
    }
}