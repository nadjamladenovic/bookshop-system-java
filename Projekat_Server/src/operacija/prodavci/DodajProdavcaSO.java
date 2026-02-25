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
public class DodajProdavcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Sistem nije mogao da doda prodavca");
        }
        
        Prodavac p= (Prodavac)param;
        if(p.getImePrezime()==null || p.getEmail().isEmpty() || p.getLozinka().length()<5 || p.getKorisnickoIme().isEmpty()){
             throw new Exception("Sistem nije mogao da doda prodavca, nisu ispunjeni uslovi");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
         // --- dalje u controller da se operacija izvrsu
        broker.add((Prodavac)param);
    }
    
}
