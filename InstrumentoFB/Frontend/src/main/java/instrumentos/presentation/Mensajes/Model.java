package instrumentos.presentation.Mensajes;
import instrumentos.Application;
import instrumentos.logic.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    List<Message> list;
    int mode;
    int props;

    public Model() {
    }
    public void init(){
        props=0;
        List<Message> list = new ArrayList<Message>();
        this.setList(list);
        mode = Application.MODE_CREATE;
    }

    public void setList(List<Message> list){
        this.list = list;
        props+=LIST;
    }

    public List<Message> getList() {
        return list;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(props);
        props=0;
    }


    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    public static int LIST=1;
}
