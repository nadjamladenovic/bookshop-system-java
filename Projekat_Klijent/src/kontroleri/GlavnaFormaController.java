/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class GlavnaFormaController {

    private GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {

    }

    public void otvoriFormu() {
        gf.getjButtonIzmeniRacun().setVisible(false);
        Prodavac ulogovani = cordinator.Cordinator.getInstance().getUlogovaniProdavac();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(ulogovani.getImePrezime());

        ModelTabeleStavkaRacuna mts = new ModelTabeleStavkaRacuna(new ArrayList<>());
        gf.getjTableStavkeRacuna().setModel(mts);

        popuniComboBoxeve();
    }

    private void popuniComboBoxeve() {
        List<Prodavac> sviProdavci = Komunikacija.getInstance().ucitajProdavce();
        gf.getjComboBoxProdavac().removeAllItems();
        for (Prodavac p : sviProdavci) {
            gf.getjComboBoxProdavac().addItem(p);
        }
        gf.getjComboBoxProdavac().setSelectedItem(Cordinator.getInstance().getUlogovaniProdavac());

        List<Kupac> sviKupci = Komunikacija.getInstance().ucitajKupce();
        gf.getjComboBoxKupac().removeAllItems();
        for (Kupac k : sviKupci) {
            gf.getjComboBoxKupac().addItem(k);
        }
        gf.getjComboBoxKupac().setSelectedItem(null);

        List<Knjiga> sveKnjige = Komunikacija.getInstance().ucitajKnjige();
        gf.getjComboBoxKnjiga().removeAllItems();
        for (Knjiga k : sveKnjige) {
            gf.getjComboBoxKnjiga().addItem(k);
        }
        gf.getjComboBoxKnjiga().setSelectedItem(null);
    }

    
}
