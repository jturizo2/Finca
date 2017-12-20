package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class medicamentos extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    private EditText cod_a, med_apli,deta,cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cod_a =(EditText)findViewById(R.id.cod2);
        med_apli =(EditText)findViewById(R.id.medicamento1);
        deta =(EditText)findViewById(R.id.detalle1);
        cost =(EditText)findViewById(R.id.costo1);
    }
    @Override
    public void  onBackPressed(){
    }


    public void back(View view) {
        Intent iw = new Intent(medicamentos.this, botones_ingreso_info.class);
        startActivity(iw);
        finish();

        // Toast.makeText(this, "soy jesu!", Toast.LENGTH_LONG).show();
    }

    public void guardar_medicamento(View view){

        if ("".equals(cod_a.getText().toString())) {
            Toast.makeText(this, "Ingrese codigo del Medicamento!!", Toast.LENGTH_LONG).show();
        }
            else{
                if ("".equals(med_apli.getText().toString())) {
                    Toast.makeText(this, "Ingrese el nombre del medicamento!!", Toast.LENGTH_LONG).show();
                }
                else {
                    if ("".equals(deta.getText().toString())) {
                        Toast.makeText(this, "Ingrese una descripcion acerca del medicamento aplicado!!", Toast.LENGTH_LONG).show();
                    } else {
                        if ("".equals(cost.getText().toString())) {
                            Toast.makeText(this, "Ingrese el valor del medicamento!!", Toast.LENGTH_LONG).show();
                        }
                            else {

                            //-------------------------------------------------------
                            //-----Se busca si el cod existe en los animales
                            // Cod actual
                            String use=cod_a.getText().toString();
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



                            }else{
                                String codigo_a = cod_a.getText().toString();
                                String medicamento_a = med_apli.getText().toString();
                                String detalle_m = deta.getText().toString();
                                String costo =  cost.getText().toString();
                                String fc = dateFormat.format(date.getTime());

                                //Base de datos
                                UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                                SQLiteDatabase db3 = admine3.getWritableDatabase();

                                ContentValues registro = new ContentValues();
                                registro.put("CODIGO", codigo_a);
                                registro.put("MEDICAMENTO", medicamento_a);
                                registro.put("DETALLE", detalle_m);
                                registro.put("COSTO", costo);
                                registro.put("FECHA", fc);
                                db3.insert("TRATAMIENTOS", null, registro);
                                db3.close();
                                Toast.makeText(this, "Informacion de Medicamentos Guardado con exito!", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(medicamentos.this, botones_ingreso_info.class);
                                startActivity(i);
                                finish();

                            }

                            //-------------------------------------------------------

                              }
                        }
                }
            }

    }//fin guardar medicamento






}//fin de la clase
