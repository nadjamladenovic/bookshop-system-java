/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() throws IOException {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException e) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Prodavac login(String username, String password) throws Exception {
        Prodavac prodavac = new Prodavac();
        prodavac.setKorisnickoIme(username);
        prodavac.setLozinka(password);

        Zahtev zahtev = new Zahtev(Operacija.LOGIN, prodavac);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        //System.out.println("Podatak u odgovoru je: " + odg.getOdgovor().getClass().getName());
        // Provera: ako je odgovor null, znaci da login nije uspeo (pogresni podaci)
        if (odg.getOdgovor() == null) {
            System.out.println("Login neuspešan: Prodavac nije pronađen.");
            return null; 
        }
        return (Prodavac) odg.getOdgovor();
    }
}
