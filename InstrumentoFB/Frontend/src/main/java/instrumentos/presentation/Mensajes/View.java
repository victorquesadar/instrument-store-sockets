package instrumentos.presentation.Mensajes;
import instrumentos.presentation.Calibraciones.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    public JPanel panel1;
    private JTable mensajes;
    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public void update(Observable updatedModel, Object properties) {
        int props = (int) properties;
        mensajes.setBackground(Color.GREEN);
        int[] cols = {tableModel.TIPO, tableModel.ENTIDAD, tableModel.TEXTO};
        if ((props & Model.LIST) == Model.LIST) {
            mensajes.setModel(new tableModel(cols, model.getList()));
            mensajes.setRowHeight(30);
            TableColumnModel columnModel = mensajes.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(200);
        }
        mensajes.setModel(new tableModel(cols, model.getList()));
        if(model.getList().isEmpty()){

        }else{

        }
        this.panel1.revalidate();
    }
}
