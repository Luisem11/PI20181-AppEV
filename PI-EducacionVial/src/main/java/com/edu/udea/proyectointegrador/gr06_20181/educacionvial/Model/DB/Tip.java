package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.ContentValues;
import android.database.Cursor;

public class Tip {
    int id;
    private String  title;
    private String title2;
    private String subtitle;
    private String body;
    private String body2;
    private String type;

    private String link;

    public Tip(String title, String subtitle, String body, String title2, String body2, String type, String link) {
        this.title = title;
        this.title2 = title2;
        this.subtitle = subtitle;
        this.body = body;
        this.body2 = body2;
        this.type = type;
        this.link = link;
    }

    public Tip(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Tip.ID));
        title = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.TITLE));
        title2 = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.TITLE2));
        subtitle = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.SUBTITLE));
        body = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.BODY));
        body2 = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.BODY2));
        type = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.TYPE));
        link = cursor.getString(cursor.getColumnIndex(StatusContract.Column_Tip.LINK));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(StatusContract.Column_Tip.TITLE, title);
        values.put(StatusContract.Column_Tip.TITLE2, title2);
        values.put(StatusContract.Column_Tip.SUBTITLE, subtitle);
        values.put(StatusContract.Column_Tip.BODY, body);
        values.put(StatusContract.Column_Tip.BODY2, body2);
        values.put(StatusContract.Column_Tip.TYPE, type);
        values.put(StatusContract.Column_Tip.LINK, link);
        return values;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody2() {
        return body2;
    }

    public void setBody2(String body2) {
        this.body2 = body2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
