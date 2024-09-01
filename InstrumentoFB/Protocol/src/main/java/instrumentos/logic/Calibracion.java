/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calibracion implements Serializable {
    String numero;
    Instrumento instrumento;
    String fecha;
    int mediciones;
    List<Medida> medidas;

    public Calibracion() {
        this("",new Instrumento(), "",0);
    }    

    public Calibracion(String numero, Instrumento instrumento, String fecha, int mediciones) {
        this.numero=numero;
        this.instrumento = instrumento;
        this.fecha = fecha;
        this.mediciones = mediciones;
        this.medidas=new ArrayList<Medida>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMediciones() {
        return mediciones;
    }

    public void setMediciones(int mediciones) {
        this.mediciones = mediciones;
    }

    public List<Medida> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<Medida> medidas) {
        this.medidas = medidas;
    }

    
}
