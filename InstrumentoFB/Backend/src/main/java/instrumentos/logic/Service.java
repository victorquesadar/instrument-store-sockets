/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;

import instrumentos.data.CalibracionesDao;
import instrumentos.data.InstrumentoDao;
import instrumentos.data.MedicionesDao;
import instrumentos.data.TipoInstrumentoDao;

import java.util.List;

public class Service implements IService{
    private static Service theInstance;
    public static Service instance(){
        if (theInstance==null){ 
            theInstance=new Service();
        }
        return theInstance;
    }
    
    private TipoInstrumentoDao tipoInstrumentoDao;

    private InstrumentoDao instrumentoDao;

    private CalibracionesDao calibracionDao;
    private MedicionesDao medicionDao;

    public Service() {
        try{
            tipoInstrumentoDao = new TipoInstrumentoDao();
            instrumentoDao = new InstrumentoDao();
            calibracionDao = new CalibracionesDao();
            medicionDao = new MedicionesDao();
        }
        catch(Exception e){
        }
    }

    // ------------ TIPOS DE INTRUMENTO -------------
    public void create(TipoInstrumento e)throws Exception{
        tipoInstrumentoDao.create(e);
    }

    public TipoInstrumento read(TipoInstrumento e) throws Exception{
        return tipoInstrumentoDao.read(e.getCodigo());
    }  

    public void update(TipoInstrumento e)throws Exception{
        tipoInstrumentoDao.update(e);
    }

    public void delete(TipoInstrumento e)throws Exception{
        tipoInstrumentoDao.delete(e);
    }

    public List<TipoInstrumento> search(TipoInstrumento e) throws Exception {
        return tipoInstrumentoDao.search(e);
    }

    // ------------ INSTRUMENTOS -------------
    public void create(Instrumento e) throws Exception{
        instrumentoDao.create(e);
    }
    
    public Instrumento read(Instrumento e) throws Exception{
        return instrumentoDao.read(e.getSerie());
    }

    public void update(Instrumento e)throws Exception{
        instrumentoDao.update(e);
    }

    public void delete(Instrumento e)throws Exception{
        instrumentoDao.delete(e);
    }

    public List<Instrumento> search(Instrumento v) throws Exception {
        return instrumentoDao.search(v);
    }


    // ------------ Calibraciones -------------
    public void create(Calibracion e) throws Exception{
        calibracionDao.create(e);
        medicionDao.crearMediciones(e);
    }

    public Calibracion read(Calibracion e) throws Exception{
        return calibracionDao.read(e.getNumero());
    }

    public void update(Calibracion e)throws Exception{
        calibracionDao.update(e);
    }

    public void delete(Calibracion e)throws Exception{
        if(e.getMediciones() > 0){
            medicionDao.delete(e);
        }
        calibracionDao.delete(e);
    }

    public List<Calibracion> search(Calibracion v) throws Exception {
        return calibracionDao.search(v);
    }
    // ------------ Mediciones -------------
    public void create(Medida e) throws Exception{
        medicionDao.create(e);
    }

    public Medida read(Medida e) throws Exception{
        return medicionDao.read(e.getMedida());
    }

    public void update(Medida e)throws Exception{
        medicionDao.update(e);
    }

    public List<Medida> search(Medida v) throws Exception {
        return medicionDao.search(v);
    }




}
