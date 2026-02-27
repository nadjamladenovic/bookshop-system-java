/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.List;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class PrikazKupcaSO extends ApstraktnaGenerickaOperacija {

    List<Kupac> kupci;

    public List<Kupac> getKupci() {
        return kupci;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        //SELECT * FROM kupac JOIN grad g ON g.gradID=kupac.gradID
        String uslov =" JOIN grad g ON g.gradID=kupac.gradID";
           kupci=broker.getAll(new Kupac(), uslov);    // ovde se vraca oono sto mi vrati dbroker
    }

}
