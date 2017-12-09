package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class listado_ventas extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_ventas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView = (TextView) findViewById(R.id.textView13);

        String[] campos = new String[] {"CODIGO","PESO","DETALLE","COMPRADOR","VALOR","FECHAV"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();

        Cursor fila = db5.query("VENTAS", campos, null, null, null, null, null);
        String ssd ="";
        while (fila.moveToNext()) {
            ssd += "Codigo del Animal: " + fila.getString(0)+ "\n" + "Peso del Animal: " + fila.getString(1)+ "\n"+ "Detalle de la venta: " + fila.getString(2)+ "\n" +"Nombre del Comprador: " + fila.getString(3)+ "\n" +"Valor del Animal($): " + fila.getString(4)+"\n" +"Fecha de Venta: " + fila.getString(5)+"\n \n";
        }
        db5.close();
        textView.setText(ssd);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(listado_ventas.this, home_consultas.class);
        startActivity(iw);
        finish();
    }

}
