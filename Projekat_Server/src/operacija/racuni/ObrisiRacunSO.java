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
public class ObrisiRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Racun)){
            throw  new Exception("Sistem ne moze da obrise racun - obrisiSO");
          
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        // da bih obrisala racun prvo moram sve stavke tog racuna pa onda racun
        Racun r=(Racun) param;
        List<StavkaRacuna>stavke=r.getStavke();
        for (StavkaRacuna s : stavke) {
            s.setRacunID(r.getRacunID());
            broker.delete(s);
        }
        broker.delete(r);
    }
    
}
