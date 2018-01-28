package domain.fincas.com.fincas.datos;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Ing Jorge Silva C on 28/01/2018.
 */

public class hornal {

    private String id;
    private String fecha;
    private String trabajo;
    private double cantjornal;
    private double valorjornal;
    private double total;

    public hornal(String FECHA, String TRABAJO, double CANTJORNAL, double VALORJORNAL) {
        this.fecha = FECHA;
        this.trabajo = TRABAJO;
        this.cantjornal = CANTJORNAL;
        this.valorjornal = VALORJORNAL;
        this.total = CANTJORNAL*VALORJORNAL;
    }

    public hornal(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.ID));
        fecha = cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.FECHA));
        trabajo = cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.TRABAJO));
        cantjornal =  Double.parseDouble(cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.CANTJORNAL)));
        valorjornal =  Double.parseDouble(cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.VALORJORNAL)));
        total =  Double.parseDouble(cursor.getString(cursor.getColumnIndex(hornalContratc.HornalEntry.TOTAL)));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(hornalContratc.HornalEntry.ID,  id);
        values.put(hornalContratc.HornalEntry.FECHA, fecha);
        values.put(hornalContratc.HornalEntry.TRABAJO, trabajo);
        values.put(hornalContratc.HornalEntry.CANTJORNAL, cantjornal);
        values.put(hornalContratc.HornalEntry.VALORJORNAL, valorjornal);
        values.put(hornalContratc.HornalEntry.TOTAL, total);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public double getCantjornal() {
        return cantjornal;
    }

    public double getValorjornal() {
        return valorjornal;
    }

    public double getTotal() {
        return total;
    }
}
