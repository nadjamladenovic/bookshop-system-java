/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavci;

import model.Prodavac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class ObrisiProdavcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Sistem nije mogao da obrise prodavca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Prodavac)param);
    }
    
}
