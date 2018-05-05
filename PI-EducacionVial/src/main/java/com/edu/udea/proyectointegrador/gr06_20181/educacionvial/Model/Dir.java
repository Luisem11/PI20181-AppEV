package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model;

/**
 * Created by luisernesto on 3/05/18.
 */

public class Dir {
    private String title, number, action, description;

    public Dir(String title, String number, String action, String description) {
        this.title = title;
        this.number = number;
        this.action = action;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
