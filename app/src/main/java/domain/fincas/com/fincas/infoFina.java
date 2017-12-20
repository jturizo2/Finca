package domain.fincas.com.fincas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class infoFina extends AppCompatActivity {

    private EditText INI,FIN;
    private TextView lec2,tvacas11,tvacas22,tvacas33;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fina);
        INI= (EditText) findViewById(R.id.INI);
        FIN= (EditText) findViewById(R.id.FIN);
        tvacas11 = (TextView) findViewById(R.id.tvacas11);
        tvacas22 = (TextView) findViewById(R.id.tvacas22);
        tvacas33 = (TextView) findViewById(R.id.tvacas33);
        lec2 = (TextView) findViewById(R.id.lec2);

        String fc = dateFormat.format(date.getTime());
        FIN.setText(fc);

    }
    public void consul8(View view) {
        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
        String uni = INI.getText().toString();
        String fin = FIN.getText().toString();
        try{
            Date iini = sdt.parse(uni);
            Date ifin = sdt.parse(fin);

            if(ifin.after(iini)){

                //-----------Conteo leche------------------
                UsersSQLiteHelper admine33 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db33 = admine33.getWritableDatabase();
                Cursor fila33 = db33.rawQuery("SELECT LITROS,FECHA FROM LECHE", null);
                String ssd1="";
                Double data = 0.0;
                while (fila33.moveToNext()) {
                    String PRUBE =fila33.getString(1) ;
                    Date IPRUBE = sdt.parse(PRUBE);
                    Boolean p = iini.before(IPRUBE);
                    Boolean q =  ifin.after(IPRUBE);
                    if(p && q){
                        data  += Double.parseDouble(fila33.getString(0));
                    }
                }
                db33.close();
                lec2.setText(data.toString());

                //-----------Conteo medicamentos------------------
                UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db3 = admine3.getWritableDatabase();
                Cursor fila3 = db3.rawQuery("SELECT COSTO,FECHA FROM TRATAMIENTOS", null);
                ssd1="";
                data = 0.0;
                while (fila3.moveToNext()) {
                    String PRUBE =fila3.getString(1) ;
                    Date IPRUBE = sdt.parse(PRUBE);
                    Boolean p = iini.before(IPRUBE);
                    Boolean q =  ifin.after(IPRUBE);
                    if(p && q){
                        data  += Double.parseDouble(fila3.getString(0));
                    }                }
                db3.close();
                tvacas33.setText(data.toString());
                //-----------Conteo COMPRAS------------------
                UsersSQLiteHelper admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db1 = admine1.getWritableDatabase();
                Cursor fila1 = db1.rawQuery("SELECT VALORC,FECHAINGRE FROM ANIMALESN WHERE COMPPAR='0'", null);
                ssd1="";
                data = 0.0;
                while (fila1.moveToNext()) {

                    String PRUBE =fila1.getString(1) ;
                    Date IPRUBE = sdt.parse(PRUBE);
                    Boolean p = iini.before(IPRUBE);
                    Boolean q =  ifin.after(IPRUBE);
                    if(p && q){
                        data  += Double.parseDouble(fila1.getString(0));
                    }
                }
                db1.close();
                tvacas11.setText(data.toString());
                //-----------Conteo VENTAS------------------
                UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db2 = admine2.getWritableDatabase();
                Cursor fila2 = db2.rawQuery("SELECT VALOR,FECHAV FROM VENTAS", null);
                ssd1="";
                data = 0.0;
                while (fila2.moveToNext()) {
                    String PRUBE =fila2.getString(1) ;
                    Date IPRUBE = sdt.parse(PRUBE);
                    Boolean p = iini.before(IPRUBE);
                    Boolean q =  ifin.after(IPRUBE);
                    if(p && q){
                        data  += Double.parseDouble(fila2.getString(0));
                    }
                }
                db2.close();
                tvacas22.setText(data.toString());


            }else{

                Toast.makeText(this,"La fecha final debe ser mayor a la inicial!!", Toast.LENGTH_LONG).show();


            }
        }catch (Exception e){
            Toast.makeText(this, "Error: Formato de fecha incorrecto.", Toast.LENGTH_LONG).show();
        }



    }
    @Override
    public void  onBackPressed(){

    }
    public void atras(View view) {
        Intent iw = new Intent(infoFina.this, home_consultas.class);
        startActivity(iw);
        finish();
    }


}
