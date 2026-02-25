/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;

import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {// prim i posilj rade preko istog soketa na portu 9000
        this.s = s;
        this.posiljalac = new Posiljalac(s);
        this.primalac = new Primalac(s);
    }

    public ObradaKlijentskihZahteva() {
    }

    // posto je nit moramo da implementiramo run metodu
    // ona treba da osluskuje i ceka kada cemo mi da primimo zahtev
    @Override
    public void run() {
        while (!kraj) {
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            try {
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Prodavac p = (Prodavac) zahtev.getParametar();
                        p = controller.Controller.getInstance().login(p); // hocu da ga setujem nazad u odgovor 
                        odgovor.setOdgovor(p);
                        break;
                    case UCITAJ_PRODAVCE:
                        List<Prodavac> prodavci = Controller.getInstance().prikaziProdavce(); // ovde treba iz baze da se ucita -- idem preko kontrolera
                        odgovor.setOdgovor(prodavci);
                        break;
                    case OBRISI_PRODAVCA:
                        try {
                        Prodavac prodavac = (Prodavac) zahtev.getParametar();
                        Controller.getInstance().obrisiProdavca(prodavac);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_PRODAVCA:
                        Prodavac prodavac = (Prodavac) zahtev.getParametar();
                        Controller.getInstance().dodajProdavca(prodavac);
                        odgovor.setOdgovor(null); // u klij str u komunikac
                        break;
                    default:
                        System.out.println("Greska, operacija ne postoji!");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                odgovor.setOdgovor(ex.getMessage());
                posiljalac.posalji(odgovor);
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}
