package com.enib.applicationenib.appCodeSource.edt;


import com.enib.applicationenib.appCodeSource.events.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("02039ab3-a686-4715-893a-1858011790a9")
public class SchoolClass {
	
    @objid ("4daf66d7-8084-415c-992a-0f88d8131a75")
    protected String group;

    @objid ("708451d2-3d37-4aaf-bddf-cb4152c1e2f5")
    protected int uID;

    @objid ("7f992378-276d-405a-a1d9-96fea4bf89f4")
    protected boolean DSstate;

    @objid ("81ca5a1d-b88a-4a0f-83cd-d16c04c52ab6")
    protected String summary;

    @objid ("20576c98-75ee-4ad1-ae77-85ce4a284069")
    protected String description;

    @objid ("432762f0-a04f-4de8-904c-92d69566b972")
    protected Date dtstart;

    @objid ("792be9a5-e8dc-4351-bb15-51dea8e41a05")
    protected Date dtend;

    @objid ("f7e6c833-35b0-44a3-a5a6-cc4f5c88c489")
    protected String teacher;

    @objid ("929ea797-5f47-41b6-bbd4-99147fd3df60")
    protected Homework homework;

    protected String location;

    @objid ("9e5a675e-c2a6-4840-aeb0-bca8295259af")
    public String getGroup() {
        return this.group;
    }

    @objid ("cfaa7d99-cb25-4eac-ad8f-7392f0986d11")
    public void setGroup(String group) {
        this.group = group;
    }
    
    @objid ("127c9fe9-c8ca-40df-adc9-f7c187fc41f6")
    public void setUID(int UID) {
        this.uID = UID;
    }

    @objid ("626948f4-307f-410d-b657-34ba9d85565b")
    public int getUID() {
        return this.uID;
    }

    @objid ("0df9877a-2113-4a25-8c4d-50e418d0e7f7")
    public boolean isEnableDS() {
        return this.DSstate;
    }

    @objid ("5d34dddf-7573-4c8b-a335-abebe7315620")
    public void enableDS() {
        this.DSstate = true;
    }

    @objid ("81e8c714-bece-4b42-a0b0-75c5e255a334")
    public Homework getHomework() {
        return this.homework;
    }

    @objid ("c0e137ee-d6f9-4281-8a8e-ec5df8bd052b")
    public String getSummary() {
        return this.summary;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {this.location = location;}

    @objid ("a715312f-704a-44fc-8cb2-ff4b70e70751")
    public void setSummary(String sum) {
        this.summary = sum;
    }

    @objid ("5cc813db-f202-4cd7-94de-8935e0fc50a3")
    public String getDescription() {
        return this.description;
    }

    @objid ("293b2303-4cfc-4e71-b95c-a025dce987b1")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("8716a806-2037-4100-8c8f-50dc9f65ecdc")
    public void addHW(String description) {
        this.homework = new Homework(description);
        this.homework.setHwID(uID);
    }

    @objid ("b3151749-4dd1-4607-b2c6-f808b34618c6")
    public void disableDS() {
        this.DSstate = false;
    }

	public SchoolClass(String group, int uID, boolean dSstate, String summary, String description, Date dtstart,
			Date dtend, String teacher, String location) {
		super();
		this.group = group;
		this.uID = uID;
		DSstate = dSstate;
		this.summary = summary;
		this.description = description;
		this.dtstart = dtstart;
		this.dtend = dtend;
		this.teacher = teacher;
        this.location = location;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getDtstart() {
		return dtstart;
	}

	public void setDtstart(Date dtstart) {
		this.dtstart = dtstart;
	}

	public Date getDtend() {
		return dtend;
	}

	public void setDtend(Date dtend) {
		this.dtend = dtend;
	}
}
