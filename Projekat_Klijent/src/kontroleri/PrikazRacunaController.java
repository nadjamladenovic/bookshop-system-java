/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.PrikazProdavacaForma;
import forme.PrikazRacunaForma;
import forme.model.ModelTabeleProdavac;
import forme.model.ModelTabeleRacuni;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Prodavac;
import model.Racun;

/**
 *
 * @author Nadja
 */
public class PrikazRacunaController {
    private final PrikazRacunaForma prf;

    public PrikazRacunaController(PrikazRacunaForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }
//metoda za dugme

    private void addActionListeners() {
    
    }

    private void pripremiFormu() {
        try {
            List<Racun> racuni = komunikacija.Komunikacija.getInstance().ucitajRacune();
            ModelTabeleRacuni mtr = new ModelTabeleRacuni(racuni);
            prf.getjTableRacuni().setModel(mtr);
        } catch (Exception ex) {
            Logger.getLogger(PrikazProdavacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
