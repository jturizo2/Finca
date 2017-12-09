package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home_configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_configuracion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void  onBackPressed(){
    }

    public void hierro(View view) {
        Intent i = new Intent(home_configuracion.this, hierro.class);
        startActivity(i);
        finish();

    }

    public void propietario(View view) {
        Intent i = new Intent(home_configuracion.this, propietario.class);
        startActivity(i);
        finish();

    }

    public void finca(View view) {
        Intent i = new Intent(home_configuracion.this, ingreso_fincas.class);
        startActivity(i);
        finish();

    }

    public void atras(View view) {
        Intent i = new Intent(home_configuracion.this, homep.class);
        startActivity(i);
        finish();

    }

}
