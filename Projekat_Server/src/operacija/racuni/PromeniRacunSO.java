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
public class PromeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne moze da nadje racun");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       Racun r = (Racun) param;
        
        // 1. Ažuriraj sam račun
        broker.edit(r);

        // 2. Pronađi stare stavke i obriši ih
        String uslov = " WHERE racunID=" + r.getRacunID();
        List<StavkaRacuna> stareStavke = broker.getAll(new StavkaRacuna(), uslov);
        
        for (StavkaRacuna sr : stareStavke) {
            // DODAJ OVU LINIJU - Osiguraj da stara stavka zna koji račun briše
            sr.setRacunID(r.getRacunID()); 
            broker.delete(sr);
        }

        // 3. Dodaj nove stavke
        List<StavkaRacuna> noveStavke = r.getStavke();
        for (StavkaRacuna sR : noveStavke) {
            // DODAJ OVU LINIJU - Ključno da se ID ne bi izgubio (da ne bude 0)
            sR.setRacunID(r.getRacunID()); 
            
            // DODAJ OVAJ PRINT - Da bi u konzoli servera videla šta se dešava
            System.out.println("SERVER: Dodajem stavku za RacunID: " + sR.getRacunID() + ", RB: " + sR.getRb());
            
            broker.add(sR);
        }

        System.out.println("Uspesno izmenjen racun sa ID: " + r.getRacunID());
    }

}
