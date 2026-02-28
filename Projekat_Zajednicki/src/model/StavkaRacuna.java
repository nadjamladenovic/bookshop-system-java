/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class StavkaRacuna implements ApstraktniDomenskiObjekat {

    private int racunID;
    private int rb;
    private double iznos;
    private int kolicina;
    private double cena;
    private Knjiga knjigaID;

    public StavkaRacuna() {
    }

    public StavkaRacuna(int racunID, int rb, double iznos, int kolicina, double cena, Knjiga knjigaID) {
        this.racunID = racunID;
        this.rb = rb;
        this.iznos = iznos;
        this.kolicina = kolicina;
        this.cena = cena;
        this.knjigaID = knjigaID;
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Knjiga getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(Knjiga knjigaID) {
        this.knjigaID = knjigaID;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        /*  List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            int rb = rs.getInt("rb");
            double cenaUsluge = rs.getDouble("cena");
            int kolicina = rs.getInt("kolicina");
            double iznos = rs.getDouble("iznos");
            int racunID = rs.getInt("racunID");

            int knjigaID = rs.getInt("knjigaID");
            double cena = rs.getDouble("cena");
            String naziv = rs.getString("naziv");
            String autor = rs.getString("autor");

            Knjiga k = new Knjiga(knjigaID, naziv, autor, cena);
            StavkaRacuna stavka = new StavkaRacuna(racunID, rb, iznos, kolicina, cena, k);

            lista.add(stavka);
        }
        return lista;*/
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("rb");
            int kolicina = rs.getInt("kolicina");
            double iznos = rs.getDouble("iznos");
            int racunID = rs.getInt("racunID");
            int knjigaID = rs.getInt("knjigaID");
            double cena = rs.getDouble("cena");

            // Obezbeđujemo default vrednosti za knjigu
            String naziv = "N/A";
            String autor = "N/A";

            // Proveravamo da li kolone 'naziv' i 'autor' postoje u ResultSet-u
            // Ovo sprečava SQLSyntaxErrorException: Column 'naziv' not found
            try {
                naziv = rs.getString("naziv");
                autor = rs.getString("autor");
            } catch (java.sql.SQLException e) {
                // Ako kolone ne postoje (jer nema JOIN-a), program nastavlja dalje sa "N/A"
                // To je sasvim dovoljno jer nam za brisanje trebaju samo racunID i rb
            }

            Knjiga k = new Knjiga(knjigaID, naziv, autor, cena);
            StavkaRacuna stavka = new StavkaRacuna(racunID, rb, iznos, kolicina, cena, k);

            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "racunID,rb,iznos,kolicina,cena,knjigaID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return racunID + ", " + rb + ", " + iznos + ", " + kolicina + ", " + cena + ", " + knjigaID.getKnjigaID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racunID=" + racunID + " AND rb=" + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "iznos=" + iznos + ", kolicina=" + kolicina + ", cena=" + cena + ", knjigaID=" + knjigaID.getKnjigaID();
    }

}
