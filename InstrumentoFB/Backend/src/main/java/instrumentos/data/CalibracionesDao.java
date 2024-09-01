package instrumentos.data;

import instrumentos.logic.Calibracion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CalibracionesDao {
    Database db;
    public CalibracionesDao() {
        db = Database.instance();
    }
    public void create(Calibracion e) throws Exception {
        String sql = "insert into " +
                "Calibraciones " +
                "(fecha, mediciones, instrumento) " +
                "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getFecha());
        stm.setInt(2, e.getMediciones());
        stm.setString(3, e.getInstrumento().getSerie());
        ResultSet keys=db.executeUpdateWithKeys(stm);
        keys.next();
        e.setNumero(""+keys.getInt(1));
    }

    public Calibracion read(String serie) throws Exception {
        String sql = "select " +
                "* " +
                "from " +
                "Calibraciones c inner join Instrumento i on c.instrumento = i.serie " +
                "where c.numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, serie);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "c");
        } else {
            throw new Exception("CALIBRACION NO EXISTE");
        }
    }

    public void update(Calibracion e) throws Exception {
        String sql = "update " +
                "Calibraciones " +
                "set numero=?, fecha=?, mediciones=?, instrumento=? " +
                "where numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNumero());
        stm.setString(2, e.getFecha());
        stm.setInt(3, e.getMediciones());
        stm.setString(4, e.getInstrumento().getSerie());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("CALIBRACION NO EXISTE");
        }

    }

    public void delete(Calibracion e) throws Exception {
        String sql = "delete " +
                "from Calibraciones " +
                "where numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNumero());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("CALIBRACION NO EXISTE");
        }
    }

    public List<Calibracion> search(Calibracion e) throws Exception {
        List<Calibracion> resultado = new ArrayList<Calibracion>();
        String sql = "select * " +
                "from " +
                "Calibraciones c inner join Instrumento i on c.instrumento = i.serie "  +
                "where c.instrumento = ? and c.numero like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getInstrumento().getSerie());
        stm.setString(2, "%" + e.getNumero() + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "c"));
        }
        return resultado;
    }

    public Calibracion from(ResultSet rs, String alias) throws Exception {
        Calibracion e = new Calibracion();
        //InstrumentoDao ed = new InstrumentoDao();
        //e.setInstrumento(ed.from(rs,"i"));
        e.setFecha(rs.getString(alias + ".fecha"));
        e.setMediciones(rs.getInt(alias + ".mediciones"));
        e.setNumero(rs.getString(alias + ".numero"));
        return e;
    }
}
