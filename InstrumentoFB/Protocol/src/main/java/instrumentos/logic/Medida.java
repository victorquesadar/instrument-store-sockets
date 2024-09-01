/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;

import java.io.Serializable;

public class Medida implements Serializable {
    String medida;
    Calibracion calibracion;
    int referencia;
    int lectura;

    public Medida() {
    }
   
    public Medida(String medida, Calibracion calibracion, int referencia, int lectura) {
        this.medida = medida;
        this.calibracion = calibracion;
        this.referencia = referencia;
        this.lectura = lectura;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Calibracion getCalibracion() {
        return calibracion;
    }

    public void setCalibracion(Calibracion calibracion) {
        this.calibracion = calibracion;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getLectura() {
        return lectura;
    }

    public void setLectura(int lectura) {
        this.lectura = lectura;
    }
    
    
}
