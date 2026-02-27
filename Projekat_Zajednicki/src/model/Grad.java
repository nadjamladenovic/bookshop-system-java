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
public class Grad implements ApstraktniDomenskiObjekat{
    private int gradID;
    private String nazivGrada;
    private int postanskibroj;

    public Grad() {
    }

    public Grad(int gradID, String nazivGrada, int postanskibroj) {
        this.gradID = gradID;
        this.nazivGrada = nazivGrada;
        this.postanskibroj = postanskibroj;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    public int getPostanskibroj() {
        return postanskibroj;
    }

    public void setPostanskibroj(int postanskibroj) {
        this.postanskibroj = postanskibroj;
    }

    @Override
    public String vratiNazivTabele() {
        return "grad";
    }

    @Override
    public String toString() {
        return nazivGrada;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Grad other = (Grad) obj;
        return this.gradID == other.gradID;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int gradID=rs.getInt("grad.gradID");
            String nazivGrada=rs.getString("grad.nazivGrada");
            int postanskiBroj=rs.getInt("grad.postanskiBroj");
            Grad g = new Grad(gradID, nazivGrada, postanskiBroj);
            lista.add(g);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivGrada,postanskiBroj";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivGrada+"',"+postanskibroj;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "grad.gradID="+gradID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivGrada='"+nazivGrada+"', postanskiBroj="+postanskibroj;
    }
    
}
