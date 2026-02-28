/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import model.ProdavacRS;
import model.RadnaSmena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class UbaciSmenuSo extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof ProdavacRS)) {
            throw new Exception("Sistem ne moze da kreira prodavac smenu");
            
        }
         
         ProdavacRS prs=(ProdavacRS) param;
         if(prs.getProdavacID().getImePrezime()==null || prs.getProdavacID().getImePrezime().isEmpty())
             throw new Exception("Sistem ne moze da kreira prodavac smenu");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       broker.add((ProdavacRS)param);
    }
    
}
