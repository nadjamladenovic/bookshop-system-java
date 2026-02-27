/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    private List<StavkaRacuna> stavke = new ArrayList<>();

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

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
       while (rs.next()) {
            // **RACUN
            int idRacuna = rs.getInt("racun.racunID");
            double ukIznos = rs.getDouble("racun.ukupanIznos");
            Date datumIzdavanja = rs.getDate("racun.datum");
            String status=rs.getString("racun.status");
//         
           // ** Prodavac** 
            int prodavacID=rs.getInt("p.prodavacID");
            String imePrezime=rs.getString("p.imePrezime");
            //String email=rs.getString("g.prezime");   
           Prodavac p=new Prodavac(prodavacID, imePrezime, null, null, null);
           
           // **Kupac **
           int kupacID=rs.getInt("k.kupacID");
           String imePrezimeK=rs.getString("k.imePrezime");
           String brojtelefona=rs.getString("k.brojTelefona");
           String adresa=rs.getString("k.adresa");           
           Kupac k = new Kupac(kupacID, imePrezime, null, brojtelefona, adresa, null);
           
           Racun r=new Racun(idRacuna, datumIzdavanja, ukIznos, status, p, k);
           r.setStavke(new ArrayList<>()); // prazna lista stavki
           lista.add(r);
            
            

         
         }
         return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,ukupanIznos,status,prodavacID,kupacID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datum.getTime()) + "', " + ukupanIznos + ", '" + status + "', " + prodavacID.getProdavacID() + ", " + kupacID.getKupacID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.racunID=" + racunID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + new java.sql.Date(datum.getTime()) + "', ukupanIznos=" + ukupanIznos + ", status='" + status + "', prodavacID=" + prodavacID.getProdavacID() + ", kupacID=" + kupacID.getKupacID();
    }

    @Override
    public String toString() {
        return "Racun{" + "racunID=" + racunID + ", datum=" + datum + ", ukupanIznos=" + ukupanIznos + ", status=" + status + ", prodavacID=" + prodavacID + ", kupacID=" + kupacID + ", stavke=" + stavke + '}';
    }

}
