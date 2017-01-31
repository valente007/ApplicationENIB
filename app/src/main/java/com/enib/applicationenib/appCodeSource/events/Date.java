package com.enib.applicationenib.appCodeSource.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b06efe7b-b29d-4927-ba8d-af8923465d4d")
public class Date {
    @objid ("445b5770-4631-4502-b32d-51595666b82e")
    protected int day;

    @objid ("287ee54f-9a8d-422e-820c-4772aee4be34")
    protected int month;

    @objid ("00b1b0e8-e3b9-4d78-9159-f50806c1d73e")
    protected int year;

    @objid ("c43c62cc-d883-4461-b408-2e3bba7f4845")
    protected int hour;

    @objid ("814ac428-2e1f-41d0-b9e3-9d786c87b71b")
    protected int minutes;

    @objid ("2ac764ce-6dcd-474a-a3cf-a5f0b173aa83")
    public int getMonth() {
        return month;
    }

    @objid ("12e2e57a-078f-4b95-995d-14763006d9e7")
    public void setDay(int day) {
    }

    @objid ("d295a917-eb3c-4fab-aaa9-24f8fa96cbd8")
    public void setYear(int year) {
    }

    @objid ("38c24779-da6d-4da1-989c-47c78c0ee553")
    public int getMinutes() {
        return minutes;
    }

    @objid ("f3116cd3-af96-4012-89f4-5bb59bd40bf7")
    public void setMinutes(int minutes) {
    }

    @objid ("45f5f09b-7b94-4058-a55f-872ec88367b0")
    public int getHour() {
        return hour;
    }

    @objid ("2c5a32dc-0951-4298-bb35-0101f1b2ac77")
    public void setHour(int hour) {
        this.hour = hour;
    }

    @objid ("efe20fca-64df-4838-a68a-feee95e61b16")
    public int getDay() {
        return day;
    }

    @objid ("027443f5-05cf-41a2-82e6-95b4ab50b70f")
    public int getYear() {
        return year;
    }

    @objid ("1229a3c0-72b9-4fc6-b70a-bbf20f7a8152")
    public void setMonth(int month) {
        this.month = month;
    }

	public Date(int day, int month, int year, int hour, int minutes) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
	}
	
	public Date(String date) {
		super();
		this.day = Integer.parseInt(date.substring(0,2));
		this.year = Integer.parseInt(date.substring(12,16));
		this.hour = Integer.parseInt(date.substring(17,19));
		this.minutes = Integer.parseInt(date.substring(20,22));
		switch(date.substring(3,7)){
		case "janv":
			this.month = 01;
			break;
		case "fevr":
			this.month = 02;
			break;
		case "mars":
			this.month = 03;
			break;
		case "avri":
			this.month = 04;
			break;
		case "mai ":
			this.month = 05;
			break;
		case "juin":
			this.month = 06;
			break;
		case "juil":
			this.month = 07;
			break;
		case "aout":
			break;
		case "sept":
			this.month = 8;
			break;
		case "octo":
			this.month = 9;
			break;
		case "nove":
			this.month = 10;
			break;
		case "dece":
			this.month = 11;
			break;
		}
	}

}
