package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;


import android.content.ContentValues;
import android.database.Cursor;

public class Type {
    private int id;
    private String name;

    public Type(int idTip, String name) {
        this.id = idTip;
        this.name = name;
    }

    public Type(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Type.ID));
        name = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Type.NAME));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(StatusContract.Column_Type.NAME, name);
        values.put(StatusContract.Column_Type.ID, id);
        return values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
