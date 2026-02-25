/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Prodavac;
import operacija.login.LoginOperacija;
import operacija.prodavci.AzurirajProdavcaSO;
import operacija.prodavci.DodajProdavcaSO;
import operacija.prodavci.ObrisiProdavcaSO;
import operacija.prodavci.UcitajProdavceSO;

/**
 *
 * @author Nadja
 */
public class Controller {

    private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Prodavac login(Prodavac p) throws Exception {
        //da proveri dalje u bazi  
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(p, null);
        System.out.println("KLASA CONTROLLER login: " + operacija.getProdavac());

        return operacija.getProdavac(); // vraca ga dalje u OKZ
    }

    public List<Prodavac> prikaziProdavce() throws Exception {
        UcitajProdavceSO operacija = new UcitajProdavceSO();
        operacija.izvrsi(null, null);

        System.out.println("KLASA KONTROLER ucitajProdavceSO: " + operacija.getProdavci());
        return operacija.getProdavci();
    }

    public void obrisiProdavca(Prodavac prodavac) throws Exception {
        ObrisiProdavcaSO operacija = new ObrisiProdavcaSO();
        operacija.izvrsi(prodavac, null);
    }

    public void dodajProdavca(Prodavac prodavac) throws Exception {
        DodajProdavcaSO operacija = new DodajProdavcaSO();
        System.out.println("DEBUG: Pozivam operacija.izvrsi za prodavca");
        operacija.izvrsi(prodavac, null); //--- ova metoda se izvrsava u okz
    }

    public void azurirajProdavca(Prodavac prodavacA) throws Exception {
        AzurirajProdavcaSO operacija = new AzurirajProdavcaSO();
        operacija.izvrsi(prodavacA, null);
    }
}
