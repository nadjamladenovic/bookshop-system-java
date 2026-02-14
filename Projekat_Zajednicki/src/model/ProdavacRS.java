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
public class ProdavacRS implements ApstraktniDomenskiObjekat {
    private Prodavac prodavacID;
    private RadnaSmena radnaSmenaID;
    private Date datumSmene;

    public ProdavacRS() {
    }

    public ProdavacRS(Prodavac prodavacID, RadnaSmena radnaSmenaID, Date datumSmene) {
        this.prodavacID = prodavacID;
        this.radnaSmenaID = radnaSmenaID;
        this.datumSmene = datumSmene;
    }

    public Prodavac getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(Prodavac prodavacID) {
        this.prodavacID = prodavacID;
    }

    public RadnaSmena getRadnaSmenaID() {
        return radnaSmenaID;
    }

    public void setRadnaSmenaID(RadnaSmena radnaSmenaID) {
        this.radnaSmenaID = radnaSmenaID;
    }

    public Date getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(Date datumSmene) {
        this.datumSmene = datumSmene;
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavacrs";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "prodavacID,radnaSmenaID,datumSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return prodavacID.getProdavacID() + ", " + radnaSmenaID.getRadnaSmenaID()+ ", '" +new java.sql.Date(datumSmene.getTime()) + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavacrs.prodavacID="+prodavacID.getProdavacID()+" AND prodavacrs.radnaSmenaID="+radnaSmenaID.getRadnaSmenaID()+" AND prodavacrs.datumSmene="+datumSmene;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
