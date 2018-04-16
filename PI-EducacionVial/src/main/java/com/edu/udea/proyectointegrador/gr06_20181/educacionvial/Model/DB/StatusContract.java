package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB;

import android.provider.BaseColumns;

public class StatusContract {
    public static final String DB_NAME = "educacionvial.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_TIP = "tip";
    public static final String TABLE_TYPE = "type";

    public class Column_Tip {

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String TITLE2 = "title2";
        public static final String BODY = "body";
        public static final String BODY2 = "body2";
        public static final String SUBTITLE = "subtitle";
        public static final String TYPE = "type";
    }

    public class Column_Type {

        public static final String ID = "id";
        public static final String IDTIP = "idtip";
        public static final String NAME = "name";
    }

}
