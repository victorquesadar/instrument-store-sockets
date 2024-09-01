package instrumentos.presentation.tipos;

import instrumentos.Application;
import instrumentos.logic.Service;
import instrumentos.logic.TipoInstrumento;

import java.util.List;

public class Controller{
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init();
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

    public void search(TipoInstrumento filter) throws  Exception{
        model.setFilter(filter);
        List<TipoInstrumento> rows = Service.instance().search(model.getFilter());
        model.setList(rows);
        model.setMode(Application.MODE_CREATE);
        model.commit();
    }

    public void save(TipoInstrumento e) throws  Exception{
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                 break;
        }
        model.setFilter(new TipoInstrumento());
        search(model.getFilter());
    }


    public void edit(int row){
        TipoInstrumento e = model.getList().get(row);
        try {
            model.setCurrent(Service.instance().read(e));
            model.setMode(Application.MODE_EDIT);
            model.commit();
        } catch (Exception ex) {}
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        model.setFilter(new TipoInstrumento());
        search(model.getFilter());
    }

    public void clear() {
        model.setCurrent(new TipoInstrumento());
        model.setMode(Application.MODE_CREATE);
        model.commit();
    }
}
