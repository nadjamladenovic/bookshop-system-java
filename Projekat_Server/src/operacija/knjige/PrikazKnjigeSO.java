/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import java.util.List;
import model.Knjiga;

import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class PrikazKnjigeSO extends ApstraktnaGenerickaOperacija {

    List<Knjiga> knjige;

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        knjige=broker.getAll(new Knjiga(), "");
    }

}
