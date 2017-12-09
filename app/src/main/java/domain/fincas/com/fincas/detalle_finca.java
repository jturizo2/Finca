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

public class detalle_finca extends AppCompatActivity {
    private TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,tvacas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_finca);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        text1 = (TextView) findViewById(R.id.nfin2);
        text2 = (TextView) findViewById(R.id.thecta2);
        text3 = (TextView) findViewById(R.id.tdivi2);
        text4 = (TextView) findViewById(R.id.tlotes2);

        text5 = (TextView) findViewById(R.id.tanima2);
        text6 = (TextView) findViewById(R.id.tmachos2);
        text7 = (TextView) findViewById(R.id.thembras2);
        text8 = (TextView) findViewById(R.id.tterneros2);
        text9 = (TextView) findViewById(R.id.tterneras2);

        text10 = (TextView) findViewById(R.id.tnovillos2);
        text11 = (TextView) findViewById(R.id.tnovillas2);
        text12 = (TextView) findViewById(R.id.ttoros2);
        tvacas = (TextView) findViewById(R.id.tvacas2);


        String[] campos = new String[] {"NOMBREF", "HECTAREAS", "DIVICIONES", "LOTES"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        Cursor fila = db5.query("NFINCAS", campos, null, null, null, null, null);
        Cursor sql1 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN ", null);
        Cursor sql2 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho'", null);
        Cursor sql3 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra'", null);

        Cursor sql4 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Ternero'", null);
        Cursor sql5 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Ternera'", null);
        Cursor sql6 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Novillo'", null);
        Cursor sql7 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Novilla'", null);
        Cursor sql8 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Toro'", null);

        Cursor sql9 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Vaca'", null);




        String ssd ="", ss2 ="",ss3 ="" ,ss4 ="";
        String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="";

        while (fila.moveToNext()) {
            ssd += fila.getString(0);
            ss2 += fila.getString(1);
            ss3 += fila.getString(2);
            ss4 += fila.getString(3);
        }

        while (sql1.moveToNext()) { s1 += sql1.getString(0);}
        while (sql2.moveToNext()) { s2 += sql2.getString(0);}
        while (sql3.moveToNext()) { s3 += sql3.getString(0);}
        while (sql4.moveToNext()) { s4 += sql4.getString(0);}
        while (sql5.moveToNext()) { s5 += sql5.getString(0);}

        while (sql6.moveToNext()) { s6 += sql6.getString(0);}
        while (sql7.moveToNext()) { s7 += sql7.getString(0);}
        while (sql8.moveToNext()) { s8 += sql8.getString(0);}
        while (sql9.moveToNext()) { s9 += sql9.getString(0);}


        db5.close();
        text1.setText(ssd);
        text1.setMovementMethod(new ScrollingMovementMethod());

        text2.setText(ss2);
        text2.setMovementMethod(new ScrollingMovementMethod());

        text3.setText(ss3);
        text3.setMovementMethod(new ScrollingMovementMethod());

        text4.setText(ss4);
        text4.setMovementMethod(new ScrollingMovementMethod());
        // fin consulta de finca (nombre,lotes, divisiones, hectareas)

        //Consulta de animales
        text5.setText(s1);
        text5.setMovementMethod(new ScrollingMovementMethod());

        text6.setText(s2);
        text6.setMovementMethod(new ScrollingMovementMethod());

        text7.setText(s3);
        text7.setMovementMethod(new ScrollingMovementMethod());

        text8.setText(s4);
        text8.setMovementMethod(new ScrollingMovementMethod());

        text9.setText(s5);
        text9.setMovementMethod(new ScrollingMovementMethod());

        text10.setText(s6);
        text10.setMovementMethod(new ScrollingMovementMethod());

        text11.setText(s7);
        text11.setMovementMethod(new ScrollingMovementMethod());

        text12.setText(s8);
        text12.setMovementMethod(new ScrollingMovementMethod());

        tvacas.setText(s9);
        tvacas.setMovementMethod(new ScrollingMovementMethod());

    }//fin oncreate
    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(detalle_finca.this, home_consultas.class);
        startActivity(iw);
        finish();
    }




}
