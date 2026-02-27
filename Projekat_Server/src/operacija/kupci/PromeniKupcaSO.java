/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class PromeniKupcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kupac)) {
            throw new Exception("Sistem ne moze da kreira kupca");
        }
        Kupac kupac = (Kupac) param;
        // **Ime i prezime **
        if (kupac.getImePrezime() == null || kupac.getImePrezime().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
        for (int i = 0; i < kupac.getImePrezime().length(); i++) {
            char c = kupac.getImePrezime().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception("Sistem ne moze da zapamti kupac imePrezime");
            }
        }
        // ** email **
        if (kupac.getEmail() == null || kupac.getEmail().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
        for (int i = 0; i < kupac.getEmail().length(); i++) {
            char c = kupac.getEmail().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception("Sistem ne moze da zapamti kupac email");
            }
        }
        // **BROJ TELEFONA **
        if (kupac.getBrojtelefona() == null || kupac.getBrojtelefona().isEmpty() || kupac.getBrojtelefona().length() < 10 || kupac.getBrojtelefona().length() > 10) {
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
        for (int i = 0; i < kupac.getBrojtelefona().length(); i++) {
            char c = kupac.getBrojtelefona().charAt(i);
            if (!Character.isDigit(c)) {
                throw new Exception("Sistem ne moze da zapamti kupac brojTelefona");
            }
        }
        // adresa
        if (kupac.getAdresa() == null || kupac.getAdresa().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
        for (int i = 0; i < kupac.getAdresa().length(); i++) {
            char c = kupac.getAdresa().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception("Sistem ne moze da zapamti kupac adresa");
            }
        }

        if (kupac.getGradID() == null) {
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Kupac) param);
    }

}
