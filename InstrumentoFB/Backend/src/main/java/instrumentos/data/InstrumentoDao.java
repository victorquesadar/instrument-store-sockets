package instrumentos.data;

import instrumentos.logic.Instrumento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InstrumentoDao {
    Database db;
    public InstrumentoDao() {
        db = Database.instance();
    }
    public void create(Instrumento e) throws Exception {
        String sql = "insert into " +
                "Instrumento " +
                "(tipoisntrumentos, serie, descripcion, minimo, maximo, tolerancia) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getTipo().getCodigo());
        stm.setString(2, e.getSerie());
        stm.setString(3, e.getDescripcion());
        stm.setInt(4, e.getMinimo());
        stm.setInt(5, e.getMaximo());
        stm.setInt(6, e.getTolerancia());

        db.executeUpdate(stm);
    }

    public Instrumento read(String serie) throws Exception {
        String sql = "select " +
                "* " +
                "from " +
                "Instrumento i inner join TipoInstrumento t on i.tipoisntrumentos= t.codigo " +
                "where i.serie=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, serie);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "i");
        } else {
            throw new Exception("INSTRUMENTO NO EXISTE");
        }
    }

    public void update(Instrumento e) throws Exception {
        String sql = "update " +
                "Instrumento " +
                "set tipoisntrumentos=?, serie=?, descripcion=?, minimo=?, maximo=?, tolerancia=? " +
                "where serie=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getTipo().getCodigo());
        stm.setString(2, e.getSerie());
        stm.setString(3, e.getDescripcion());
        stm.setInt(4, e.getMinimo());
        stm.setInt(5, e.getMaximo());
        stm.setInt(6, e.getTolerancia());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("INSTRUMENTO NO EXISTE");
        }

    }

    public void delete(Instrumento e) throws Exception {
        String sql = "delete " +
                "from Instrumento " +
                "where serie=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getSerie());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("INSTRUMENTO NO EXISTE");
        }
    }

    public List<Instrumento> search(Instrumento e) throws Exception {
        List<Instrumento> resultado = new ArrayList<Instrumento>();
        String sql = "select * " +
                "from " +
                "Instrumento i inner join TipoInstrumento t on i.tipoisntrumentos= t.codigo " +
                "where i.descripcion like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getDescripcion() + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "i"));
        }
        return resultado;
    }

    public Instrumento from(ResultSet rs, String alias) throws Exception {
        Instrumento e = new Instrumento();
        TipoInstrumentoDao ed = new TipoInstrumentoDao();
        e.setTipo(ed.from(rs,"t"));
        e.setSerie(rs.getString(alias + ".serie"));
        e.setDescripcion(rs.getString(alias + ".descripcion"));
        e.setMinimo(rs.getInt(alias + ".minimo"));
        e.setMaximo(rs.getInt(alias + ".maximo"));
        e.setTolerancia(rs.getInt(alias + ".tolerancia"));
        return e;
    }
}
