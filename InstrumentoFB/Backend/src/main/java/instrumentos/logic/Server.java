package instrumentos.logic;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    ServerSocket srv;
    List<Worker> workers;

    public Server() {
        try {
            srv = new ServerSocket(Protocol.PORT);
            workers = Collections.synchronizedList(new ArrayList<Worker>());
            System.out.println("Servidor iniciado...");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        String sid;
        IService service = new Service();
        boolean continuar = true;
        ObjectSocket os = null;
        Socket skt = null;
        while (continuar) {
            try {
                skt = srv.accept();
                os = new ObjectSocket(skt);
                System.out.println("Conexion Establecida...");
                int type = os.in.readInt();
                switch (type){
                    case Protocol.SYNC:
                        sid=skt.getRemoteSocketAddress().toString();
                        os.sid = sid;
                        System.out.println("SYNCH: "+os.sid);
                        Worker worker = new Worker(this, os, Service.instance());
                        workers.add(worker);
                        System.out.println("Quedan: "+workers.size());
                        worker.start();
                        os.out.writeObject(os.sid);
                        break;
                    case Protocol.ASYNC:
                        sid=(String) os.in.readObject();
                        os.sid = sid;
                        System.out.println("ASYNCH: "+ os.sid);
                        join(os);
                        break;
                }
                os.out.flush();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }





    public void remove(Worker w) {
        workers.remove(w);
        System.out.println("Quedan: " + workers.size());
    }
    public void deliver (Message m){
        for(Worker w:workers){
            w.deliver(m);
        }
    }
    public void join(ObjectSocket a){
        for(Worker w:workers){
            if(w.os.sid.equals(a.sid)){
                w.as=a;
                break;
            }
        }
    }

}