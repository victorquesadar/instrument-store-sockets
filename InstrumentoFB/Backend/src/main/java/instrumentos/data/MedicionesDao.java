package instrumentos.data;

import instrumentos.logic.Calibracion;
import instrumentos.logic.Medida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicionesDao {
    Database db;
    public MedicionesDao() {
        db = Database.instance();
    }
    public void create(Medida e) throws Exception {
        String sql = "insert into " +
                "Mediciones " +
                "(calibracion , referencia,lectura) " +
                "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCalibracion().getNumero());
        stm.setInt(2, e.getReferencia());
        stm.setInt(3, e.getLectura());
        ResultSet keys=db.executeUpdateWithKeys(stm);
        keys.next();
        e.setMedida(""+keys.getInt(1));
    }

    public Medida read(String serie) throws Exception {
        String sql = "select " +
                "* " +
                "from " +
                "Mediciones m inner join Calibraciones c on m.calibracion = c.numero " +
                "where m.medida=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, serie);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "m");
        } else {
            throw new Exception("MEDICION NO EXISTE");
        }
    }

    public List<Medida> search(Medida e) throws Exception {
        List<Medida> resultado = new ArrayList<Medida>();
        String sql = "select * " +
                "from " +
                "Mediciones m inner join Calibraciones c on m.calibracion = c.numero "  +
                "where m.calibracion = ? and m.medida like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCalibracion().getNumero());
        stm.setString(2, "%" + e.getMedida() + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "m"));
        }
        return resultado;
    }

    public void update(Medida e) throws Exception {
        String sql = "update " +
                "Mediciones " +
                "set calibracion=?, medida=?, referencia=?, lectura=? " +
                "where medida=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCalibracion().getNumero());
        stm.setString(2, e.getMedida());
        stm.setInt(3, e.getReferencia());
        stm.setInt(4, e.getLectura());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("MEDICION NO EXISTE");
        }

    }

    public void delete(Calibracion e) throws Exception {
        String sql = "delete " +
                "from Mediciones " +
                "where calibracion=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNumero());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("MEDICION NO EXISTE");
        }
    }


    public Medida from(ResultSet rs, String alias) throws Exception {
        Medida e = new Medida();
        CalibracionesDao ed=new CalibracionesDao();

        e.setCalibracion(ed.from(rs,"c"));
        e.setMedida(rs.getString(alias + ".medida"));
        e.setReferencia(rs.getInt(alias + ".referencia"));
        e.setLectura(rs.getInt(alias + ".lectura"));
        return e;
    }

    public void crearMediciones(Calibracion c) throws Exception {
        int max = c.getInstrumento().getMaximo();
        int min =c.getInstrumento().getMinimo();
        int mediciones =c.getMediciones();
        int n = (max-min)/mediciones;
        if(c.getMediciones() != 0 ) {
            Medida a = new Medida("", c, min, 0);
            try {
                create(a);
            } catch (Exception e) {
                new RuntimeException(e);
            }
            for (int i = 1; i < mediciones; i++) {
                a = new Medida("", c, n, 0);
                create(a);
                n += n;
            }
        }
    }
}
