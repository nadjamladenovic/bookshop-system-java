/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String joinUslov = " JOIN prodavac p ON p.prodavacID=racun.prodavacID JOIN kupac k ON k.kupacID=racun.kupacID";
        List<String> uslovi = new ArrayList<>();
        Racun racun = (Racun) param;
        if (racun.getRacunID() != 0) {
            uslovi.add("racun.racunID=" + racun.getRacunID());
        }
        if (racun.getUkupanIznos() != 0) {
            uslovi.add("racun.ukupanIznos <= " + racun.getUkupanIznos());
        }
        if (racun.getDatum() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatiraniDatum = sdf.format(racun.getDatum());
            uslovi.add("racun.datum='" + formatiraniDatum + "'");

        }

        if (racun.getProdavacID() != null) {
            uslovi.add("p.prodavacID=" + racun.getProdavacID().getProdavacID());
        }
        if (racun.getKupacID() != null) {
            uslovi.add("k.kupacID=" + racun.getKupacID().getKupacID());
        }

        String whereUslov = "";
        if (!uslovi.isEmpty()) {
            whereUslov = " WHERE " + String.join(" AND ", uslovi);
        }

        String finalniUslov = joinUslov + whereUslov;
        System.out.println(finalniUslov);

        racuni = broker.getAll(new Racun(), finalniUslov);
        if (racuni != null) {
            for (model.Racun r : racuni) {
                // Pozivamo stavke za svaki pronađeni račun
                String uslovStavke = " JOIN knjiga k ON k.knjigaID = stavkaracuna.knjigaID "
                                   + "WHERE stavkaracuna.racunID=" + r.getRacunID();
                
                List<model.StavkaRacuna> stavke = broker.getAll(new model.StavkaRacuna(), uslovStavke);
                r.setStavke(stavke); // Sada račun "nosi" svoje stavke sa sobom na klijent
                
                System.out.println("DEBUG: Pronađeno " + stavke.size() + " stavki za filtrirani račun ID: " + r.getRacunID());
            }
        }
    }

}
