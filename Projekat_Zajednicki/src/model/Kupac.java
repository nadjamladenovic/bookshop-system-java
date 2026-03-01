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
public class Kupac implements ApstraktniDomenskiObjekat {

    private int kupacID;
    private String imePrezime;
    private String email;
    private String brojtelefona;
    private String adresa;
    private Grad gradID;

    public Kupac() {
    }

    public Kupac(int kupacID, String imePrezime, String email, String brojtelefona, String adresa, Grad gradID) {
        this.kupacID = kupacID;
        this.imePrezime = imePrezime;
        this.email = email;
        
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
    public String toString() {
        return imePrezime;
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
        return this.kupacID == other.kupacID;
    }

   

    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        //ova metoda je nakon sto smo izvrsili selekt upit
        // vraca mi se neki result set kog treba da vratim kao listu
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKupca = rs.getInt("kupacID");
            String imePrezime = rs.getString("imePrezime");
            String email = rs.getString("email");
            String brojTelefona=rs.getString("brojTelefona");
            String adresa=rs.getString("adresa");
            int gradID=rs.getInt("gradID");
            String nazivGrada=rs.getString("nazivGrada");
            int postanskiBroj=rs.getInt("postanskiBroj");
            
            Grad g=new Grad(gradID, nazivGrada, postanskiBroj);
            
            Kupac k=new Kupac(idKupca, imePrezime, email,  brojTelefona, adresa, g);
            lista.add(k);

        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime,email,brojTelefona,adresa,gradID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        //return "'"+imePrezime+"', '"+email+"', '"+brojtelefona+"', "+gradID;
        return "'" + imePrezime + "', '" + email + "', '" + brojtelefona + "', '" + adresa + "', " + gradID.getGradID();
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
        return "imePrezime='"+imePrezime+"', email='"+email+"', brojTelefona='"+brojtelefona+"', gradID="+gradID.getGradID();
    }

}
