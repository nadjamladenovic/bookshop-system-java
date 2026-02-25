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
public class AzurirajProdavcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Sistem nije mogao da doda prodavca");
        }
        Prodavac p = (Prodavac) param;
        if (p.getImePrezime()== null || p.getImePrezime().isEmpty() ) {
            throw new Exception("Sistem nije mogao da doda prodavca, nisu ispunjeni uslovi");
        }
        if (p.getEmail()== null || p.getEmail().isEmpty() ) {
            throw new Exception("Sistem nije mogao da doda prodavca niste uneli email");
        }
        
         if (p.getKorisnickoIme()== null || p.getKorisnickoIme().isEmpty() ) {
            throw new Exception("Sistem nije mogao da doda prodavca niste uneli korisnicko ime");
        }
          if (p.getLozinka()== null || p.getLozinka().isEmpty() ) {
            throw new Exception("Sistem nije mogao da doda prodavca niste uneli sifru");
        }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Prodavac)param);
    }
    
}
