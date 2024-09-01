package instrumentos.presentation.Calibraciones;


import instrumentos.logic.Calibracion;
import instrumentos.logic.Instrumento;
import instrumentos.logic.Medida;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model  extends java.util.Observable{
    List<Calibracion> list;
    Calibracion current;
    Instrumento instrumento;
    List<Medida> listMedicione;
    Calibracion filter;

    int modo = 1; //1 = agregar    2 = modificar  3 = borrar

    int changedProps = NONE;

    public Model() {

    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public void commit(){
        setChanged();
        notifyObservers(changedProps);
        changedProps = 0;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;


    }

    public void init(){
        instrumento = new Instrumento();
        changedProps =0;
        setCurrent(new Calibracion());
        filter = new Calibracion();
        List<Calibracion> rows = new ArrayList<Calibracion>();
        setList(rows);
        modo = 1;
    }

    public Calibracion getFilter() {
        return filter;
    }

    public void setFilter(Calibracion filter) {
        this.filter = filter;
    }

    public List<Calibracion> getList() {
        return list;
    }

    public void setList(List<Calibracion> lisst){
        this.list =lisst;
        changedProps +=LIST;
    }

    public Calibracion getCurrent() {
        return current;
    }

    public void setCurrent(Calibracion current) {
        changedProps +=CURRENT;
        this.current = current;
    }

    public void setListMedicione(List<Medida> list){
        this.listMedicione = list;
        changedProps +=MEDICIONES;
    }

    public List<Medida> getListMedicione() {
        return listMedicione;
    }

    public static int NONE=0;
    public static int LIST=1;
    public static int CURRENT=2;
    public static int MEDICIONES=4;
}
