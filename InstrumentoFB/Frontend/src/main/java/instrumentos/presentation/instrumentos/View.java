package instrumentos.presentation.instrumentos;

import instrumentos.Application;
import instrumentos.logic.Instrumento;
import instrumentos.logic.TipoInstrumento;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JTextField searchDescripcion;
    private JButton search;
    private JButton save;
    private JTable list;
    private JButton delete;
    private JLabel searchDescripcionLbl;
    private JButton report;
    private JTextField serie;
    private JTextField descripcion;
    private JLabel serieLbl;
    private JLabel descripcionLbl;
    private JLabel tipoLbl;
    private JButton clear;
    private JComboBox tipo;
    private JLabel minimoLbl;
    private JTextField minimo;
    private JLabel maximoLbl;
    private JTextField maximo;
    private JTextField tolerancia;
    private JLabel toleranciaLbl;

    public View() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Instrumento filter=new Instrumento();
                    filter.setDescripcion(searchDescripcion.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Instrumento n = take();
                    try {
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel, "INTRUMENTO REGISTRADO", "", JOptionPane.INFORMATION_MESSAGE);
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
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                controller.shown();
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
        searchDescripcion.setText(model.getFilter().getDescripcion());
        if ((props & Model.LIST) == Model.LIST) {
            int[] cols = {TableModel.SERIE, TableModel.DESCRIPCION, TableModel.MINIMO, TableModel.MAXIMO, TableModel.TOLERANCIA};
            list.setModel(new TableModel(cols, model.getList()));
            list.setRowHeight(30);
            TableColumnModel columnModel = list.getColumnModel();
            columnModel.getColumn(1).setPreferredWidth(300);
        }
        if ((props & Model.TYPES) == Model.TYPES) {
            tipo.setModel( new DefaultComboBoxModel(model.getListTypes().toArray(new TipoInstrumento[0])));
            if(model.getMode()==Application.MODE_EDIT){
                tipo.setSelectedItem(model.getCurrent().getTipo());
            }
        }
        if ((props & Model.CURRENT) == Model.CURRENT) {
            serie.setText(model.getCurrent().getSerie());
            descripcion.setText(model.getCurrent().getDescripcion());
            minimo.setText(""+model.getCurrent().getMinimo());
            maximo.setText(""+model.getCurrent().getMaximo());
            tolerancia.setText(""+model.getCurrent().getTolerancia());
            tipo.setSelectedItem(model.getCurrent().getTipo());

            serieLbl.setBorder(null);
            serieLbl.setToolTipText(null);
            descripcionLbl.setBorder(null);
            descripcionLbl.setToolTipText(null);
            minimoLbl.setBorder(null);
            minimoLbl.setToolTipText(null);
            maximoLbl.setBorder(null);
            maximoLbl.setToolTipText(null);
            toleranciaLbl.setBorder(null);
            toleranciaLbl.setToolTipText(null);
        }

        if (model.getMode()==Application.MODE_EDIT){
            serie.setEnabled(false);
            delete.setEnabled(true);
        }
        else {
            serie.setEnabled(true);
            delete.setEnabled(false);
        }

        this.panel.revalidate();
    }

    public Instrumento take() {
        Instrumento e = new Instrumento();
        e.setSerie(serie.getText());
        e.setDescripcion(descripcion.getText());
        e.setMinimo(Integer.parseInt(minimo.getText()));
        e.setMaximo(Integer.parseInt(maximo.getText()));
        e.setTolerancia(Integer.parseInt(tolerancia.getText()));
        e.setTipo((TipoInstrumento) tipo.getSelectedItem());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (serie.getText().isEmpty()) {
            valid = false;
            serieLbl.setBorder(Application.BORDER_ERROR);
            serieLbl.setToolTipText("Codigo requerido");
        } else {
            serieLbl.setBorder(null);
            serieLbl.setToolTipText(null);
        }

        if (descripcion.getText().isEmpty()) {
            valid = false;
            descripcionLbl.setBorder(Application.BORDER_ERROR);
            descripcionLbl.setToolTipText("Nombre requerido");
        } else {
            descripcionLbl.setBorder(null);
            descripcionLbl.setToolTipText(null);
        }

        try{
            Integer.parseInt(minimo.getText());
            minimoLbl.setBorder(null);
            minimoLbl.setToolTipText(null);
        }
        catch (Exception ex){
            valid = false;
            minimoLbl.setBorder(Application.BORDER_ERROR);
            minimoLbl.setToolTipText("Unidad requerida");
        }

        try{
            Integer.parseInt(maximo.getText());
            maximoLbl.setBorder(null);
            maximoLbl.setToolTipText(null);
        }
        catch (Exception ex){
            valid = false;
            maximoLbl.setBorder(Application.BORDER_ERROR);
            maximoLbl.setToolTipText("Unidad requerida");
        }

        try{
            Integer.parseInt(tolerancia.getText());
            toleranciaLbl.setBorder(null);
            toleranciaLbl.setToolTipText(null);
        }
        catch (Exception ex){
            valid = false;
            toleranciaLbl.setBorder(Application.BORDER_ERROR);
            toleranciaLbl.setToolTipText("Unidad requerida");
        }

        return valid;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
