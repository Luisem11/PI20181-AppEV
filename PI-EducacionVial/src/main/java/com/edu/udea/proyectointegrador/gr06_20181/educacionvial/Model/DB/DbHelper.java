package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DbHelper extends SQLiteOpenHelper {

    private Context c;

    public DbHelper(Context context) {
        super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
        this.c = context.getApplicationContext();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE " + StatusContract.TABLE_USER + " ("
                + StatusContract.Column_User.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StatusContract.Column_User.MAIL + " TEXT NOT NULL,"
                + StatusContract.Column_User.NAME + " TEXT NOT NULL,"
                + StatusContract.Column_User.USER + " TEXT NOT NULL,"
                + StatusContract.Column_User.PASSWORD + " TEXT NOT NULL,"
                + StatusContract.Column_User.PICTURE + " TEXT,"
                + StatusContract.Column_User.STATE + " TEXT NOT NULL,"
                + "UNIQUE (" + StatusContract.Column_User.USER + ", "+ StatusContract.Column_User.ID + "))");

        Bitmap pic = BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_camera);

        UserStructure user = new UserStructure("Pedro Perez", "pe", "1", "pedroperez@aaaa.com","1234");
        db.insert(StatusContract.TABLE_USER, null, user.toContentValues());*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
