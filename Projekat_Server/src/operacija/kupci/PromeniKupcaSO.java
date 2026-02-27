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
            throw new Exception("Sistem ne moze da prepozna kupca.");
        }
        
        Kupac kupac = (Kupac) param;

        // 1. Provera Imena i Prezimena
        // Dozvoljavamo slova i razmak (Character.isWhitespace)
        if (kupac.getImePrezime() == null || kupac.getImePrezime().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca (ime i prezime su prazni).");
        }
        for (int i = 0; i < kupac.getImePrezime().length(); i++) {
            char c = kupac.getImePrezime().charAt(i);
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                throw new Exception("Sistem ne moze da zapamti kupac imePrezime (dozvoljena samo slova i razmak).");
            }
        }

        // 2. Provera Email-a
        // Proveravamo samo da li je prazan i da li ima osnovne znake @ i .
        if (kupac.getEmail() == null || kupac.getEmail().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca (email je prazan).");
        }
        if (!kupac.getEmail().contains("@") || !kupac.getEmail().contains(".")) {
            throw new Exception("Sistem ne moze da zapamti kupac email (nevalidan format).");
        }

        // 3. Provera Broja telefona
        // Ovde ostavljamo proveru cifara, ali dodajemo opseg (npr. 9 do 11 cifara)
        if (kupac.getBrojtelefona() == null || kupac.getBrojtelefona().length() < 9 || kupac.getBrojtelefona().length() > 11) {
            throw new Exception("Sistem ne moze da zapamti kupca (broj telefona mora imati 9-11 cifara).");
        }
        for (int i = 0; i < kupac.getBrojtelefona().length(); i++) {
            if (!Character.isDigit(kupac.getBrojtelefona().charAt(i))) {
                throw new Exception("Sistem ne moze da zapamti kupac brojTelefona (samo cifre su dozvoljene).");
            }
        }

        // 4. Provera Adrese
        // Adresa MORA da dozvoli i brojeve i razmake (npr. "Knez Mihailova 10")
        if (kupac.getAdresa() == null || kupac.getAdresa().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti kupca (adresa je prazna).");
        }
        // Ovde NE koristimo petlju isLetter jer adresa skoro uvek ima broj i razmak

        // 5. Provera Grada
        if (kupac.getGradID() == null) {
            throw new Exception("Sistem ne moze da zapamti kupca (grad nije odabran).");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Kupac) param);
    }

}
