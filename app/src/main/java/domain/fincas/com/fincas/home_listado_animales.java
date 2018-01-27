package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home_listado_animales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listado_animales);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(home_listado_animales.this, home_consultas.class);
        startActivity(iw);
        finish();
    }

    public void listado_animales_parto(View view) {
        Intent iw = new Intent(home_listado_animales.this, listado_animales_parto.class);
        startActivity(iw);
        finish();
        finish();
    }

    public void listado_animales_compra(View view) {
        Intent iw = new Intent(home_listado_animales.this, listado_animales.class);
        startActivity(iw);
        finish();
    }

    public void listado_animales_existentes(View view) {
        Intent iw = new Intent(home_listado_animales.this, listado_animales_existentes.class);
        startActivity(iw);
        finish();
    }

    public void listado_animales_codigo(View view) {
        Intent iw = new Intent(home_listado_animales.this, intoCode.class);
        startActivity(iw);
        finish();
    }


}
