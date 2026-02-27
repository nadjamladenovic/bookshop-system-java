/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class UcitajRacuneSO extends ApstraktnaGenerickaOperacija {

    List<Racun> racuni;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN prodavac p ON p.prodavacID=racun.prodavacID "
                + "JOIN kupac k ON k.kupacID=racun.kupacID";

        racuni = broker.getAll(new Racun(), uslov);

        for (Racun r : racuni) {
            String uslovStavke = " JOIN knjiga k ON k.knjigaID = stavkaracuna.knjigaID "
                    + "WHERE racunID=" + r.getRacunID();
            List<StavkaRacuna> stavke = broker.getAll(new StavkaRacuna(), uslovStavke);
            r.setStavke(stavke);
        }
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

}
