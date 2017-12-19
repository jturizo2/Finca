package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home_consultas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_consultas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void  onBackPressed(){
    }

    public void consulta_medicamento(View view) {
        Intent iw = new Intent(home_consultas.this, consulta_medicamento.class);
        startActivity(iw);
        finish();
    }

    public void listado_fincas(View view) {
        Intent iw = new Intent(home_consultas.this, detalle_finca.class);
        startActivity(iw);
        finish();
    }

    public void listado_ventas(View view) {
        Intent iw = new Intent(home_consultas.this, listado_ventas.class);
        startActivity(iw);
        finish();
    }

    public void home_listado_animales(View view) {
        Intent iw = new Intent(home_consultas.this, home_listado_animales.class);
        startActivity(iw);
        finish();
    }

    public void atras(View view) {
        Intent iw = new Intent(home_consultas.this, homep.class);
        startActivity(iw);
        finish();
    }

    public void leche(View view) {
        Intent iw = new Intent(home_consultas.this, intoCodeLeche.class);
        startActivity(iw);
        finish();
    }



}
