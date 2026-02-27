/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.gradovi;

import java.util.List;
import model.Grad;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class UcitajGradoveSO extends ApstraktnaGenerickaOperacija {
List<Grad> listaGradova;

    public List<Grad> getListaGradova() {
        return listaGradova;
    }

    public void setListaGradova(List<Grad> listaGradova) {
        this.listaGradova = listaGradova;
    }

  
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
// ovde treba da mi se vrati lista mesta iz baze
        listaGradova=broker.getAll(new Grad(), "");
    }
    
}
