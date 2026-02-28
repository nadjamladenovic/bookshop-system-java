/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavkaRacuna;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;

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

    public void otvoriFormu(FormaMod formaMod) {
        popuniComboBoxeve();
        Prodavac ulogovani = Cordinator.getInstance().getUlogovaniProdavac();
        gf.getjLabelUlogovani().setText(ulogovani.getImePrezime());
        gf.setVisible(true);

        ModelTabeleStavkaRacuna mts = new ModelTabeleStavkaRacuna(new ArrayList<>());
        gf.getjTableStavkeRacuna().setModel(mts);

        if (formaMod == FormaMod.PROMENI) {
            gf.getjButtonKreirajRacun().setVisible(false);
            Racun r = (Racun) Cordinator.getInstance().vratiParam("racunZaIzmenu");
            mts.setLista(r.getStavke());
            gf.getjTextFieldIDRacuna().setEnabled(false);
            gf.getjTextFieldIDRacuna().setText(r.getRacunID()+ "");
            gf.getjComboBoxProdavac().setSelectedItem(r.getProdavacID());
            gf.getjComboBoxKupac().setSelectedItem(r.getKupacID());
            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
            gf.getjTextFieldDatum().setText(formater.format(r.getDatum()));
        }

        if (formaMod == FormaMod.DETALJI) {
            Racun r = (Racun) Cordinator.getInstance().vratiParam("racunZaDetalje");
            mts.setLista(r.getStavke());
            gf.getjTextFieldIDRacuna().setText(r.getRacunID() + "");
            gf.getjComboBoxProdavac().setSelectedItem(r.getProdavacID());
            gf.getjComboBoxKupac().setSelectedItem(r.getKupacID());
            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
            gf.getjTextFieldDatum().setText(formater.format(r.getDatum()));

            gf.getjTextFieldIDRacuna().setEnabled(false);
            gf.getjTextFieldDatum().setEditable(false);
            gf.getjComboBoxProdavac().setEnabled(false);
            gf.getjComboBoxKupac().setEnabled(false);
            gf.getjTableStavkeRacuna().setEnabled(false);

            gf.getjButtonKreirajRacun().setVisible(false);
            gf.getjButtonIzmeniRacun().setVisible(false);
        }
    }

}
