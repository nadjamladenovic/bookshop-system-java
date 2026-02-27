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
public class Knjiga implements ApstraktniDomenskiObjekat {

    private int knjigaID;
    private String naziv;
    private String autor;
    private double cena;
    private String zanr;
    private String izdavalac;
    private int godinaIzdanja;

    public Knjiga() {
    }

    public Knjiga(int knjigaID, String naziv, String autor, double cena, String zanr, String izdavalac, int godinaIzdanja) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.cena = cena;
        this.zanr = zanr;
        this.izdavalac = izdavalac;
        this.godinaIzdanja = godinaIzdanja;
    }

    public Knjiga(int knjigaID, String naziv, String autor, double cena) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.cena = cena;
    }

    public int getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getIzdavalac() {
        return izdavalac;
    }

    public void setIzdavalac(String izdavalac) {
        this.izdavalac = izdavalac;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
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
        final Knjiga other = (Knjiga) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }

    @Override
    public String toString() {
        return  naziv + " " + autor ;
    }

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int knjigaID = rs.getInt("knjiga.knjigaID");
            String naziv = rs.getString("knjiga.naziv");
            String autor = rs.getString("knjiga.autor");
            double cena = rs.getDouble("knjiga.cena");
            String zanr = rs.getString("knjiga.zanr");
            String izdavalac = rs.getString("knjiga.izdavalac");
            int godinaIzdanja = rs.getInt("knjiga.godinaIzdanja");
            Knjiga k = new Knjiga(knjigaID, naziv, autor, cena, zanr, izdavalac, godinaIzdanja);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,autor,cena,zanr,izdavalac,godinaIzdanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + autor + "'," + cena + ", '" + zanr + "', '" + izdavalac + "', " + godinaIzdanja;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "knjiga.knjigaID=" + knjigaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', autor='" + autor + "', cena=" + cena + ", zanr='" + zanr + "', izdavalac='" + izdavalac + "', godinaIzdanja=" + godinaIzdanja;
    }

}
