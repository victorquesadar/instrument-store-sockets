package instrumentos.logic;

public class Protocol {
    public static final String SERVER = "localhost";
    public static final int PORT = 1234;
    public static final int TipoInstrumento_create=100;
    public static final int TipoInstrumento_read=101;
    public static final int TipoInstrumento_update=102;
    public static final int TipoInstrumento_delete=103;
    public static final int TipoInstrumento_search=104;


    public static final int Intrumento_create=200;
    public static final int Intrumento_read=201;
    public static final int Intrumento_update=202;
    public static final int Intrumento_delete=203;
    public static final int Intrumento_search=204;


    public static final int Calibracion_create=300;
    public static final int Calibracion_read=301;
    public static final int Calibracion_update=302;
    public static final int Calibracion_delete=303;
    public static final int Calibracion_search=304;

    public static final int Medida_create=400;
    public static final int Medida_read=401;
    public static final int Medida_update=402;
    public static final int Medida_search=403;




    public static final int SYNC=10;
    public static final int ASYNC=11;

    public static final int DELIVER=12;
    public static final int DISCONNECT=13;
    
    public static final int ERROR_NO_ERROR=0;
    public static final int ERROR_ERROR=1;
}