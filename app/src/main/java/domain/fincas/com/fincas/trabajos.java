package domain.fincas.com.fincas;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.fincas.com.fincas.datos.*;
import domain.fincas.com.fincas.datos.hornal;

public class trabajos extends AppCompatActivity {

    private EditText etPlannedDate,trabajo,cantidad,valorJ,detalle,cantinsu,valorinsu;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    private HornalDBHelper mHornalDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //fecha por defecto
        String fc = dateFormat.format(date.getTime());

        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        trabajo = (EditText) findViewById(R.id.trabajo);
        cantidad = (EditText) findViewById(R.id.cantJornal);
        valorJ = (EditText) findViewById(R.id.valoj);
        detalle = (EditText) findViewById(R.id.trabjo);
        cantinsu = (EditText) findViewById(R.id.cantornal);
        valorinsu = (EditText) findViewById(R.id.vaoj);


        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        etPlannedDate.setText(fc);

        /*
        mHornalDbHelper = new HornalDBHelper(trabajos.this);
        mHornalDbHelper.saveHornal(new hornal("12/12/2018","Nada",8,4));
        Cursor pr = mHornalDbHelper.getAllHornal();
        String s = "algo";
        while (pr.moveToNext()) {
            s += pr.getString(0)+" "+pr.getString(1)+" "+pr.getString(2)+" "+pr.getString(3)+" "+pr.getString(4)+" "+pr.getString(5)+" \n";
        }
        Toast.makeText(this, s+"", Toast.LENGTH_LONG).show();
*/
    }
    //metodo mostrar datepicker
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");


    } // fin metodo mostrar datepicker
    public void save (View view) {
        String fecha = etPlannedDate.getText().toString();
        String trabaj = trabajo.getText().toString();
        String canti = cantidad.getText().toString();
        String valor = valorJ.getText().toString();
        String deta = detalle.getText().toString();
        String cantis = cantinsu.getText().toString();
        String valins = valorinsu.getText().toString();
        //Guardar en Base de datos:

        UsersSQLiteHelper admine9 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db9 = admine9.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("FECHA",fecha);
        registro.put("TRABAJO",trabaj);
        registro.put("CANTJORNAL",canti);
        registro.put("VALORJORNAL",valor);
        double x =Double.parseDouble(canti)*Double.parseDouble(valor);
        registro.put("SUBTOJOR",x);
        registro.put("DETALLE",deta);
        registro.put("CANTINSUMOS",cantis);
        registro.put("VALORINSUMO",valins);
        double y = Double.parseDouble(cantis)*Double.parseDouble(valins);
        registro.put("SUBTOINS",y);
        registro.put("TOTAL",x + y);


        db9.insert("HORNAL", null, registro);
        db9.close();
        Toast.makeText(this, "Trabajo  Registrado!!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(trabajos.this, botones_ingreso_info.class);
        startActivity(i);
        finish();

    }
    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(trabajos.this, botones_ingreso_info.class);
        startActivity(iw);
        finish();
    }
}
