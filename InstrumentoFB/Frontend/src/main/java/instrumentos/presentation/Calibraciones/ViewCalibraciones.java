package instrumentos.presentation.Calibraciones;

import instrumentos.logic.Calibracion;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ViewCalibraciones implements Observer {
    private JLabel tipoInstrumento;
    private JTextField Numero;
    private JTextField mediciones;
    private JTextField fecha;
    private JButton save;
    private JButton delete;
    private JButton clear;
    private JTextField searchNumero;
    private JButton search;
    private JTable table1;
    private JTable table2;
    private JButton report;
    private JScrollPane panelMedi;
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public ViewCalibraciones() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {{
                    try {
                        Calibracion filter = new Calibracion();
                        filter.setNumero(searchNumero.getText());
                        controller.search(filter);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "No se encontro ninguna coincidencia");
                    }
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                model.setModo(2);
                controller.edit(row);
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if ((!fecha.getText().isEmpty()) && (!mediciones.getText().isEmpty()) ) {
                        Calibracion calibracion1 = new Calibracion(Numero.getText(), model.getInstrumento(), fecha.getText(), Integer.parseInt(mediciones.getText()));
                        try {
                            controller.save(calibracion1);
                            Numero.setText("");
                            fecha.setText("");
                            mediciones.setText("");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel, "Calibracion ya existe");
                        }
                    }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calibracion Calibracion3 = new Calibracion(Numero.getText(), model.getInstrumento(), fecha.getText(), Integer.parseInt(mediciones.getText()));
                try {
                    controller.delete();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setModo(1);
                delete.setEnabled(false);
                searchNumero.setText("");
                fecha.setText("");
                mediciones.setText("");
                Numero.setText("");
            }
        });
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  controller.imprimir(model.getInstrumento());
                if(Desktop.isDesktopSupported()){
                    File myFile = new File("ListadeCalibraciones.pdf");
                    try {
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object properties) {
        int[] cools = {TableModel.NUMERO, TableModel.FECHA
                , TableModel.MEDICIONES};
        int changedProps = (int) properties;
        if ((changedProps & Model.LIST) == Model.LIST) {
            tipoInstrumento.setText(model.getInstrumento().tosstring());
            tipoInstrumento.setForeground(Color.red);
            table1.setModel(new TableModel(cools, model.getInstrumento().getCalibraciones()));
            table1.setRowHeight(30);
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(1).setPreferredWidth(200);
            //table1.setRowSelectionInterval(0,0);
        }
        if ((changedProps & Model.CURRENT) == Model.CURRENT) {
            Numero.setText(model.getCurrent().getNumero());
            mediciones.setText(String.valueOf(model.getCurrent().getMediciones()));
            fecha.setText(model.getCurrent().getFecha());

        }
        if ((changedProps & Model.MEDICIONES) == Model.MEDICIONES) {
            int[] cols = {TableModelMediciones.MEDIDA, TableModelMediciones.REFERENCIA
                    , TableModelMediciones.LECTURA};
            table2.setModel(new TableModelMediciones(cols, model.getListMedicione()));
            table2.setRowHeight(30);
            TableColumnModel columnModel = table2.getColumnModel();
            columnModel.getColumn(1).setPreferredWidth(200);
            //table2.setRowSelectionInterval(0,0);
        }
        Numero.setEnabled(false);
        //habilitar y desabilitar botones
        if(model.getModo() == 1){
            delete.setEnabled(false);
            panelMedi.setEnabled(false);
        }else if(model.getModo() == 2){
            Numero.setEnabled(false);
            delete.setEnabled(true);
            table2.setEnabled(true);
        }
        this.panel.revalidate();
        table1.setModel(new TableModel(cools, model.getList()));

        if(model.getInstrumento().getCalibraciones().isEmpty()){

        }else{
            table1.setRowSelectionInterval(0,0);
        }
    }

}
