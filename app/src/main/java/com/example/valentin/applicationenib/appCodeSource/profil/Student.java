package com.example.valentin.applicationenib.appCodeSource.profil;

import android.util.Base64;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

@objid("89cfdeba-eee0-49b7-95f7-01083076dfb7")
public class Student {

	private String nom;
	private String prenom;
	private String surnom;
	private String phone;
	private String login;
	private String authorization;

	public String getLogin() {return login;}
	public String getName(){return this.nom;}
	public String getSurname(){return this.prenom;}
	public String getNickname(){return this.surnom;}
	public String getPhone(){return this.phone;}
	public String getAuthorization(){return this.authorization;}

	// Request HTTP GET
	public void sendGet(String authorization)  {
		String url = "https://enib.net/api/users/?format=json";

		HttpsURLConnection con = null;
		try {
			URL obj = new URL(url);
			con = (HttpsURLConnection)obj.openConnection();
			con.setRequestMethod("GET");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Setting properties

		con.setRequestProperty("Authorization", authorization);
		con.setRequestProperty("Accept", "application/json");

		//Reading data from the request

		BufferedReader br = null;
		String input;
		String data = "";
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((input = br.readLine()) != null){
				data += input;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray infos = null;
		try {
			infos = new JSONArray(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Parsing data to find student's profil

		for (int i = 0; i < infos.length(); i++)
		{
			JSONObject etudiant = null;
			try {
				etudiant = infos.getJSONObject(i);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				if (etudiant.getString("username").equals(this.login))
				{
					this.nom = etudiant.getString("last_name");
					this.prenom = etudiant.getString("first_name");
					JSONObject profil = etudiant.getJSONObject("profile");
					this.surnom = profil.getString("nickname");
					this.phone = profil.getString("phone");
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public Student(String login,String mdp) {
		this.login = login;
		String pass = (login + ":" + mdp);
		this.authorization = "Basic " + Base64.encodeToString(pass.getBytes(), Base64.DEFAULT);
		this.sendGet(this.authorization);
	}
}
