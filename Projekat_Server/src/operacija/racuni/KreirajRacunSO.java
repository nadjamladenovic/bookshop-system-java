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
public class KreirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne može da kreira račun.");
        }
        //datum ne moze iz proslosti
        Racun r = (Racun) param;
        if (r.getUkupanIznos() < 0) {
            throw new Exception("Sistem ne može da kreira račun.");
        }
        //jos neki preduslov
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
//ovde treba da kreiram racun i sve njegove stavke
        Racun r = (Racun) param;
        int idRacuna = broker.addReturnKey(param);

        List<StavkaRacuna> stavke = r.getStavke();
        for (StavkaRacuna sR : stavke) { // svaku stavku sa metodom add dodajemo u bazu i dodeljujemo joj id racuna
            sR.setRacunID(idRacuna);
            broker.add(sR);

        }
    }

}
