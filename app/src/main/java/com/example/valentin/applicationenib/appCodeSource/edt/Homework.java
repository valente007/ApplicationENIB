package com.example.valentin.applicationenib.appCodeSource.edt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7e402d6c-8057-4d7c-ab44-98830f4812b0")
public class Homework {
	
    @objid ("a656acbf-f57f-41af-a8ed-66cc1d6b1db7")
    protected int hwID;

    @objid ("19ad226f-3c57-4500-8f67-1cf619326fc3")
    protected String description;

    @objid ("bcc43c9b-0a40-4210-9406-42c484e14f80")
    public String getDescription() {
        return this.description;
    }

    @objid ("ff068b32-50d0-4e2d-bc10-b917aedb1b64")
    public int getHwID() {
        return hwID;
    }
    
    @objid ("153cf2b2-577e-43ec-a0d6-816e2d0ab340")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("2b612e4b-21f2-42f7-937f-8d89249c3d7b")
    public void setHwID(int hwID) {
        this.hwID = hwID;
    }

	public Homework(String description) {
		super();
		this.description = description;
	}

}
