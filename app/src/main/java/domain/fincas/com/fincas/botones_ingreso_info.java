package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class botones_ingreso_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones_ingreso_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void  onBackPressed(){
    }

    public void devolver(View view) {
        Intent i = new Intent(botones_ingreso_info.this, homep.class);
        startActivity(i);
        finish();
    }

    public void ingreso_fincas(View view) {
        Intent i = new Intent(botones_ingreso_info.this, ingreso_fincas.class);
        startActivity(i);
        finish();
    }
    public void animales(View view) {
        Intent i = new Intent(botones_ingreso_info.this, ingresoTipoAnimal.class);
        startActivity(i);
        finish();
    }
    public void medicamentos(View view) {
        Intent i = new Intent(botones_ingreso_info.this, medicamentos.class);
        startActivity(i);
        finish();

    }

    public void ventas(View view) {
        Intent i = new Intent(botones_ingreso_info.this, ventas.class);
        startActivity(i);
        finish();

    }

   }
