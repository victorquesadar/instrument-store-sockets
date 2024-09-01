package instrumentos.presentation.instrumentos;

import instrumentos.Application;
import instrumentos.logic.Instrumento;
import instrumentos.logic.Service;
import instrumentos.logic.TipoInstrumento;

import java.util.List;

public class Controller{
    View view;
    Model model;

    public Controller(View view, Model model) {
        try {
            model.init(Service.instance().search(new TipoInstrumento()));
        } catch (Exception e) {
        }
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        try {
            model.setList(Service.instance().search(model.getFilter()));
        } catch (Exception e) {
        }
        model.commit();
    }

    public void search(Instrumento filter) throws  Exception{
        model.setFilter(filter);
        List<Instrumento> rows = Service.instance().search(model.getFilter());
        if (rows.isEmpty()){
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        model.setMode(Application.MODE_CREATE);
        model.commit();
    }

    public void save(Instrumento e) throws  Exception{
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Instrumento());
        search(model.getFilter());
    }

    public void edit(int row){
        Instrumento e = model.getList().get(row);
        try {
            model.setCurrent(Service.instance().read(e));
            model.setMode(Application.MODE_EDIT);
            model.commit();
        } catch (Exception ex) {}
        try {
            Application.CalibracionesController.setIstrumento(model.getCurrent());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        model.setFilter(new Instrumento());
        search(model.getFilter());
}

    public void clear() {
        model.setCurrent(new Instrumento());
        model.setMode(Application.MODE_CREATE);
        model.commit();
    }

    public void shown() {
        try {
            model.setListTypes(Service.instance().search(new TipoInstrumento()));
        } catch (Exception e) {
        }
        model.commit();
    }

}
