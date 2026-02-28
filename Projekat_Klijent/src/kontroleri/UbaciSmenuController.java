/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.UbaciSmenaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Prodavac;
import model.ProdavacRS;
import model.RadnaSmena;

/**
 *
 * @author Nadja
 */
public class UbaciSmenuController {

    private final UbaciSmenaForma usf;

    public UbaciSmenuController(UbaciSmenaForma usf) {
        this.usf = usf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        usf.setVisible(true);
    }

    private void pripremiFormu() {
        try {
            List<RadnaSmena> smene = Komunikacija.getInstance().vratiSmene();
            usf.getjComboBoxSmena().removeAllItems();
            for (RadnaSmena s : smene) {
                usf.getjComboBoxSmena().addItem(s);
            }
            usf.getjComboBoxSmena().setSelectedItem(null); // default prazno

            List<Prodavac> prodavci = Komunikacija.getInstance().ucitajProdavce();
            usf.getjComboBoxProdavci().removeAllItems();
            for (Prodavac p : prodavci) {
                usf.getjComboBoxProdavci().addItem(p);
            }
            usf.getjComboBoxProdavci().setSelectedItem(null); // default prazno
        } catch (Exception ex) {
            Logger.getLogger(UbaciSmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        usf.ubaciSmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodaj(e);
                } catch (Exception ex) {
                    Logger.getLogger(UbaciSmenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodaj(ActionEvent e) throws ParseException {
                try {
                  /*  RadnaSmena rs = (RadnaSmena) usf.getjComboBoxSmena().getSelectedItem();
                    Prodavac p = (Prodavac) usf.getjComboBoxProdavci().getSelectedItem();
                    String vremePocetka = usf.getjTextFieldVremePocetka().getText().trim();
                    String vremeKraja = usf.getjTextFieldVremeKraja().getText().trim();

                    if (vremePocetka.isEmpty() || vremeKraja.isEmpty() || rs == null || p == null) {
                        JOptionPane.showMessageDialog(usf, "Morate popuniti sva polja!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    sdf.setLenient(false);
                    int trajanjeMinuti = 0;
                    java.time.LocalTime pocetakLocal;
                    java.time.LocalTime krajLocal;
                    try {
                        Date pocetak = sdf.parse(vremePocetka);
                        Date kraj = sdf.parse(vremeKraja);
                        if (kraj.before(pocetak)) {
                            JOptionPane.showMessageDialog(usf, "Vreme kraja ne moze biti pre vreme pocetka", "GRESKA", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int razlikaMS = (int) (kraj.getTime() - pocetak.getTime());
                        trajanjeMinuti = razlikaMS / (1000 * 60);
                        pocetakLocal = java.time.LocalTime.parse(vremePocetka);
                        krajLocal = java.time.LocalTime.parse(vremeKraja);
                    } catch (ParseException pe) {
                        JOptionPane.showMessageDialog(usf, "Vreme mora biti u formatu HH:mm:ss!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    RadnaSmena smena = new RadnaSmena(-1, trajanjeMinuti, rs.getTipSmene(),pocetakLocal,krajLocal);
                    Komunikacija.getInstance().UbaciProdavacSmena(smena);

                    JOptionPane.showMessageDialog(usf, "Sistem je zapamtio smenu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    usf.dispose();*/
                  RadnaSmena rs = (RadnaSmena) usf.getjComboBoxSmena().getSelectedItem();
                    Prodavac p = (Prodavac) usf.getjComboBoxProdavci().getSelectedItem();
                    String datumString = usf.getjTextFieldDatumSmene().getText().trim();

                    if (datumString.isEmpty() || rs == null || p == null) {
                        JOptionPane.showMessageDialog(usf, "Morate popuniti sva polja!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Date datum;
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        sdf.setLenient(false);
                        datum = sdf.parse(datumString);
                    } catch (ParseException pe) {
                        JOptionPane.showMessageDialog(usf, "Datum mora biti u formatu dd.MM.yyyy!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    ProdavacRS prodavacrs = new ProdavacRS(p,rs,datum);
                    Komunikacija.getInstance().UbaciProdavacSmena(prodavacrs);

                    JOptionPane.showMessageDialog(usf, "Sistem je zapamtio smenu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    usf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(usf, "Sistem ne moze da zapamti smenu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
    }
}
