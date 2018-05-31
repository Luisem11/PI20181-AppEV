package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.content.ContentValues;
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
                + StatusContract.Column_Tip.TYPE + " TEXT NOT NULL,"
                + StatusContract.Column_Tip.LINK + " TEXT )");

        db.execSQL("CREATE TABLE " + StatusContract.TABLE_TYPE + " ("
                + StatusContract.Column_Type.NAME + " TEXT NOT NULL, "
                + StatusContract.Column_Type.ID + " INTEGER PRIMARY KEY NOT NULL)" );

        db.execSQL("CREATE TABLE " + StatusContract.TABLE_TYPE_TIP + " ("
                + StatusContract.Column_Type_Tip.ID_TIP + " TEXT NOT NULL, "
                + StatusContract.Column_Type_Tip.ID_TYPE + " INTEGER NOT NULL," +
                " CONSTRAINT PK_INT_SUB PRIMARY KEY ( " + StatusContract.Column_Type_Tip.ID_TIP +
                " , " + StatusContract.Column_Type_Tip.ID_TYPE + " ))");

        insertData(db);


    }

    private void insertData(SQLiteDatabase db) {

        Type type = new Type(1,"Técnicas de Manejo");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(2,"Normatividad");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(3,"Indumentaria");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());
        type = new Type(4,"Infracciones");
        db.insert(StatusContract.TABLE_TYPE, null, type.toContentValues());



        Tip tip = new Tip("Cuidado con el Suelo Mojado!",
                "Con el suelo mojado el riesgo de sufrir una caída aumenta bastante, por eso es importante reducir el ritmo y hacer todas las maniobras con gran delicadeza.",
                "Con el suelo mojado las condiciones cambian completamente y nuestro manejo debe adaptarse a ellas o de lo contrario tendremos sorpresas desagradables.",
                "Indumentaria",
                "\"Lo ideal es que siempre estemos preparados para cuando la lluvia aparezca. \n" +
                        "Llevar constantemente el equipo adecuado hace parte de la seguridad en la vía, si vamos secos y confortables a pesar del aguacero, es una garantía de que nuestra atención estará puesta al 100% en la conducción. Asegurate de tener el equipo adecuado:\n" +
                        "\n" +
                        "Botas\n" +
                        "Chaqueta\n" +
                        "Pantalones \n" +
                        "Guantes\n" +
                        "Casco Adecuado\"",
                "L",
                "¿Quieres saber más de Tecnicas de manejo en la lluvia?:https://www.pasionbiker.com/10-tips-de-manejo-en-lluvia/");


        long id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int int_id = (int) id;

        ContentValues values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 1);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 4);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);


        tip = new Tip("Ante la señal de pare, ¡PARE!",
                "¿Sabes de cuanto es la multa por pasarse un semáforo en rojo?",
                "No importa que debas llegar rápido, lo importante es llegar bien.",
                "¿Sabes de cuanto es la multa por pasarse un semáforo en rojo?",
                "Pasarse un semáforo en rojo es de las infracciones más comunes en Colombia, y de las que más causan accidentes.\n" +
                        "\n" +
                        "Estacionar en sitios prohibidos, transitar en Pico Placa o no parar ante un semáforo en rojo tendrá una multa de $781.200, además de esto, te retienen el vehículo hasta que pagues la multa!. \n" +
                        "\n" +
                        "Cuida tu bolsillo, cuida tu vida.",
                "Normal",
                "¿Quieres saber más acerca de las infracciones?:1Multas");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 2);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 3);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);


        tip = new Tip("No olvides el PICO Y PLACA",
                "El PICO y PLACA  es un compromiso con la movilidad y el medio ambiente de la ciudad. Acá todo lo que debes saber...",
                "El PICO y PLACA es un compromiso con la movilidad y el medio ambiente de la ciudad. Se rota cada seis (6) meses aproximadamente.",
                "¿Como se que dias tengo PICO y PLACA?",
                "Puedes consultar la página de la secretaria de movilidad de la ciudad\n" +
                        "\n" +
                        "Recuerda que aplica de 7:00 a.m a 8:30 a.m. y de 5:30 p.m. a 7:00 p.m.",
                "Normal",
                "¿Quieres saber más?:https://www.medellin.gov.co/movilidad/de-interes/pico-placa");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 2);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 4);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        tip = new Tip("¿Sabes que pasa luego de que te multan?",
                "Conoce cómo pueden sancionar y qué hacer si te multan",
                "¿Alguna vez has pensado todo lo que conlleva infringir una norma de tránsito?\n" +
                        "Además de la multa te pueden retener el vehículo o suspender e incluso cancelar tu licencia de conducción\"",
                "Si haces el curso para infractores tienes un descuento en las multas",
                "Para obtener el descuento, deberá realizar el curso dentro de los siguientes términos:\n" +
                        "\n" +
                        "Comparendos notificados en vía: 50% de descuento en el valor de la sanción, si realiza el curso y el pago dentro de los cinco (5) días hábiles siguientes a la imposición o el 25% de descuento, si realiza el curso y el pago entre los días 6 y 20 hábiles siguientes. \n" +
                        "\n" +
                        "Comparendos electrónicos notificados por correspondencia: 50% de descuento en el valor de la sanción, si realiza el curso y el pago entre el día 1 a 11 hábil siguiente a la fecha de notificación de la infracción o el 25% de descuento si realiza el curso y el pago entre el día 12 a 26 hábil siguiente a la fecha de notificación de la infracción.",
                "Lista",
                "¿Quieres saber más?:https://www.condutramitesclub.com.co/servicios/cursos-para-infractores/");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 4);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        tip = new Tip("Fotomultas, ¿Que deberías saber al respecto?",
                "Este tipo de multas se volvieron muy comunes, aquí todo lo que necesitas saber",
                "No detenerse ante una luz roja de semáforo, conducir un vehículo con exceso de velocidad y no respetar el paso de peatones que cruzan una vía, son solo algunas de las infracciones que se sancionan con las fotodetecciones.",
                "¿Como sé que fui multado?",
                "Después de realizar la revisión de las imágenes captadas por las cámaras de Fotodetección, se hace conexión con el RUNT para confirmar las características del vehículo y los datos personales del propietario. Al día siguiente de la elaboración del comparendo es entregado a la empresa de correo y enviado a la dirección registrada del último propietario del vehículo. Después de recibida la Fotodetección, el ciudadano puede efectuar el pago dentro de los once (11) días hábiles siguientes y podrá obtener un descuento del cincuenta por ciento (50%) o podrá cancelar el setenta y cinco (75%) del valor de la multa, si paga dentro de los veinte seis días (26) siguientes al recibo de esta citación. En estos casos deberá asistir obligatoriamente a un curso sobre normas de tránsito.",
                "Normal",
                "¿Quieres saber más?:https://www.medellin.gov.co/simm/preguntas-frecuentes-fotodeteccion");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;
        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 2);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 4);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

        tip = new Tip("¡Cuidado donde parqueas!",
                "¿Quieres evitar ser multado por dejar tu vehículo en una zona prohibidas?",
                "El aparcar en una zona prohibida o que no cumple con los requisitos para esta función, puede no solo acarrear grandes multas, sino también ocasionar accidentes, Ten mucho cuidado donde dejas tu vehículo!",
                "¿Dónde no me puedo estacionar?",
                "Está completamente prohibido estacionar vehículos sobe andenes, zonas verdes o sobre espacio público destinado para peatones. Tampoco podrás estacionar tu automóvil en autopistas, zonas de seguridad o en medio de un cruce. Evita estacionar en lugares destinados para el transporte público y tampoco en los carriles destinados especialmente para la circulación de estos. Y para evitar, en caso de una emergencia, retrasar a los organismos de socorro, no dejes tu auto al lado de un hidrante.",
                "Normal",
                "null");

        id =  db.insert(StatusContract.TABLE_TIP, null, tip.toContentValues());
        int_id = (int) id;

        values = new ContentValues();
        values.put(StatusContract.Column_Type_Tip.ID_TIP, int_id);
        values.put(StatusContract.Column_Type_Tip.ID_TYPE, 4);
        db.insert(StatusContract.TABLE_TYPE_TIP, null, values);

//        tip = new Tip("",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "");
//



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
                        " RANDOM() LIMIT 5");
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

    public Cursor getTypeNameById(int tipId) {
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
                StatusContract.TABLE_TYPE_TIP,
                null,
                StatusContract.Column_Type_Tip.ID_TIP + " = ?",
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


    public Cursor getTipByType(int typeId) {
        return getReadableDatabase().query(
                StatusContract.TABLE_TYPE_TIP
                        + " INNER JOIN " + StatusContract.TABLE_TIP
                        + " ON " + StatusContract.TABLE_TYPE_TIP + "."
                        + StatusContract.Column_Type_Tip.ID_TIP + "="
                        + StatusContract.TABLE_TIP + "."
                        + StatusContract.Column_Tip.ID,
                null,
                StatusContract.Column_Type_Tip.ID_TYPE + " LIKE ?",
                new String[]{""+typeId},
                null,
                null,
                null);

    }
}
