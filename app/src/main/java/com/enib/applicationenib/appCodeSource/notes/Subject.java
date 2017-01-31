package com.enib.applicationenib.appCodeSource.notes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import java.util.ArrayList;
import java.util.List;

@objid ("8a528c2e-a43d-405d-855b-0240edde70bf")
public class Subject {
	
    @objid ("c16c2c4d-814e-439f-b6df-7a27419a6cc6")
    protected double average;

    @objid ("ea15e760-4fc4-4d80-9b83-e3f86d08a35d")
    protected int coeff;

    @objid ("1cc41f60-beb5-438b-ba7d-467b322cd871")
    protected List<Double> notesTD = new ArrayList<Double> ();

    @objid ("d6fa7fce-0b18-4c11-8063-87d1980cfc23")
    protected List<Double> notesTP = new ArrayList<Double> ();

    
    @objid ("411c7380-688e-43eb-8952-1b84b0f3d1e4")
    protected double noteDS;

    @objid ("c064e624-e8e4-4ff6-9b29-64f15e94548e")
    protected double averageTD;

    @objid ("d3c2f17b-e8cf-4768-81bf-4769a4e05786")
    protected double averageTP;

    @objid ("849bd6fc-f8d7-4b59-b430-dfd203dd0727")
    protected int coeffTP;

    @objid ("71600fce-1e93-4829-9828-4ff5ddf18423")
    protected int coeffTD;

    @objid ("f916f06f-afe5-4807-865b-b9928124f841")
    protected int coeffDS;

    @objid ("0fec535a-ead8-4a49-bfe1-d4b46814a6f7")
    protected String name;
    

    @objid ("39bdc41a-2531-4c3c-8bf1-69008da5782a")
    public void calculateAverage() {
    	
    	int dsEmpty = this.noteDS == 0.0 ? 0 : 1;
    	int tdEmpty = this.getNotesTD().isEmpty() ? 0 : 1;
    	int tpEmpty = this.getNotesTP().isEmpty() ? 0 : 1;
    	double div = this.coeffDS*dsEmpty+this.coeffTD*tdEmpty+this.coeffTP*tpEmpty;
        this.average = (this.averageTD*this.coeffTD + this.averageTP*this.coeffTP + this.noteDS*this.coeffDS)/div;
    }

    @objid ("17bde1ca-781a-4533-b084-8a429ab15ff0")
    public double getAverage() {
        return this.average;
    }
    
    @objid ("56931edd-2348-4f2c-b0e9-b4c6561f7503")
    public int getCoeff() {
        return this.coeff;
    }
    
    @objid ("608f1909-210f-4fbd-b5bf-04f67afb5cee")
    public List<Double> getNotesTD() {
        return this.notesTD;
    }

    @objid ("04f42345-67db-49f8-9182-ba1545a3808b")
    public List<Double> getNotesTP() {
        return this.notesTP;
    }

    @objid ("fa4b68b5-f99c-4be0-bc0c-b4bfc2a46d4f")
    public double getNoteDS() {
        return this.noteDS;
    }
    
    @objid ("4a42346e-d87a-4e11-b34c-7354713e3aa2")
    public double getAverageTP() {
        return this.averageTP;
    }

    @objid ("3f3da0f7-d323-4310-9e90-c79071c1cdcb")
    public int getCoeffTP() {
        return coeffTP;
    }
    
    @objid ("31e7e429-074b-4987-a5b3-a94a16f0aa1a")
    public String getName() {
        return name;
    }
    
    @objid ("da8aab03-a4fb-44f8-bd4c-7572a57fba18")
    public double getAverageTD() {
        return this.averageTD;
    }
    
    @objid ("98c496c6-c8d5-496f-8ac4-0e4b07595cd3")
    public int getCoeffDS() {
        return coeffDS;
    }
    
    @objid ("cbbff2ab-761b-488e-a036-04868c7c69aa")
    public int getCoeffTD() {
        return coeffTD;
    }
    
    @objid ("a78553b4-4d12-4e86-9e6b-a166141ea714")
    public void setCoeffTP(int coeffTP) {
        this.coeffTP = coeffTP;
    }

    @objid ("295a2cfa-529b-4ab8-a53d-919eea370719")
    public void setCoeffTD(int coeffTD) {
        this.coeffTD = coeffTD;
    }

    @objid ("a89ae9d7-8388-4afa-a42f-d925eb0c82e2")
    public void setCoeffDS(int coeffDS) {
        this.coeffDS = coeffDS;
    }

    @objid ("3869895f-ce3e-4368-8235-438ebb234a3b")
    public void setAverage(double average) {
        this.average = average;
    }
    
    @objid ("a8e60ae2-88ee-49fe-adc4-c78ca16c2767")
    public void setName(String name) {
        this.name = name;
    }
    
    @objid ("a7252bdc-603c-4901-a253-16a44c2f802d")
    public void setCoeff(int value) {
        this.coeff = value;
    }

    @objid ("88a28853-46c1-474a-958d-af8b6bd21326")
    public void addNote(double note, String categorie) {
        switch (categorie) {
        
        case "td" :
            this.notesTD.add(note);
            this.calculateAverageTD();
            this.calculateAverage();
            break;
        
        case "tp" :
            this.notesTP.add(note);
            this.calculateAverageTP();
            this.calculateAverage();
            break;
         
        case "ds" :
            this.noteDS = note;
            this.calculateAverage();
            break;        
        }
    }

    @objid ("d48034b8-c9cb-4bb7-885b-0ef72eda5202")
    public void calculateAverageTD() {
    	double sum = 0;
        for(double i : this.notesTD){
        	sum += i;
        }
        this.averageTD = sum/this.notesTD.size();
    }

    @objid ("0088918f-046d-4548-895e-6cad6bb81462")
    public void calculateAverageTP() {
        double sum = 0;
        for(double i : this.notesTP){
            sum += i;
        }
        this.averageTP = sum/this.notesTP.size();
    }
    


    @objid ("0f6c89f5-030c-4270-b760-ab337f7aa3af")
    public Subject(String name, int coeff, int coeffTP, int coeffTD, int coeffDS) {
        super();
        this.name = name;
        this.coeff = coeff;
        this.coeffTP = coeffTP;
        this.coeffTD = coeffTD;
        this.coeffDS = coeffDS;
    }
}
