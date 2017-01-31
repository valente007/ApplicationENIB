package com.enib.applicationenib.appCodeSource.application;

/**
 * Created by Anthony on 13/12/2015.
 */

import android.os.StrictMode;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.net.URL;

/**
 * Retrieve array from student website with note history
 */
public class https  {

    private JSONArray array;
    /** Constructor of the class    */

    public https(String note){
        try {
            // Execute in main thread, could block all app
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Get URL from parameters
            URL enibWebsiteURL = new URL("https://enib.net/enibar/history?note=" + note);
            String charset = "UTF-8";

            //Retriveve content of the webpage
            String response = IOUtils.toString(enibWebsiteURL, charset);

            // Convert the content in a JSONArray
            this.array = new JSONArray(response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** return array */
    public  JSONArray getHistory(){
        try {
            return this.array;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
