/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;



import java.io.Serializable;
import java.util.List;

public interface IService  {

    // ------------ TIPOS DE INTRUMENTO -------------
    public void create(TipoInstrumento e)throws Exception;

    public TipoInstrumento read(TipoInstrumento e) throws Exception;

    public void update(TipoInstrumento e)throws Exception;

    public void delete(TipoInstrumento e)throws Exception;

    public List<TipoInstrumento> search(TipoInstrumento e) throws Exception ;

    // ------------ INSTRUMENTOS -------------
    public void create(Instrumento e) throws Exception;
    
    public Instrumento read(Instrumento e) throws Exception;

    public void update(Instrumento e)throws Exception;

    public void delete(Instrumento e)throws Exception;

    public List<Instrumento> search(Instrumento v) throws Exception ;


    // ------------ Calibraciones -------------
    public void create(Calibracion e) throws Exception;

    public Calibracion read(Calibracion e) throws Exception;

    public void update(Calibracion e)throws Exception;

    public void delete(Calibracion e)throws Exception;

    public List<Calibracion> search(Calibracion v) throws Exception ;
    // ------------ Mediciones -------------
    public void create(Medida e) throws Exception;

    public Medida read(Medida e) throws Exception;

    public void update(Medida e)throws Exception;

    public List<Medida> search(Medida v) throws Exception ;




}
