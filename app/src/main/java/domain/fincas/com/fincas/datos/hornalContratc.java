package domain.fincas.com.fincas.datos;

import android.provider.BaseColumns;

/**
 * Created by Ing Jorge Silva C on 28/01/2018.
 */

public class hornalContratc {
    public static abstract class HornalEntry implements BaseColumns {
        public static final String TABLE_NAME = "HORNAL";
        public static final String ID = "ID";
        public static final String FECHA = "FECHA";
        public static final String TRABAJO = "TRABAJO";
        public static final String CANTJORNAL = "CANTJORNAL";
        public static final String VALORJORNAL = "VALORJORNAL";
        public static final String TOTAL = "TOTAL";
    }
}
