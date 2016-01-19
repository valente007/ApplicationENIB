package com.example.valentin.applicationenib.appCodeSource.profil;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f93870b0-e392-4ea9-9e0a-3a11229f9470")
public class Adress {
    @objid ("418974df-5ed0-42d3-abb6-9a93ec7e9c71")
    protected String adress;

    @objid ("4ff21182-b45c-4f1f-926b-94726a374ae2")
    protected String zipCode;

    @objid ("4ef7afd9-6701-4f80-b446-758c30d09265")
    protected String town;

    @objid ("ff77bacf-51c3-4fcf-86f2-ffb33b269eb3")
    public String getAdress() {
        return adress;
    }

    @objid ("2a457a36-ee54-4c27-b488-80eb5b301e2d")
    public void setAdress(String adress) {
    }

    @objid ("140d6d13-c4f6-4bef-876f-4ddebecd490e")
    public String getZipCode() {
        return zipCode;
    }

    @objid ("183c4ac4-b4e9-421b-a270-a8b533ad992e")
    public String getTown() {
        return town;
    }

    @objid ("120b2750-66ae-4143-ae0c-46753dd56ee0")
    public void setCity(String city) {
    }

	public Adress(String adress, String zipCode, String town) {
		super();
		this.adress = adress;
		this.zipCode = zipCode;
		this.town = town;
	}

}
