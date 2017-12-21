package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ventas extends AppCompatActivity {

    private EditText cod_a, peso,deta,comprad, valor;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cod_a =(EditText)findViewById(R.id.cod2);
        peso =(EditText)findViewById(R.id.peso2);
        deta =(EditText)findViewById(R.id.detalle1);
        comprad =(EditText)findViewById(R.id.comprador1);
        valor =(EditText)findViewById(R.id.Valor1);


    }

    @Override
    public void  onBackPressed(){
    }

    public void back(View view) {
        Intent iw = new Intent(ventas.this, botones_ingreso_info.class);
        startActivity(iw);
        finish();
    }

    public void save(View view) {
        if ("".equals(cod_a.getText().toString())) {
            Toast.makeText(this, "Digite un codigo de animal!!", Toast.LENGTH_LONG).show();
        }
        else{
            if ("".equals(peso.getText().toString())) {
                Toast.makeText(this, "Digite el peso del animal!!", Toast.LENGTH_LONG).show();
            }
            else{
                if ("".equals(deta.getText().toString())) {
                    Toast.makeText(this, "Digite un detalle acerca de la venta!!", Toast.LENGTH_LONG).show();
                }
                else{
                    if ("".equals(comprad.getText().toString())) {
                        Toast.makeText(this, "Digite el nombre del comprador!!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if ("".equals(valor.getText().toString())) {
                            Toast.makeText(this, "Digite un para la venta!!", Toast.LENGTH_LONG).show();
                        }
                        else{

                            String gcod_a = cod_a.getText().toString();
                            String gpeso = peso.getText().toString();
                            String gdeta = deta.getText().toString();
                            String gcomprad = comprad.getText().toString();
                            String gvalor = valor.getText().toString();
                            String fecha_i =dateFormat.format(date).toString();
                            //-------------------------------------------

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
                                //Base de datos
                                Toast.makeText(this, "El código del animal no existe!!!", Toast.LENGTH_LONG).show();


                            }else{


                                UsersSQLiteHelper admine9 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                                SQLiteDatabase db9 = admine9.getWritableDatabase();
                                Cursor fila5 = db9.rawQuery("SELECT GENERO, ETAPAP FROM ANIMALESN WHERE CODIGO='"+gcod_a+"'", null);
                                String genero = "";
                                String etapa = "";
                                while (fila5.moveToNext()) {
                                    genero = fila5.getString(0);
                                    etapa = fila5.getString(1);
                                }

                                ContentValues registro = new ContentValues();
                                registro.put("CODIGO", gcod_a);
                                registro.put("PESO", gpeso);
                                registro.put("DETALLE", gdeta);
                                registro.put("COMPRADOR", gcomprad);
                                registro.put("VALOR", gvalor);
                                registro.put("FECHAV",fecha_i);
                                registro.put("GENERO",genero);
                                registro.put("ETAPAP",etapa);
                                db9.insert("VENTAS", null, registro);
                                db9.close();
                                Toast.makeText(this, "Venta Registrada!!", Toast.LENGTH_LONG).show();

                                delete(gcod_a);
                                Intent i = new Intent(ventas.this, botones_ingreso_info.class);
                                startActivity(i);
                                finish();


                            }


                            //-------------------------------------------


                        }
                    }
                }
            }
        }





    }


    public void delete(String cod){
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        db2.execSQL("DELETE FROM `ANIMALESN` WHERE `CODIGO`='"+cod+"'");
       // Toast.makeText(this, cod+" Entré", Toast.LENGTH_LONG).show();
       // System.out.println("Aqui");
        db2.close();

    }




}
