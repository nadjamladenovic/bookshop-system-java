/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Grad;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.RadnaSmena;
import operacija.gradovi.UcitajGradoveSO;
import operacija.knjige.PrikazKnjigeSO;
import operacija.kupci.DodajKupcaSO;
import operacija.kupci.ObrisiKupcaSO;
import operacija.kupci.PrikazKupcaSO;
import operacija.kupci.PromeniKupcaSO;
import operacija.login.LoginOperacija;
import operacija.prodavci.AzurirajProdavcaSO;
import operacija.prodavci.DodajProdavcaSO;
import operacija.prodavci.ObrisiProdavcaSO;
import operacija.prodavci.UcitajProdavceSO;
import operacija.racuni.UcitajRacuneSO;
import operacija.smene.UcitajSmeneSO;

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

    public List<Racun> prikaziRacune() throws Exception {
        UcitajRacuneSO operacija = new UcitajRacuneSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA Controller prikaziRacune" + operacija.getRacuni());
        return operacija.getRacuni();
    }

    public List<Kupac> prikaziKupce() throws Exception {
        PrikazKupcaSO operacija = new PrikazKupcaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER prikaziKupce: " + operacija.getKupci());
        return operacija.getKupci();
    }

    public List<Knjiga> prikaziKnjige() throws Exception {
        PrikazKnjigeSO operacija = new PrikazKnjigeSO();
        operacija.izvrsi(null, null);
        System.out.println("KASA CONTROLLER ucitajUsluge: " + operacija.getKnjige());
        return operacija.getKnjige();
    }

    public List<RadnaSmena> ucitajSmene() throws Exception {
        UcitajSmeneSO operacija = new UcitajSmeneSO();
        operacija.izvrsi(null, null);
        System.out.println("KASA CONTROLLER ucitajSmene: " + operacija.getSmene());
        return operacija.getSmene();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupcaSO operacija = new ObrisiKupcaSO();
        operacija.izvrsi(k, null);
        System.out.println("KLASA Controller obrisi kupca");
    }

    public List<Grad> ucitajGradove() throws Exception {
        UcitajGradoveSO operacija=new UcitajGradoveSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER ucitajGradove: " + operacija.getListaGradova());
        return operacija.getListaGradova();
    }

    public void dodajKupca(Kupac k2) throws Exception {
        DodajKupcaSO operacija=new DodajKupcaSO();
        operacija.izvrsi(k2, null);
         System.out.println("KLASA CONTROLLER dodajKupca: " + k2);
    }

    public void PromeniKupca(Kupac k3) throws Exception {
        PromeniKupcaSO operacija=new PromeniKupcaSO();
        operacija.izvrsi(k3, null);
        System.out.println("KLASA CONTROLLER promeniKupca: " + k3);
    }
}
