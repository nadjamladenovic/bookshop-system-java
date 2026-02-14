/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Nadja
 */
public class Zahtev implements Serializable {
    private Object operacija;
    private Object parametar;

    public Zahtev() {
    }

    public Zahtev(Object operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    public Object getOperacija() {
        return operacija;
    }

    public void setOperacija(Object operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
}
