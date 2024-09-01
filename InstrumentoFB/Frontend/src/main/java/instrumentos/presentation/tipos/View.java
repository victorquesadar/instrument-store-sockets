package instrumentos.presentation.tipos;

import instrumentos.Application;
import instrumentos.logic.TipoInstrumento;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JTextField searchNombre;
    private JButton search;
    private JButton save;
    private JTable list;
    private JButton delete;
    private JLabel searchNombreLbl;
    private JButton report;
    private JTextField codigo;
    private JTextField nombre;
    private JTextField unidad;
    private JLabel codigoLbl;
    private JLabel nombreLbl;
    private JLabel unidadLbl;
    private JButton clear;

    public View() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TipoInstrumento filter= new TipoInstrumento();
                    filter.setNombre(searchNombre.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    TipoInstrumento n = take();
                    try {
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel, "TIPO DE INTRUMENTO REGISTRADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                controller.edit(row);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.delete();
                    JOptionPane.showMessageDialog(panel, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
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
        int props = (int) properties;
        searchNombre.setText(model.getFilter().getNombre());
        if ((props & Model.LIST) == Model.LIST) {
            int[] cols = {TableModel.CODIGO, TableModel.NOMBRE, TableModel.UNIDAD};
            list.setModel(new TableModel(cols, model.getList()));
            list.setRowHeight(30);
            TableColumnModel columnModel = list.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(200);
        }
        if ((props & Model.CURRENT) == Model.CURRENT) {
            codigo.setText(model.getCurrent().getCodigo());
            nombre.setText(model.getCurrent().getNombre());
            unidad.setText(model.getCurrent().getUnidad());

            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
            unidadLbl.setBorder(null);
            unidadLbl.setToolTipText(null);
        }
        if (model.getMode()==Application.MODE_EDIT){
            codigo.setEnabled(false);
            delete.setEnabled(true);
        }
        else {
            codigo.setEnabled(true);
            delete.setEnabled(false);
        }

        this.panel.revalidate();
    }

    public TipoInstrumento take() {
        TipoInstrumento e = new TipoInstrumento();
        e.setCodigo(codigo.getText());
        e.setNombre(nombre.getText());
        e.setUnidad(unidad.getText());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigo.getText().length() == 0) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (nombre.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        if (unidad.getText().length() == 0) {
            valid = false;
            unidadLbl.setBorder(Application.BORDER_ERROR);
            unidadLbl.setToolTipText("Unidad requerida");
        } else {
            unidadLbl.setBorder(null);
            unidadLbl.setToolTipText(null);
        }
        return valid;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
