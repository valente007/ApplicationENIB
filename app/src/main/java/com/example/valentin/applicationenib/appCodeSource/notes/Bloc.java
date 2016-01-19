package com.example.valentin.applicationenib.appCodeSource.notes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import java.util.ArrayList;
import java.util.List;

@objid ("88b4e14e-4fef-48fc-8e68-ebd0b3f27634")
public class Bloc {
	
    @objid ("3843f467-bfd2-4892-a8b1-287c5aeae723")
    protected double average;

    @objid ("e66b242a-4e00-4b0c-b494-da48cc9d4587")
    protected String name;

    @objid ("8ba369e9-420e-4914-9dc1-89ce428c3c35")
    protected int coeff;

    public List<Subject> getSubjects() {
        return subjects;
    }

    @objid ("531f47aa-1494-4411-bede-c772baf04e69")
    protected List<Subject> subjects = new ArrayList<Subject> ();
    
    
    @objid ("dfd857bb-00a3-4736-946f-3e169e64dcb0")
    public void calculateAverage() {
        double sum = 0;
        int div = 0;
        int empty = 0;
        for(Subject i : this.subjects){
        	if(i.getAverage() == 0){
        		empty = 0;
        	}
        	else{
        		empty = 1;
        	}
            sum += i.average*i.coeff;
            div += i.coeff*empty;
        }
        if(sum != 0){
        	this.average = sum/div;
        }
    }

    @objid ("e80c7104-76ac-4e0b-a549-5263d40cc438")
    public double getAverage() {
        return this.average;
    }
    
    @objid ("b09c1d9b-be62-48e2-96b9-2e9afa7ef5de")
    public String getName() {
        return name;
    }

    @objid ("1c86279b-604b-4bce-b120-035c4c51bcf2")
    public Subject getSubject(String name) {
    	Subject subject = new Subject("none",0,0,0,0);
        for (Subject s : this.subjects){
            if(s.getName().equals(name)){
                subject = s;
                break;
            }
        }
        return subject;
    }
    
    @objid ("c0c3d9e8-c435-4a2c-8c0a-6f621f2d7dd0")
    public int getCoeff() {
        return coeff;
    }
    
    /*
     * Setting coefficient of a Bloc based on the sum up of each subject
     */
    @objid ("52fe8815-97a2-4785-8277-c847cc3f19dd")
    public void setCoeff() {
        for(Subject i : this.subjects){
            this.coeff += i.coeff;
        }
    }

    @objid ("6489a9ad-1d90-4813-8132-bba9f215f7b9")
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        this.calculateAverage();
        this.setCoeff();
    }

    @objid ("68a66c4d-6732-491d-bd42-1f24d704bff6")
    public Bloc(String name) {
        super();
        this.name = name;
        this.average = 0;
        this.calculateAverage();
    }
}
