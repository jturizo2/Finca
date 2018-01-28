package domain.fincas.com.fincas.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ing Jorge Silva C on 28/01/2018.
 */

public class HornalDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Hor.db";

    public HornalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        db.execSQL("CREATE TABLE " + hornalContratc.HornalEntry.TABLE_NAME + " ("
                + hornalContratc.HornalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + hornalContratc.HornalEntry.ID + " TEXT NOT NULL,"
                + hornalContratc.HornalEntry.FECHA + " TEXT NOT NULL,"
                + hornalContratc.HornalEntry.TRABAJO + " TEXT NOT NULL,"
                + hornalContratc.HornalEntry.CANTJORNAL + " TEXT NOT NULL,"
                + hornalContratc.HornalEntry.VALORJORNAL + " TEXT NOT NULL,"
                + hornalContratc.HornalEntry.TOTAL + " TEXT,"
                + "UNIQUE (" + hornalContratc.HornalEntry.ID + "))");

        // Insertar datos ficticios para prueba inicial
        mockData(db);
    }


    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new hornal("01/05/2017","Carcado",5,5000));
        mockLawyer(sqLiteDatabase, new hornal("02/05/2017","Podado",9,8000));
        mockLawyer(sqLiteDatabase, new hornal("03/05/2017","Demolición",6,7000));

    }

    public long mockLawyer(SQLiteDatabase db, hornal hornal) {
        return db.insert(
                hornalContratc.HornalEntry.TABLE_NAME,
                null,
                hornal.toContentValues());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveLawyer(hornal hornal) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                hornalContratc.HornalEntry.TABLE_NAME,
                null,
                hornal.toContentValues());

    }

    public Cursor getAllHornal() {
        return getReadableDatabase()
                .query(
                        hornalContratc.HornalEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getHornalById(String hornalId) {
        Cursor c = getReadableDatabase().query(
                hornalContratc.HornalEntry.TABLE_NAME,
                null,
                hornalContratc.HornalEntry.ID + " LIKE ?",
                new String[]{hornalId},
                null,
                null,
                null);
        return c;
    }

    public int deleteHornal(String hornalId) {
        return getWritableDatabase().delete(
                hornalContratc.HornalEntry.TABLE_NAME,
                hornalContratc.HornalEntry.ID + " LIKE ?",
                new String[]{hornalId});
    }

    public int updateHornal(hornal hornal, String hornalId) {
        return getWritableDatabase().update(
                hornalContratc.HornalEntry.TABLE_NAME,
                hornal.toContentValues(),
                hornalContratc.HornalEntry.ID + " LIKE ?",
                new String[]{hornalId}
        );
    }
}
