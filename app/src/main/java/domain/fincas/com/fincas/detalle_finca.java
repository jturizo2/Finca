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
    private TextView text1,text2,text3,text4,text5,text6,lec2,text7,text8,text9,text10,text11,text12,tvacas,tvacas11,tvacas22,tvacas33;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
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

        tv1= (TextView) findViewById(R.id.tanimav);
        tv2= (TextView) findViewById(R.id.tmachosv);
        tv3= (TextView) findViewById(R.id.thembras2v);
        tv4= (TextView) findViewById(R.id.tternerosv);
        tv5= (TextView) findViewById(R.id.tterneras2v);
        tv6= (TextView) findViewById(R.id.tnovillos2v);
        tv7= (TextView) findViewById(R.id.tnovillas2v);
        tv8= (TextView) findViewById(R.id.ttoros2v);
        tv9= (TextView) findViewById(R.id.tvacas2v);




        //-------------------------------------------------------------------
        String[] campos = new String[] {"NOMBREF", "HECTAREAS", "DIVICIONES", "LOTES"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        Cursor fila = db5.query("NFINCAS", campos, null, null, null, null, null);
        //Consultas de animales propios
        Cursor sql1 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN ", null);
        Cursor sql2 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho'", null);
        Cursor sql3 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra'", null);
        Cursor sql4 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Ternero'", null);
        Cursor sql5 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Ternera'", null);
        Cursor sql6 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Novillo'", null);
        Cursor sql7 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Novilla'", null);
        Cursor sql8 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Macho' AND ETAPAP ='Toro'", null);
        Cursor sql9 = db5.rawQuery("SELECT COUNT(*) FROM ANIMALESN WHERE GENERO = 'Hembra' AND ETAPAP ='Vaca'", null);

        //Consulta de animales vendidos

        Cursor sql10 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS ", null);
        Cursor sql11 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Macho'", null);
        Cursor sql12 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Hembra'", null);
        Cursor sql13 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Macho' AND ETAPAP ='Ternero'", null);
        Cursor sql14 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Hembra' AND ETAPAP ='Ternera'", null);
        Cursor sql15 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Macho' AND ETAPAP ='Novillo'", null);
        Cursor sql16 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Hembra' AND ETAPAP ='Novilla'", null);
        Cursor sql17 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Macho' AND ETAPAP ='Toro'", null);
        Cursor sql18 = db5.rawQuery("SELECT COUNT(*) FROM VENTAS WHERE GENERO = 'Hembra' AND ETAPAP ='Vaca'", null);




        String ssd ="", ss2 ="",ss3 ="" ,ss4 ="";
        String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="";
        String s10="", s11="", s12="", s13="", s14="", s15="", s16="", s17="", s18="";


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


        while (sql10.moveToNext()) { s10 += sql10.getString(0);}
        while (sql11.moveToNext()) { s11 += sql11.getString(0);}
        while (sql12.moveToNext()) { s12 += sql12.getString(0);}
        while (sql13.moveToNext()) { s13 += sql13.getString(0);}
        while (sql14.moveToNext()) { s14 += sql14.getString(0);}
        while (sql15.moveToNext()) { s15 += sql15.getString(0);}
        while (sql16.moveToNext()) { s16 += sql16.getString(0);}
        while (sql17.moveToNext()) { s17 += sql17.getString(0);}
        while (sql18.moveToNext()) { s18 += sql18.getString(0);}



        db5.close();
        text1.setText(ssd);
        text2.setText(ss2);
        text3.setText(ss3);
        text4.setText(ss4);

        // fin consulta de finca (nombre,lotes, divisiones, hectareas)

        //Consulta de animales
        text5.setText(s1);
        text6.setText(s2);
        text7.setText(s3);
        text8.setText(s4);
        text9.setText(s5);
        text10.setText(s6);
        text11.setText(s7);
        text12.setText(s8);
        tvacas.setText(s9);

        tv1.setText(s10);
        tv2.setText(s11);
        tv3.setText(s12);
        tv4.setText(s13);
        tv5.setText(s14);
        tv6.setText(s15);
        tv7.setText(s16);
        tv8.setText(s17);
        tv9.setText(s18);



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