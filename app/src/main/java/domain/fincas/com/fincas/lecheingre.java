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

public class lecheingre extends AppCompatActivity {
    private EditText cod1,litros,fecha,etPlannedDate;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecheingre);
        cod1= (EditText) findViewById(R.id.cod2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        litros= (EditText) findViewById(R.id.litros);
        //fecha= (EditText) findViewById(R.id.fecha2);

        String fc = dateFormat.format(date.getTime());
        //fecha.setText(fc);

        //--------------- datepicker---------------------------------
        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        etPlannedDate.setText(fc);

        //--------------- datepicker---------------------------------
    }

    @Override
    public void  onBackPressed(){

        Intent i = new Intent(lecheingre.this, botones_ingreso_info.class);
        startActivity(i);
        finish();

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
    public void guardar(View view) {


        String gcod1 = cod1.getText().toString();
        String glitros = litros.getText().toString();
        String gfecha = etPlannedDate .getText().toString().replace(" ","");
        //------------------------------------------------------------------------------------
        //-----Se busca si el cod existe en los animales
        // Cod actual
        String use=cod1.getText().toString();
        // Lista de codigos existentes
        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db = admine.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT CODIGO FROM ANIMALESN", null);
        String ssd ="";
        String flag="nada";
        while (fila.moveToNext()) {
            String prube= fila.getString(0);
            if(use.equals(prube)){
                flag="algop";
                break;
            }

        }
        db.close();

        if(flag.equals("nada")){
            Toast.makeText(this, "El código no existe!!!", Toast.LENGTH_LONG).show();


        }else{
            UsersSQLiteHelper admine6 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db6 = admine6.getWritableDatabase();
            Cursor fila6 = db6.rawQuery("SELECT ID FROM ANIMALESN WHERE CODIGO='"+use+"' AND ETAPAP='Vaca'", null);
            String flag6="nada";
            while (fila6.moveToNext()) {
                String prube= fila.getString(0);
                if(use.equals(prube)){
                    flag6="algop";
                    break;
                }

            }
            db6.close();
            // Verificar si es vaca----------------
            if(flag6.equals("nada")){
                Toast.makeText(this, "El animal no es una vaca!!!", Toast.LENGTH_LONG).show();
            }else{

                    if(glitros.equals("")){
                        glitros="0";
                    }
                //Base de datos
                UsersSQLiteHelper admine9 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db9 = admine9.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("COD", gcod1);
                registro.put("LITROS", glitros);
                registro.put("FECHA", gfecha);
                db9.insert("LECHE", null, registro);
                db9.close();
                Toast.makeText(this, "Información registrada!!", Toast.LENGTH_LONG).show();


                Intent i = new Intent(lecheingre.this, botones_ingreso_info.class);
                startActivity(i);
                finish();
            }

        }

    }
}