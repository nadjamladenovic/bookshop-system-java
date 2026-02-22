/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import static komunikacija.Operacija.LOGIN;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author Nadja
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        this.posiljalac = new Posiljalac(s);
        this.primalac = new Primalac(s);
    }

    public ObradaKlijentskihZahteva() {
    }

    @Override
    public void run() {
        while (!kraj) {
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor=new Odgovor();
            switch(zahtev.getOperacija()) {
               case LOGIN:
                    
                 //   break;
                default:
                    System.out.println("Greska, operacija ne postoji!");
            }
            posiljalac.posalji(odgovor);
        }
    }
public void prekini(){
    kraj=true;
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    interrupt();
}
}
