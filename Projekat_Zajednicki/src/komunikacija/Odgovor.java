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
public class Odgovor implements Serializable {
    private Object odgvor;

    public Odgovor() {
    }

    public Odgovor(Object odgvor) {
        this.odgvor = odgvor;
    }

    public Object getOdgvor() {
        return odgvor;
    }

    public void setOdgvor(Object odgvor) {
        this.odgvor = odgvor;
    }
    
}
