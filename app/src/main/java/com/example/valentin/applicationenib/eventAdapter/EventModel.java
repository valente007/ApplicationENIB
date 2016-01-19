package com.example.valentin.applicationenib.eventAdapter;

/**
 * Created by Valentin on 06/12/2015.
 */
public class EventModel {

    private String name;
    private boolean selected;

    public EventModel(String name) {
        this.name = name;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
