/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavci;

import java.util.List;
import model.Prodavac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class UcitajProdavceSO extends ApstraktnaGenerickaOperacija {

    private List<Prodavac> prodavci;

    public List<Prodavac> getProdavci() {
        return prodavci;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
         prodavci=broker.getAll(new Prodavac(), "");
    }

}
