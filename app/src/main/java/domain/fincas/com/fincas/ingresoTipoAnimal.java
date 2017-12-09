package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ingresoTipoAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_tipo_animal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void  onBackPressed(){
    }

    public void naci(View view) {
        Intent iw = new Intent(ingresoTipoAnimal.this,ingreso_animales .class);
        startActivity(iw);
        finish();
    }
    public void parto(View view) {
        Intent iw = new Intent(ingresoTipoAnimal.this, ingresoAnimalesParto.class);
        startActivity(iw);
        finish();
    }

    public void anima_exis(View view) {
        Intent iw = new Intent(ingresoTipoAnimal.this, ingreso_animales_existentes.class);
        startActivity(iw);
        finish();
    }

    public void atras(View view) {
        Intent iw = new Intent(ingresoTipoAnimal.this, botones_ingreso_info.class);
        startActivity(iw);
        finish();
   }

}
