/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavkaRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;

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
        gf.dodajStavkuAddActionListener(e -> {
            if (gf.getjComboBoxKnjiga().getSelectedItem() == null
                    || gf.getjTextFieldKolicina().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(gf, "Morate uneti knjigu i kolicinu", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Knjiga k = (Knjiga) gf.getjComboBoxKnjiga().getSelectedItem();
            int kolicina;
            try {
                kolicina = Integer.parseInt(gf.getjTextFieldKolicina().getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gf, "Količina mora biti ceo broj!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double ukIznos = k.getCena() * kolicina;
            gf.getjTextFieldUkupanIznos().setText(String.valueOf(ukIznos));

            StavkaRacuna sr = new StavkaRacuna();
            sr.setCena(k.getCena());
            sr.setKolicina(kolicina);
            sr.setIznos(ukIznos);
            sr.setKnjigaID(k);

            ModelTabeleStavkaRacuna mts = (ModelTabeleStavkaRacuna) gf.getjTableStavkeRacuna().getModel();
            mts.dodajStavku(sr);
        });
        gf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();

            }
        });
        gf.obrisiStavkuAddActionListener(e -> {
            int red = gf.getjTableStavkeRacuna().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(gf, "Morate odabrati stavku za brisanje", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ModelTabeleStavkaRacuna mts = (ModelTabeleStavkaRacuna) gf.getjTableStavkeRacuna().getModel();
            mts.obrisiStavku(mts.getLista().get(red));
        });

        // Kreiranje računa
        gf.dodajRacunAddActionListener(e -> kreirajRacun());

        // Izmena računa
        gf.izmeniRacunAddActionListener(e -> izmeniRacun());
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
            gf.getjTextFieldIDRacuna().setText(r.getRacunID() + "");
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

    private void izmeniRacun() {
        try {
            Racun r = pripremiRacun(true);
            if (r == null) {
                return;
            }

            Komunikacija.getInstance().PromeniRacun(r);
            JOptionPane.showMessageDialog(gf, "Sistem je zapamtio račun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gf, "Sistem ne može da zapamti račun", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Racun pripremiRacun(boolean izmena) {
        if (gf.getjComboBoxKupac().getSelectedItem() == null
                || gf.getjComboBoxProdavac().getSelectedItem() == null
                || gf.getjTextFieldDatum().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        ModelTabeleStavkaRacuna mts = (ModelTabeleStavkaRacuna) gf.getjTableStavkeRacuna().getModel();
        List<StavkaRacuna> stavke = mts.getLista();
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate dodati barem jednu stavku", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Racun r = new Racun();
        r.setStavke(stavke);
        r.setProdavacID(Cordinator.getInstance().getUlogovaniProdavac());
        r.setKupacID((Kupac) gf.getjComboBoxKupac().getSelectedItem());

        if (izmena) {
            try {
                r.setRacunID(Integer.parseInt(gf.getjTextFieldIDRacuna().getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gf, "ID racuna nije validan broj", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        double ukIznos = 0.0;
        for (StavkaRacuna sr : stavke) {
            ukIznos += sr.getIznos();
        }
        r.setUkupanIznos(ukIznos);

        String datumString = gf.getjTextFieldDatum().getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date datum = sdf.parse(datumString);
            r.setDatum(datum);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Datum nije validan! Format: dd.MM.yyyy", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return r;
    }

    private void kreirajRacun() {
        try {
            Racun r = pripremiRacun(false);
            if (r == null) {
                return;
            }

            Komunikacija.getInstance().KreirajRacun(r);
            JOptionPane.showMessageDialog(gf, "Sistem je kreirao račun", "USPEH", JOptionPane.INFORMATION_MESSAGE);

            gf.getjTextFieldIDRacuna().setText("");
            gf.getjTextFieldKolicina().setText("");
            gf.getjTextFieldUkupanIznos().setText("");
            gf.getjTextFieldDatum().setText("");
            gf.getjComboBoxKupac().setSelectedItem(null);
            gf.getjComboBoxKnjiga().setSelectedItem(null);
            gf.getjTableStavkeRacuna().setModel(new ModelTabeleStavkaRacuna(new ArrayList<>()));
        } catch (Exception ex) {
            Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gf, "Sistem ne može da kreira račun", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu() {
        // 1. Resetuj tekstualna polja
        gf.getjTextFieldIDRacuna().setText("");
        gf.getjTextFieldKolicina().setText("");
        gf.getjTextFieldUkupanIznos().setText("");
        gf.getjTextFieldDatum().setText("");

        // 2. Resetuj ComboBox-eve
        gf.getjComboBoxKupac().setSelectedItem(null);
        gf.getjComboBoxKnjiga().setSelectedItem(null);

        // Prodavca uvek setujemo na trenutno ulogovanog
        gf.getjComboBoxProdavac().setSelectedItem(Cordinator.getInstance().getUlogovaniProdavac());

        // 3. Isprazni tabelu stavki
        // Kreiramo potpuno novu praznu listu i setujemo je u model
        ModelTabeleStavkaRacuna mts = (ModelTabeleStavkaRacuna) gf.getjTableStavkeRacuna().getModel();
        mts.setLista(new ArrayList<>());

        // Opciono: Ako želiš da ponovo omogućiš polja koja su možda bila zaključana u Detaljima
        gf.getjTextFieldIDRacuna().setEnabled(true);
        gf.getjTextFieldDatum().setEditable(true);
        gf.getjComboBoxProdavac().setEnabled(true);
        gf.getjComboBoxKupac().setEnabled(true);
        gf.getjTableStavkeRacuna().setEnabled(true);

        // Podesi vidljivost dugmića za kreiranje novog
        gf.getjButtonKreirajRacun().setVisible(true);
        gf.getjButtonIzmeniRacun().setVisible(false);
    }
}
