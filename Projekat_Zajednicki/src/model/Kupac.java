/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nadja
 */
public class Kupac implements ApstraktniDomenskiObjekat {

    private int kupacID;
    private String imePrezime;
    private String email;
    private String korisnickoIme;
    private String lozinka;
    private String brojtelefona;
    private String adresa;
    private Grad gradID;

    public Kupac() {
    }

    public Kupac(int kupacID, String imePrezime, String email, String korisnickoIme, String lozinka, String brojtelefona, String adresa, Grad gradID) {
        this.kupacID = kupacID;
        this.imePrezime = imePrezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.brojtelefona = brojtelefona;
        this.adresa = adresa;
        this.gradID = gradID;
    }

    public int getKupacID() {
        return kupacID;
    }

    public void setKupacID(int kupacID) {
        this.kupacID = kupacID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getBrojtelefona() {
        return brojtelefona;
    }

    public void setBrojtelefona(String brojtelefona) {
        this.brojtelefona = brojtelefona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Grad getGradID() {
        return gradID;
    }

    public void setGradID(Grad gradID) {
        this.gradID = gradID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kupac other = (Kupac) obj;
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.brojtelefona, other.brojtelefona)) {
            return false;
        }
        return Objects.equals(this.adresa, other.adresa);
    }

    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime,email,korisnickoIme,lozinka,brojTelefona,adresa,gradID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezime+"', '"+email+"', '"+korisnickoIme+"', '"+lozinka+"', '"+brojtelefona+"', "+gradID;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.kupacID="+kupacID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='"+imePrezime+"', email='"+email+"', korisnickoIme='"+korisnickoIme+"', lozinka='"+lozinka+"', brojTelefona='"+brojtelefona+"', gradID="+gradID.getGradID();
    }

}
