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
import android.widget.Toast;

public class listado_fincas extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_fincas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView = (TextView) findViewById(R.id.textView11);



        String[] campos = new String[] {"CODIGO","NOMBREF"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();

        Cursor fila = db5.query("NFINCAS", campos, null, null, null, null, null);
        String ssd ="";
        while (fila.moveToNext()) {
            ssd += "Codigo: " + fila.getString(0)+ "\n" + "Nombre de la Finca: " + fila.getString(1)+ "\n";
        }
        db5.close();
        textView.setText(ssd);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }
    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(listado_fincas.this, detalle_finca.class);
        startActivity(iw);
        finish();
    }

    public void atras(View view) {
        Intent iw = new Intent(listado_fincas.this, home_consultas.class);
        startActivity(iw);
        finish();
    }
}
