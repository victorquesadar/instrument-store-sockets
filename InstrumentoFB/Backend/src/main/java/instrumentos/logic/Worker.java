package instrumentos.logic;



import java.io.IOException;
import java.util.List;

public class Worker {
    Server srv;
    ObjectSocket os;
    ObjectSocket as;
    IService service;


    public Worker(Server srv, ObjectSocket os, IService service) {
        this.srv=srv;
        this.os=os;

        this.service=service;
    }

    boolean continuar;    
    public void start(){
        try {
            System.out.println("Worker atendiendo peticiones...");
            Thread t = new Thread(new Runnable(){
                public void run(){
                    listen();
                }
            });
            continuar = true;
            t.start();
        } catch (Exception ex) {  
        }
    }
    
    public void stop(){
        continuar=false;
        System.out.println("Conexion cerrada...");
    }
    
    public void listen(){
        int method;
        while (continuar) {
            try {
                method = os.in.readInt();
                System.out.println("Operacion: "+method);
                switch(method){
                //case Protocol.LOGIN: done on accept
                    case Protocol.DISCONNECT:
                        try {
                            this.stop();
                            srv.remove(this);

                        } catch (Exception ex) {}

                        break;
                    case Protocol.TipoInstrumento_create:
                    try {
                        TipoInstrumento e= ((TipoInstrumento)os.in.readObject());
                        service.create(e);
                        os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        srv.deliver(new Message(Message.CREATE,"TI",e.getNombre()));
                    } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}

                    break;                 
                    case Protocol.TipoInstrumento_read:

                    try {
                        TipoInstrumento e=service.read((TipoInstrumento) os.in.readObject());
                        os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        os.out.writeObject(e);

                    } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                    break;
                    case Protocol.TipoInstrumento_update:

                        try {
                            TipoInstrumento e= ((TipoInstrumento)os.in.readObject());
                            service.update(e);

                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver(new Message(Message.UPDATE,"TI",e.getNombre()));

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;

                    case Protocol.TipoInstrumento_delete:

                        try {
                            TipoInstrumento e= ((TipoInstrumento)os.in.readObject());
                            service.delete(e);
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver(new Message(Message.DELETE,"TI",e.getNombre()));
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.TipoInstrumento_search:

                        try {
                            List<TipoInstrumento> a=service.search((TipoInstrumento)os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(a);

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                        //------------------------Instrumento-------------------------
                    case Protocol.Intrumento_create:
                        try {
                            Instrumento e= ((Instrumento)os.in.readObject());
                            service.create(e);
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver(new Message(Message.CREATE,"INS",e.descripcion));
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}

                        break;
                    case Protocol.Intrumento_read:

                        try {
                            Instrumento e=service.read((Instrumento) os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(e);

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;


                    case Protocol.Intrumento_update:
                        try {
                            Instrumento e= ((Instrumento)os.in.readObject());
                            service.update(e);
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver(new Message(Message.UPDATE,"INS",e.descripcion));


                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Intrumento_delete:
                        try {
                            Instrumento e= ((Instrumento)os.in.readObject());
                            service.delete(e);
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver(new Message(Message.DELETE,"INS",e.descripcion));
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Intrumento_search:
                        try {
                            List<Instrumento> a=service.search((Instrumento)os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(a);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    //-------------------Calibracion-------------------
                    case Protocol.Calibracion_create:
                        try {
                            Calibracion e= ((Calibracion)os.in.readObject());
                            service.create(e);
                            srv.deliver(new Message(Message.CREATE,"CALI",e.getNumero()));
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}

                        break;
                    case Protocol.Calibracion_read:
                        try {
                            Calibracion e=service.read((Calibracion) os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(e);

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Calibracion_update:
                        try {
                            Calibracion e= ((Calibracion)os.in.readObject());
                            service.update(e);
                            srv.deliver(new Message(Message.UPDATE,"CALI",e.getNumero()));
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Calibracion_delete:
                        try {
                            Calibracion e= ((Calibracion)os.in.readObject());
                            service.delete(e);
                            srv.deliver(new Message(Message.DELETE,"CALI",e.getNumero()));
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Calibracion_search:
                        try {

                            List<Calibracion> a=service.search((Calibracion)os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(a);

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    //-------------------Medicion-------------------
                    case Protocol.Medida_create:
                        try {
                            Medida e= ((Medida)os.in.readObject());
                            service.create(e);
                            srv.deliver(new Message(Message.CREATE,"MEDI",e.getMedida()));
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}

                        break;
                    case Protocol.Medida_read:
                        try {
                            Medida e=service.read((Medida) os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(e);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.Medida_update:
                        try {
                            Medida e= ((Medida)os.in.readObject());
                            service.update(e);
                            srv.deliver(new Message(Message.UPDATE,"MEDI",e.getMedida()));
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;

                    case Protocol.Medida_search:
                        try {
                            List<Medida> a=service.search((Medida)os.in.readObject());
                            os.out.writeInt(Protocol.ERROR_NO_ERROR);
                            os.out.writeObject(a);

                        } catch (Exception ex) {os.out.writeInt(Protocol.ERROR_ERROR);}
                        break;
                }
                os.out.flush();

            } catch (IOException  ex) {
                srv.remove(this);
                System.out.println(ex);
                continuar = false;
            }                        
        }
    }

    public void deliver(Message m){
        if(as!=null){
            try{
                as.out.writeInt(Protocol.DELIVER);
                as.out.writeObject(m);
                as.out.flush();
            }
            catch(Exception e){

            }
        }
    }
    

}
