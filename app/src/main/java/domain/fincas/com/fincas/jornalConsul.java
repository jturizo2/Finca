package domain.fincas.com.fincas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class jornalConsul extends AppCompatActivity {
    private TextView read;
    private EditText INI,FIN;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornal_consul);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        read = (TextView) findViewById(R.id.read);
        String fc = dateFormat.format(date.getTime());

        //--------------- datepicker---------------------------------
        INI = (EditText) findViewById(R.id.INI);
        INI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        INI.setText(fc);

        FIN = (EditText) findViewById(R.id.FIN);
        FIN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });
        FIN.setText(fc);

        //--------------- datepicker---------------------------------

    }
    //metodo mostrar datepicker
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                INI.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");


    } // fin metodo mostrar datepicker

    //metodo mostrar datepicker
    private void showDatePickerDialog1() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                FIN.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");


    } // fin metodo mostrar datepicker

    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(jornalConsul.this, home_consultas.class);
        startActivity(iw);
        finish();
    }

    public void consul8(View view) {
        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
        String uni = INI.getText().toString();
        String fin = FIN.getText().toString();
        try{
            Date iini = sdt.parse(uni.replace(" ",""));
            Date ifin = sdt.parse(fin.replace(" ",""));

            if(ifin.after(iini)){

                //-----------Conteo leche------------------
                UsersSQLiteHelper admine33 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db33 = admine33.getWritableDatabase();
                Cursor fila = db33.rawQuery("SELECT ID, FECHA, TRABAJO,CANTJORNAL, VALORJORNAL, TOTAL FROM HORNAL", null);
                String ssd="";
                Double data = 0.0;
                while (fila.moveToNext()) {

                    String PRUBE =(fila.getString(1)).replace(" ","") ;
                    Date IPRUBE = sdt.parse(PRUBE);
                    System.out.println("ee:"+ PRUBE);

                    Boolean p = iini.before(IPRUBE);
                    Boolean q =  ifin.after(IPRUBE);
                    if(p && q){
                        ssd +=    "Id: "+fila.getString(0)+ "\n"
                                + "Fecha: " + fila.getString(1)+ "\n"
                                + "Trabajo: " + fila.getString(2)+ "\n"
                                + "Jornales: " + fila.getString(3)+ "\n"
                                + "Valor Jornal: " + fila.getString(4)+ "\n"
                                + "Total: " + fila.getString(5)+ "\n"
                                + "-------------------\n"
                        ;
                        data  += Double.parseDouble(fila.getString(5));

                    }
                }
                db33.close();
                read.setText(ssd+ "\n  Total suma de trabajos: "+data.toString());
            }else{

                Toast.makeText(this,"La fecha final debe ser mayor a la inicial!!", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e+"Error: Formato de fecha incorrecto.", Toast.LENGTH_LONG).show();
        }



    }


}
