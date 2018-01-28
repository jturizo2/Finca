package domain.fincas.com.fincas;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import domain.fincas.com.fincas.datos.*;
import domain.fincas.com.fincas.datos.hornal;

public class trabajos extends AppCompatActivity {
    private HornalDBHelper mHornalDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mHornalDbHelper.saveLawyer(new hornal("12/12/2018","Nada",8,4));
        Cursor pr = mHornalDbHelper.getAllHornal();
        String s="";
        while (pr.moveToNext()) {
            s += pr.getString(0)+" "+pr.getString(1)+" "+pr.getString(2)+" "+pr.getString(3)+" "+pr.getString(4)+" "+pr.getString(5)+" \n";
        }
        Toast.makeText(this, s+"", Toast.LENGTH_LONG).show();

    }
}
