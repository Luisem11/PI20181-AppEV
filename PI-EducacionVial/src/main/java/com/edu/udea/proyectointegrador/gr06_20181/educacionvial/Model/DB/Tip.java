package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Tip implements Parcelable {
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

    protected Tip(Parcel in) {
        id = in.readInt();
        title = in.readString();
        title2 = in.readString();
        subtitle = in.readString();
        body = in.readString();
        body2 = in.readString();
        type = in.readString();
        link = in.readString();
    }

    public static final Creator<Tip> CREATOR = new Creator<Tip>() {
        @Override
        public Tip createFromParcel(Parcel in) {
            return new Tip(in);
        }

        @Override
        public Tip[] newArray(int size) {
            return new Tip[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(title2);
        parcel.writeString(subtitle);
        parcel.writeString(body);
        parcel.writeString(body2);
        parcel.writeString(type);
        parcel.writeString(link);
    }
}
