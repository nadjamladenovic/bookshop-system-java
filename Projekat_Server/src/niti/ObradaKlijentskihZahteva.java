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
import model.Grad;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.ProdavacRS;
import model.Racun;
import model.RadnaSmena;

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
        try {
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
                        case AZURIRAJ_PRODAVCA:
                            Prodavac prodavacA = (Prodavac) zahtev.getParametar();
                            Controller.getInstance().azurirajProdavca(prodavacA);
                            odgovor.setOdgovor("Uspesno"); // u klij str u komunikac
                            break;
                        case UCITAJ_KUPCE:
                            List<Kupac> kupci = Controller.getInstance().prikaziKupce();
                            odgovor.setOdgovor(kupci);
                            break;
                        case OBRISI_KUPCA:
                          try {
                            Kupac k = (Kupac) zahtev.getParametar();
                            Controller.getInstance().obrisiKupca(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        case DODAJ_KUPCA:
                            Kupac k2 = (Kupac) zahtev.getParametar();
                            Controller.getInstance().dodajKupca(k2);
                            odgovor.setOdgovor(null);
                            break;
                        case PROMENI_KUPCA:
                            Kupac k3 = (Kupac) zahtev.getParametar();
                            Controller.getInstance().PromeniKupca(k3);
                            odgovor.setOdgovor(null);
                            break;
                        case UCITAJ_KNJIGE:
                            List<Knjiga> knjiga = Controller.getInstance().prikaziKnjige();
                            odgovor.setOdgovor(knjiga);
                            break;
                        case UCITAJ_SMENE:
                            List<RadnaSmena> smene = Controller.getInstance().ucitajSmene();
                            odgovor.setOdgovor(smene);
                            break;
                        case UBACI_SMENU:
                            ProdavacRS prodavacRS = (ProdavacRS) zahtev.getParametar();
                            Controller.getInstance().ubaciProdavacSmena(prodavacRS);
                            odgovor.setOdgovor(null);
                            break;
                        case UCITAJ_GRAD:
                            List<Grad> grad = Controller.getInstance().ucitajGradove();
                            odgovor.setOdgovor(grad);
                            break;
                        case UCITAJ_RACUNE:
                            List<Racun> racuni = Controller.getInstance().prikaziRacune();
                            System.out.println("KLASA OKZ: ");
                            System.out.println(racuni);
                            odgovor.setOdgovor(racuni);
                            break;
                        default:
                            System.out.println("Greska, operacija ne postoji!");
                    }
                    posiljalac.posalji(odgovor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    odgovor.setOdgovor(ex.getMessage());
                    posiljalac.posalji(odgovor);
                }
            }
        } catch (Exception ex) {
            System.out.println("Klijent se odjavio (Vezu prekinuo klijent).");
        } finally {
            prekini(); // Obavezno zatvori soket na serverskoj strani
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
