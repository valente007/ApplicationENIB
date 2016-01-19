package com.example.valentin.applicationenib.appCodeSource.profil;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fe690dd4-d727-4c5b-9da6-f4783f15f091")
public class User {
    @objid ("1cd79b02-6aca-4ad6-960a-ae54e7ad8307")
    protected String login;

    @objid ("59844119-d577-4d15-9fae-05f08452b1ee")
    public String getLogin() {
        return this.login;
    }

    @objid ("e0487127-fac5-4813-b0c0-ca52df8f5917")
    public void setLogin(String log) {
        this.login = log;
    }

	public User(String login) {
		super();
		this.login = login;
	}

}
