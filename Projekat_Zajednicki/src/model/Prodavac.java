/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nadja
 */
public class Prodavac implements ApstraktniDomenskiObjekat {

    private int prodavacID;
    private String imePrezime;
    private String email;
    private String korisnickoIme;
    private String lozinka;

    public Prodavac() {
    }

    public Prodavac(int prodavacID, String imePrezime, String email, String korisnickoIme, String lozinka) {
        this.prodavacID = prodavacID;
        this.imePrezime = imePrezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Prodavac(String imePrezime, String email, String korisnickoIme, String lozinka) {

        this.imePrezime = imePrezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(int prodavacID) {
        this.prodavacID = prodavacID;
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

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Prodavac other = (Prodavac) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int prodavacID = rs.getInt("prodavac.prodavacID");
            String imePrezime = rs.getString("prodavac.imePrezime");
            String email = rs.getString("prodavac.email");
            String korisnickoIme = rs.getString("prodavac.korisnickoIme");
            String lozinka = rs.getString("prodavac.lozinka");
            Prodavac p = new Prodavac(prodavacID, imePrezime, email, korisnickoIme, lozinka);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, email, korisnickoIme, lozinka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "', '" + email + "', '" + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavac.prodavacID=" + prodavacID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', email='" + email + "', korisnickoIme='" + korisnickoIme + "', lozinka='" + lozinka + "'";
    }

}
