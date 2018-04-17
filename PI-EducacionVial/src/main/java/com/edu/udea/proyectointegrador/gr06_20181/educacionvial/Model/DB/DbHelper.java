package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


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

        db.execSQL("CREATE TABLE " + StatusContract.TABLE_TYPE + " ("
                + StatusContract.Column_Type.NAME + " TEXT NOT NULL, "
                + StatusContract.Column_Type.IDTIP + " INTEGER NOT NULL," +
                " CONSTRAINT PK PRIMARY KEY ( " + StatusContract.Column_Type.NAME +
                " , " + StatusContract.Column_Type.IDTIP +" ))" );


        insertData(db);


    }

    private void insertData(SQLiteDatabase db) {


        Tip tip = new Tip("Cuidado con el Suelo Mojado!",
                "Indumentaria",
                "Con el suelo mojado el riesgo de sufrir una caída aumenta bastante, por eso es importante mermar el ritmo y hacer todas las maniobras con gran delicadeza.",
                "Con el suelo mojado las condiciones cambian completamente y nuestro manejo debe adaptarse a ellas o de lo contrario tendremos sorpresas desagradables. Recuerda tener precaución y el equipo adecuado",
                "Lo ideal es que siempre estemos preparados para cuando la lluvia aparezca. Llevar constantemente el impermeable puede ser en algunas ocasiones engorroso, así como ponérselo cuando las primeras gotas caen, pero si vamos secos y confortables a pesar del aguacero, es una garantía de que nuestra atención estará puesta al 100% en la conducción, por el contrario, ir completamente mojados, con frío, con los pies sobre el tanque de gasolina para no mojarnos las pantorrillas y además renegando por todo esto, hará que le prestemos menor atención a la calle o carretera incrementando el riesgo de tener sorpresas desagradables y disminuyendo nuestro tiempo de reacción ante cualquier eventualidad. ",
                "TM");

        long id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int int_id = (int) id;
        Type type = new Type(int_id,"TM");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(int_id,"IM");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(int_id,"IF");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(int_id,"N");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());

        tip = new Tip("Ante la señal de PARE, ¡pare!",
                "Sabes cuanto vale una multa por pasarte una señal de pare?",
                "Deténgase totalmente antes de la línea y/o señal de pare. Hágalo con precaución porque pueden venir vehículos detrás de usted. Advierta si hay peatones sobre la cebra. Recuerda que la prudencia puede salvar vidas.",
                "Deténgase totalmente antes de la línea y/o señal de pare. Hágalo con precaución porque pueden venir vehículos detrás de usted. Advierta si hay peatones sobre la cebra. Recuerda que la prudencia puede salvar vidas.",
                "#",
                "TM");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;
        type = new Type(int_id,"IF");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(int_id,"N");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());

        tip = new Tip("Atento al Pico y Placa",
                "¿Quieres que te lo recordemos?",
                "Esta es una de las fallas más comunes, e implica inmovilización del vehículo",
                "Esta es una de las fallas más comunes, e implica inmovilización del vehículo",
                "Programar recordatorio",
                "T");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;
        type = new Type(int_id,"IM");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(int_id,"N");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());



        //        tip = new Tip("",
//                "",
//                "",
//                "",
//                "",
//                "");
//
//        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
//        int_id = (int) id;
//        type = new Type(int_id,"TM");
//        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
//        type = new Type(int_id,"IM");
//        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
//        type = new Type(int_id,"IF");
//        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
//        type = new Type(int_id,"N");
//        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());



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

    public Cursor getTipById(int tipId) {
        Cursor c = getReadableDatabase().query(
                StatusContract.TABLE_TIP,
                null,
                StatusContract.Column_Tip.ID + " = ?",
                new String[]{""+tipId},
                null,
                null,
                null);
        return c;
    }

    public Cursor getAllTypes(){

        return getReadableDatabase()
                .query(
                        StatusContract.TABLE_TYPE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getTypeById(int typeId) {
        Cursor c = getReadableDatabase().query(
                StatusContract.TABLE_TYPE,
                null,
                StatusContract.Column_Type.IDTIP + " = ?",
                new String[]{""+typeId},
                null,
                null,
                null);
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + StatusContract.TABLE_TIP);
        db.execSQL("DROP TABLE IF EXISTS " + StatusContract.TABLE_TYPE);
        onCreate(db);

    }



}
