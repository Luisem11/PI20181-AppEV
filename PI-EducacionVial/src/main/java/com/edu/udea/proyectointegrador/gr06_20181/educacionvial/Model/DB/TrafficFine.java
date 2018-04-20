package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

/**
 * Created by user on 16/04/2018.
 */

public class TrafficFine {

    private String code, description, price, category;

    public TrafficFine(String code, String description, String price, String category){
        this.code = code;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
