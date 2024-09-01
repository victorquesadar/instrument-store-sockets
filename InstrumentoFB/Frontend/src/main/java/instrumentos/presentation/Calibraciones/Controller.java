package instrumentos.presentation.Calibraciones;

import instrumentos.Application;
import instrumentos.logic.Calibracion;
import instrumentos.logic.Instrumento;
import instrumentos.logic.Medida;
import instrumentos.logic.Service;

import java.util.List;

public class Controller {
    ViewCalibraciones view;
    Model model;

    public Controller(ViewCalibraciones view, Model model) {
        try {
            model.init();
        } catch (Exception e) {

        }
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        try {
            model.setList(Service.instance().search(model.getFilter()));
            Medida e = new Medida();
            e.setCalibracion(new Calibracion());
            model.setListMedicione(Service.instance().search(e));

        } catch (Exception e) {
            System.out.println("Hola");
        }
        model.commit();
    }

    public void search(Calibracion filter) throws  Exception{
        filter.setInstrumento(model.getInstrumento());
        model.setFilter(filter);
        List<Calibracion> rows = Service.instance().search(model.getFilter());
        if (rows.isEmpty()){
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        model.setModo(Application.MODE_CREATE);
        model.commit();
    }

    public void save(Calibracion e) throws  Exception{
        switch (model.getModo()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);

                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }


        model.setFilter(new Calibracion());
        search(model.getFilter());
    }

    public void edit(int row){
        Calibracion e = model.getList().get(row);
        try {
            Medida m = new Medida();
            m.setCalibracion(e);
            m.setMedida("");
            model.setListMedicione(Service.instance().search(m));
            model.setModo(Application.MODE_EDIT);
            model.setCurrent(e);
            model.commit();
        } catch (Exception ex) {
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        model.setFilter(new Calibracion());
        search(model.getFilter());
        //List<Medida> n = new ArrayList<>(); Aqui falta para el delete
        //model.setListMedicione(n);
    }

    public void clear() {
        model.setCurrent(new Calibracion());
        model.setModo(Application.MODE_CREATE);
        model.commit();
    }

    public void setIstrumento(Instrumento a)  {
        Calibracion c=new Calibracion();
        c.setInstrumento(a);
        try {
            model.setList(Service.instance().search(c));
        } catch (Exception e) {
            System.out.printf("no se seteo");
        }
        model.setInstrumento(a);
        model.commit();
    }

   // public void shown() {
    //    try {
    //        model.setListTypes(Service.instance().search(new TipoInstrumento()));
     //   } catch (Exception e) {
     //   }
     //   model.commit();
  //  }
}
