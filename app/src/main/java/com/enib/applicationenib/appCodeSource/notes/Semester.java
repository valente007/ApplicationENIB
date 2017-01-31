package com.enib.applicationenib.appCodeSource.notes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import java.util.ArrayList;
import java.util.List;

@objid ("6486c38e-ee9b-48c4-a317-a5faacc4bf02")
public class Semester {
	
    @objid ("391329c5-7ec4-4c45-a544-bdc0429b0f1d")
    protected int semesterNbr;

    @objid ("36615d99-727a-4197-9fef-33a23a1a13d8")
    protected double average;

    @objid ("ae552001-8bd5-402b-9678-068e781e8bfe")
    protected List<Bloc> blocs = new ArrayList<Bloc> ();    
    

    @objid ("e8c4d7bc-b640-4ead-abea-ce8e182cd86c")
    public int getSemesterNbr() {
        return this.semesterNbr;
    }
    
    @objid ("e9b9a76f-0d14-4f91-85e7-b4c14ae39a9a")
    public List<Bloc> getBlocs() {
        return this.blocs;
    }
    
    @objid ("8da5c04e-468f-4cd8-b9d2-d49ff726c458")
    public double getAverage() {
        return this.average;
    }
    
    @objid ("871f358e-8135-463a-ae01-61980b411248")
    public Bloc getBloc(String name) {
        Bloc bloc = new Bloc("none");
        for(Bloc b : this.getBlocs()){
            if(b.getName().equals(name)){
                bloc=b;
                break;
            }
        }
        return bloc;
    }

    @objid ("97c88a46-8b0c-414e-b919-4fd0c77d7d40")
    public void calculateAverage() {
        double sum = 0;
        int div = 0;
        int empty = 0;
        for(Bloc i : this.blocs){
        	if(i.getAverage() == 0){
        		empty = 0;
        	}
        	else{
        		empty = 1;
        	}
            sum += i.average*i.coeff;
            div += i.coeff*empty;
        }
        this.average = sum/div;
    }

    @objid ("a262b464-3556-4ad4-9031-305cacdbf2ab")
    public Semester showSemester() {
        return null;
    }

    @objid ("5d2903e7-f585-4bc5-8b49-62d96ec7051e")
    public void addBloc(Bloc bloc) {
        this.blocs.add(bloc);
        this.calculateAverage();
    }

    
    @objid ("595fd432-4cff-4f3e-8121-75dbef6df1fd")
    public Semester(int semesterNbr, List<Bloc> blocs) {
        super();
        this.semesterNbr = semesterNbr;
        this.blocs = blocs;
        this.average = 0;
        this.calculateAverage();
    }
}
