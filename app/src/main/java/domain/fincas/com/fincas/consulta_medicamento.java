package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class consulta_medicamento extends AppCompatActivity {
    private EditText cod_animal;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medicamento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cod_animal = (EditText) findViewById(R.id.editText6);
        textView = (TextView) findViewById(R.id.textView7);
    }

    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(consulta_medicamento.this, home_consultas.class);
        startActivity(iw);
        finish();
    }

    public void consultar(View view){
        if ("".equals(cod_animal.getText().toString())) {
            Toast.makeText(this, "Debe ingresar un codigo de animal!!", Toast.LENGTH_LONG).show();
        } else{
            //-----Se busca si el cod existe en los animales
            // Cod actual
            String use=cod_animal.getText().toString();
            // Lista de codigos existentes
            UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db = admine.getWritableDatabase();
            Cursor fila = db.rawQuery("SELECT CODIGO FROM ANIMALESN", null);
            String ssd ="";
            String flag="nada";
            while (fila.moveToNext()) {
                String prube= fila.getString(0);
                if(use.equals(prube)){
                    flag="algop";
                    break;
                }

            }
            db.close();


            if(flag.equals("nada")){
                Toast.makeText(this, "El código no existe!!!", Toast.LENGTH_LONG).show();
                limpiar();


            }else{
                UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db2 = admine2.getWritableDatabase();
                Cursor fila2 = db2.rawQuery("SELECT CODIGO, MEDICAMENTO, DETALLE, COSTO  FROM TRATAMIENTOS WHERE  CODIGO='"+use+"'", null);
                String ssd2 ="";
                while (fila2.moveToNext()) {
                    ssd2 += "CODIGO: " + fila2.getString(0)+ "\n"
                            + "MEDICAMENTO: " + fila2.getString(1)+ "\n"
                            + "DETALLE: " + fila2.getString(2)+ "\n"+
                            "COSTO: " + fila2.getString(3)+ "\n";

                }
                db2.close();
                if(ssd2.equals("")){
                    Toast.makeText(this, "El animal no tiene medicamento registrado.", Toast.LENGTH_LONG).show();
                }
                textView.setText(ssd2);
                textView.setMovementMethod(new ScrollingMovementMethod());

            }

            }
    } //fin metodo consultar

    private void limpiar(){
        textView.setText("");
    }
}
