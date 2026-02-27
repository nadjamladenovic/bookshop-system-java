/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import java.util.List;
import model.RadnaSmena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class UcitajSmeneSO extends ApstraktnaGenerickaOperacija {
    List<RadnaSmena>smene;

    public List<RadnaSmena> getSmene() {
        return smene;
    }

    public void setSmene(List<RadnaSmena> smene) {
        this.smene = smene;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        smene=broker.getAll(new RadnaSmena(), "");
    }
}
