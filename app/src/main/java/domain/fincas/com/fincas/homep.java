package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class homep extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homep);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }




    public void configuracion(View view) {
        Intent i = new Intent(homep.this, home_configuracion.class);
        startActivity(i);
        finish();

    }

    public void consultas(View view) {
        Intent i = new Intent(homep.this, home_consultas.class);
        startActivity(i);
        finish();

    }

    public void ingreso_info(View view) {
        Intent i = new Intent(homep.this, botones_ingreso_info.class);
        startActivity(i);
        finish();

    }

    public void backup(View view) {
        Intent i = new Intent(homep.this, home_datos.class);
        startActivity(i);
        finish();

    }

    public void cerrar(View view) {
        //Borrar usuario actual
        UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine3.getWritableDatabase();
        db3.delete("ACTUAL",null,null);
        Intent choo2 = new Intent(homep.this, MainActivity.class);
        startActivity(choo2);
        finish();
    }






}