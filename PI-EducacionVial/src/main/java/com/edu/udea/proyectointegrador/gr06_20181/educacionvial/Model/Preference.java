package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model;

/**
 * Created by luisernesto on 14/05/18.
 */

public class Preference {

    private String title, description, icon, type;

    public Preference(String title, String description, String icon, String type) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
