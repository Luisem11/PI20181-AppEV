package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model;

/**
 * Created by luisernesto on 8/05/18.
 */

public class Step {
    
    private int number;
    private String description;


    public Step(int number, String description) {
        this.number = number;
        this.description = description;
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
}
