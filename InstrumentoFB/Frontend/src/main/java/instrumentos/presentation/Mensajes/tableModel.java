package instrumentos.presentation.Mensajes;
import instrumentos.logic.Message;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class tableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Message> rows;
    int[] cols;

    public tableModel(int[] cols, List<Message> rows){
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
        Message sucursal = rows.get(row);
        switch (cols[col]){
            case TIPO: return sucursal.getTipo();
            case ENTIDAD: return sucursal.getEntidad();
            case TEXTO: return sucursal.getTexto();
            default: return "";
        }
    }

    public Message getRowAt(int row) {
        return rows.get(row);
    }

    public static final int TIPO=0;
    public static final int ENTIDAD=1;
    public static final int TEXTO=2;

    String[] colNames = new String[6];
    private void initColNames(){
        colNames[TIPO]= "Tipo";
        colNames[ENTIDAD]= "Entidad";
        colNames[TEXTO]= "Texto";
    }
}
