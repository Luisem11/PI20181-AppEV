package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model;

/**
 * Created by luisernesto on 8/05/18.
 */

public class Step {
    
    private int number;
    private String title, description, action, extra, type;


    public Step(int number, String description, String title, String action, String extra, String type) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.action = action;
        this.extra = extra;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
