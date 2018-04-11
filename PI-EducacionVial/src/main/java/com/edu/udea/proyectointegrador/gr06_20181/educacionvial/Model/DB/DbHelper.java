package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private Context c;

    public DbHelper(Context context) {
        super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
        this.c = context.getApplicationContext();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + StatusContract.TABLE_TIP + " ("
                + StatusContract.Column_Tip.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StatusContract.Column_Tip.TITLE + " TEXT NOT NULL,"
                + StatusContract.Column_Tip.TITLE2 + " TEXT,"
                + StatusContract.Column_Tip.SUBTITLE + " TEXT NOT NULL,"
                + StatusContract.Column_Tip.BODY + " TEXT NOT NULL,"
                + StatusContract.Column_Tip.BODY2 + " TEXT,"
                + StatusContract.Column_Tip.TYPE + " TEXT NOT NULL)");


        //private id, title, title2, subtitle, body, body2, type;
        insertData(db);


    }

    private void insertData(SQLiteDatabase db) {


        Tip tip = new Tip("Cuidado con el Suelo Mojado!",
                "Indumentaria",
                "Con el suelo mojado el riesgo de sufrir una caída aumenta bastante, por eso es importante mermar el ritmo y hacer todas las maniobras con gran delicadeza.",
                "Con el suelo mojado las condiciones cambian completamente y nuestro manejo debe adaptarse a ellas o de lo contrario tendremos sorpresas desagradables. Recuerda tener precaución y el equipo adecuado",
                "Lo ideal es que siempre estemos preparados para cuando la lluvia aparezca. Llevar constantemente el impermeable puede ser en algunas ocasiones engorroso, así como ponérselo cuando las primeras gotas caen, pero si vamos secos y confortables a pesar del aguacero, es una garantía de que nuestra atención estará puesta al 100% en la conducción, por el contrario, ir completamente mojados, con frío, con los pies sobre el tanque de gasolina para no mojarnos las pantorrillas y además renegando por todo esto, hará que le prestemos menor atención a la calle o carretera incrementando el riesgo de tener sorpresas desagradables y disminuyendo nuestro tiempo de reacción ante cualquier eventualidad. ",
                "TM");

        db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
    }

    public Cursor getAllTips(){

        return getReadableDatabase()
                .query(
                        StatusContract.TABLE_TIP,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + StatusContract.TABLE_TIP);
        onCreate(db);

    }



}
