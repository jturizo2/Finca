package domain.fincas.com.fincas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class UsersSQLiteHelper extends SQLiteOpenHelper{

    public UsersSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabla Registro de usuarios
        String sql =  "CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE TEXT,APELLIDO TEXT,USUARIO TEXT,CONTRASEÑA TEXT)";

        //Tabla actual para mantener usuario en sesion
        String SQLCREATE = "CREATE TABLE ACTUAL (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, APELLIDO TEXT)";

        //Tabla Registro de Fincas
        String SQLCREATE2 = "CREATE TABLE NFINCAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CODIGO TEXT," +
                "NOMBREF TEXT," +
                "HECTAREAS TEXT," +
                "DIVICIONES TEXT," +
                "LOTES TEXT" +
                ")";

        //Tabla Registro de Tratamientos o Medicamentos
        String SQLCREATE3 = "CREATE TABLE TRATAMIENTOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CODIGO TEXT," +
                "MEDICAMENTO TEXT," +
                "DETALLE TEXT," +
                "COSTO TEXT" +
                ")";

        //Tabla Registro de Animales
        String SQLCREATE4 = "CREATE TABLE ANIMALESN (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CODIGO TEXT UNIQUE," +
                "NOMBRE TEXT," +
                "GENERO TEXT," +
                "PROCEDENCIA TEXT," +
                "RAZA TEXT," +
                "FOTO TEXT," +
                "PROPIETARIO TEXT," +
                "PROPIETARIOA TEXT," +
                "HIERRO TEXT," +
                "PROPOSITO TEXT," +
                "FECHAINGRE TEXT," +
                "PESO TEXT," +
                "PESODESTETE TEXT," +
                "ETAPAP TEXT," +
                "VALORC TEXT," +
                "CODMAMA TEXT," +
                "CODPAPA TEXT," +
                "CODPARTO TEXT," +
                "FECHANACI TEXT," +
                "NFINCA TEXT," +
                "TIPOANIMAL TEXT," +
                "COMPPAR TEXT" +
                ")";

        //Tabla Registro de Animales Vendidos
        String SQLCREATE5 = "CREATE TABLE VENTAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CODIGO TEXT," +
                "PESO TEXT," +
                "DETALLE TEXT," +
                "COMPRADOR TEXT," +
                "VALOR TEXT," +
                "FECHAV TEXT" +
                ")";

        //Tabla Registro de Prpietarios
        String SQLCREATE6 = "CREATE TABLE PROPIETARIOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT," +
                "APELLIDO TEXT" +
                ")";
        //Tabla Registro de Hierro
        String SQLCREATE7 = "CREATE TABLE THIERRO (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "HIERRO TEXT" +
                ")";

        //Registo instalada por primera vez
        String SQLCREATE8 = "CREATE TABLE NNEW (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NEW TEXT" +
                ")";
        //Registo instalada por primera vez
        String SQLCREATE9 = "CREATE TABLE LECHE (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "COD TEXT," +
                "LITROS TEXT," +
                "FECHA TEXT" +
                ")";
        db.execSQL(sql);
        db.execSQL(SQLCREATE);
        db.execSQL(SQLCREATE2);
        db.execSQL(SQLCREATE3);
        db.execSQL(SQLCREATE4);
        db.execSQL(SQLCREATE5);
        db.execSQL(SQLCREATE6);
        db.execSQL(SQLCREATE7);
        db.execSQL(SQLCREATE8);
        db.execSQL(SQLCREATE9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
