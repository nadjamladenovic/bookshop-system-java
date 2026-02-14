/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class RadnaSmena implements ApstraktniDomenskiObjekat {

    private int radnaSmenaID;
    private int trajanje;
    private String tipSmene;
    private LocalTime vremePocetka;
    private LocalTime vremeKraja;

    public RadnaSmena() {
    }

    public RadnaSmena(int radnaSmenaID, int trajanje, String tipSmene, LocalTime vremePocetka, LocalTime vremeKraja) {
        this.radnaSmenaID = radnaSmenaID;
        this.trajanje = trajanje;
        this.tipSmene = tipSmene;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
    }

    public int getRadnaSmenaID() {
        return radnaSmenaID;
    }

    public void setRadnaSmenaID(int radnaSmenaID) {
        this.radnaSmenaID = radnaSmenaID;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getTipSmene() {
        return tipSmene;
    }

    public void setTipSmene(String tipSmene) {
        this.tipSmene = tipSmene;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    @Override
    public String vratiNazivTabele() {
        return "radnasmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while (rs.next()) {
        int radnaSmenaID = rs.getInt("radnasmena.radnaSmenaID");
        int trajanje = rs.getInt("radnasmena.trajanje");
        String tipSmene = rs.getString("radnasmena.tipSmene");
        LocalTime vremePocetka = rs.getTime("radnasmena.vremePocetka").toLocalTime();
        LocalTime vremeKraja = rs.getTime("radnasmena.vremeKraja").toLocalTime();
        RadnaSmena rsmena = new RadnaSmena(radnaSmenaID, trajanje, tipSmene, vremePocetka, vremeKraja);
        lista.add(rsmena);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "trajanje,tipSmene,vremePocetka,vremeKraja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return trajanje + ", '" + tipSmene + "', '" + java.sql.Time.valueOf(vremePocetka) + "', '" + java.sql.Time.valueOf(vremeKraja) + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "radnasmena.radnaSmenaID="+radnaSmenaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "trajanje=" + trajanje + ", tipSmene='" + tipSmene + "', vremePocetka='" + vremePocetka + "', vremeKraja='" + vremeKraja + "'";
    }

}
