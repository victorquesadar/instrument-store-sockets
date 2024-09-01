package instrumentos.logic;
import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Service implements IService,IListener{  // PROXY
    ObjectSocket as = null;
    ITarget target;
    private static Service theInstance;
    public static Service instance(){
        if (theInstance==null){ 
            theInstance=new Service();
        }
        return theInstance;
    }
    public static IListener instanceListener(){
        if (theInstance==null){
            theInstance=new Service();
        }
        return theInstance;
    }

    ObjectSocket os = null;

    public Service() {
        try{
            this.connect();
        }
        catch(Exception e){
            System.exit(-1);
        }
    }
    private void connect() throws Exception{
        os = new ObjectSocket(new Socket(Protocol.SERVER,Protocol.PORT));
        os.out.writeInt(Protocol.SYNC);
        os.out.flush();
        os.sid=(String) os.in.readObject();
    }
    private void disconnect() throws Exception{
        os.out.writeInt(Protocol.DISCONNECT);
        os.out.flush();
        os.skt.shutdownOutput();
        os.skt.close();
    }


    @Override
    public void create(TipoInstrumento e) throws Exception {
        os.out.writeInt(Protocol.TipoInstrumento_create);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("TIPO INSTRUMENTO DUPLICADO");
    }

    @Override
    public TipoInstrumento read(TipoInstrumento e) throws Exception {
        os.out.writeInt(Protocol.TipoInstrumento_read);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return(TipoInstrumento) os.in.readObject();
        else throw new Exception("TIPO INSTRUMENTO DUPLICADO");
    }

    @Override
    public void update(TipoInstrumento e) throws Exception {
        os.out.writeInt(Protocol.TipoInstrumento_update);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("TIPO INSTRUMENTO DUPLICADO");
    }

    @Override
    public void delete(TipoInstrumento e) throws Exception {
        os.out.writeInt(Protocol.TipoInstrumento_delete);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("TIPO INSTRUMENTO DUPLICADO");
    }

    @Override
    public List<TipoInstrumento> search(TipoInstrumento e) throws Exception {
        os.out.writeInt(Protocol.TipoInstrumento_search);
        os.out.writeObject(e);
        os.out.flush();
        List<TipoInstrumento> a;
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return (List<TipoInstrumento>)os.in.readObject();


        else throw new Exception("TIPO INSTRUMENTO DUPLICADO");
    }

    @Override
    public void create(Instrumento e) throws Exception {
        os.out.writeInt(Protocol.Intrumento_create);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("INSTRUMENTO DUPLICADO");
    }

    @Override
    public Instrumento read(Instrumento e) throws Exception {
        os.out.writeInt(Protocol.Intrumento_read);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return(Instrumento) os.in.readObject();
        else throw new Exception("INSTRUMENTO DUPLICADO");
    }

    @Override
    public void update(Instrumento e) throws Exception {
        os.out.writeInt(Protocol.Intrumento_update);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("INSTRUMENTO DUPLICADO");
    }

    @Override
    public void delete(Instrumento e) throws Exception {
        os.out.writeInt(Protocol.Intrumento_delete);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("INSTRUMENTO DUPLICADO");
    }

    @Override
    public List<Instrumento> search(Instrumento e) throws Exception {
        os.out.writeInt(Protocol.Intrumento_search);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return (List<Instrumento>)os.in.readObject();
        else throw new Exception("INSTRUMENTO DUPLICADO");
    }

    @Override
    public void create(Calibracion e) throws Exception {
        os.out.writeInt(Protocol.Calibracion_create);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("CALIBRACION DUPLICADO");
    }

    @Override
    public Calibracion read(Calibracion e) throws Exception {
        os.out.writeInt(Protocol.Calibracion_read);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return(Calibracion) os.in.readObject();
        else throw new Exception("CALIBRACION DUPLICADO");
    }

    @Override
    public void update(Calibracion e) throws Exception {
        os.out.writeInt(Protocol.Calibracion_update);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("CALIBRACION DUPLICADO");
    }

    @Override
    public void delete(Calibracion e) throws Exception {
        os.out.writeInt(Protocol.Calibracion_delete);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("CALIBRACION DUPLICADO");
    }

    @Override
    public List<Calibracion> search(Calibracion e) throws Exception {
        os.out.writeInt(Protocol.Calibracion_search);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return (List<Calibracion>)os.in.readObject();
        else throw new Exception("CALIBRACION DUPLICADO");
    }

    @Override
    public void create(Medida e) throws Exception {
        os.out.writeInt(Protocol.Medida_create);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("MEDICION DUPLICADO");
    }

    @Override
    public Medida read(Medida e) throws Exception {
        os.out.writeInt(Protocol.Medida_read);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return(Medida) os.in.readObject();
        else throw new Exception("MEDICION DUPLICADO");
    }

    @Override
    public void update(Medida e) throws Exception {
        os.out.writeInt(Protocol.Medida_update);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR){}
        else throw new Exception("MEDICION DUPLICADO");
    }

    @Override
    public List<Medida> search(Medida e) throws Exception {
        os.out.writeInt(Protocol.Medida_search);
        os.out.writeObject(e);
        os.out.flush();
        if (os.in.readInt()==Protocol.ERROR_NO_ERROR)return (List<Medida>)os.in.readObject();
        else throw new Exception("MEDIDA DUPLICADO");
    }

    @Override
    public void setTarget(ITarget t) {
        target = t;
    }

    @Override
    public void start() {
        this.startListening();
    }

    @Override
    public void stop() {
        this.stopListening();
        try {
            this.disconnect();
        } catch (Exception e){}

    }

    // LISTENING FUNCTIONS
    boolean continuar = true;
    public void startListening(){
        try {
            as = new ObjectSocket(new Socket(Protocol.SERVER,Protocol.PORT));
            as.sid = os.sid;
            as.out.writeInt(Protocol.ASYNC);
            as.out.writeObject(as.sid);
            as.out.flush();
        }catch (IOException e){
            System.out.println("ERROR");
        }
        Thread t = new Thread(new Runnable(){
            public void run(){
                listen();
            }
        });
        continuar = true;
        t.start();
    }

    public void stopListening(){
        continuar=false;
    }

    public void listen(){
        int method;
        while (continuar) {
            try {
                method = as.in.readInt();
                switch(method){
                    case Protocol.DELIVER:
                        try {
                            Message message=(Message)as.in.readObject();
                            deliver(message);
                        } catch (ClassNotFoundException ex) {}
                        break;
                }
            } catch (IOException  ex) {
                continuar = false;
            }
        }
        try {
            as.skt.shutdownOutput();
            as.skt.close();
        }catch (IOException e){}
    }

    private void deliver( final Message message ){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                target.deliver(message);
            }
        }
        );
    }

}
