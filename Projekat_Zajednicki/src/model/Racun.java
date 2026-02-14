/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class Racun implements ApstraktniDomenskiObjekat {
    private int racunID;
    private Date datum;
    private double ukupanIznos;
    private String status;
    private Prodavac prodavacID;
    private Kupac kupacID;

    public Racun() {
    }

    public Racun(int racunID, Date datum, double ukupanIznos, String status, Prodavac prodavacID, Kupac kupacID) {
        this.racunID = racunID;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.status = status;
        this.prodavacID = prodavacID;
        this.kupacID = kupacID;
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Prodavac getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(Prodavac prodavacID) {
        this.prodavacID = prodavacID;
    }

    public Kupac getKupacID() {
        return kupacID;
    }

    public void setKupacID(Kupac kupacID) {
        this.kupacID = kupacID;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,ukupanIznos,status,prodavacID,kupacID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datum.getTime()) +"', "+ukupanIznos+", '"+status+"', "+prodavacID.getProdavacID()+", "+kupacID.getKupacID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.racunID="+racunID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + new java.sql.Date(datum.getTime()) + "', ukupanIznos=" + ukupanIznos + ", status='" + status + "', prodavacID=" + prodavacID.getProdavacID() + ", kupacID=" + kupacID.getKupacID();
    }


    
    
}
