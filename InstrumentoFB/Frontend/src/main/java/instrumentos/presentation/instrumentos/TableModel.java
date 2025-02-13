package instrumentos.presentation.instrumentos;

import instrumentos.logic.Instrumento;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Instrumento> rows;
    int[] cols;

    public TableModel(int[] cols, List<Instrumento> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Instrumento e = rows.get(row);
        switch (cols[col]){
            case SERIE: return e.getSerie();
            case DESCRIPCION: return e.getDescripcion();
            case MINIMO: return e.getMinimo();
            case MAXIMO: return e.getMaximo();
            case TOLERANCIA: return e.getTolerancia();
            default: return "";
        }
    }

    public Instrumento getRowAt(int row) {
        return rows.get(row);
    }

    public static final int SERIE=0;
    public static final int DESCRIPCION=1;
    public static final int MINIMO=2;
    public static final int MAXIMO=3;
    public static final int TOLERANCIA=4;

    String[] colNames = new String[6];
    private void initColNames(){
        colNames[SERIE]= "No. Serie";
        colNames[DESCRIPCION]= "Descripción";
        colNames[MINIMO]= "Mínimo";
        colNames[MAXIMO]= "Máximo";
        colNames[TOLERANCIA]= "Tolerancia";
    }

}
