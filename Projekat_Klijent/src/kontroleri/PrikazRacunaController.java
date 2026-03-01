/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.FormaMod;
import forme.PrikazProdavacaForma;
import forme.PrikazRacunaForma;
import forme.model.ModelTabeleProdavac;
import forme.model.ModelTabeleRacuni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kupac;
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
        prf.detaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Morate odabrati račun!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                       // if (true) throw new Exception("Veštačka greška");
                        ModelTabeleRacuni mtr = (ModelTabeleRacuni) prf.getjTableRacuni().getModel();
                        Racun r = mtr.getLista().get(red);

                        JOptionPane.showMessageDialog(prf, "Sistem je našao račun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        Cordinator.getInstance().dodajParam("racunZaDetalje", r);
                        Cordinator.getInstance().otvoriGlavnuFormu(FormaMod.DETALJI);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        prf.azurirajRacunAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Morate odabrati račun!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleRacuni mtr = (ModelTabeleRacuni) prf.getjTableRacuni().getModel();
                        Racun r = mtr.getLista().get(red); // ovde imam samo racun nemam stavke

                        JOptionPane.showMessageDialog(prf, "Sistem je nasao racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        Cordinator.getInstance().dodajParam("racunZaIzmenu", r);
                        Cordinator.getInstance().otvoriGlavnuFormu(FormaMod.PROMENI);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        prf.pretraziRacunAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Racun racun = new Racun();

                    if (prf.getjTextFieldRacunID().getText().isEmpty() && prf.getjTextFieldUkupanIznos().getText().isEmpty()
                            && (prf.getjTextFieldGodina().getText().isEmpty() || prf.getjTextFieldMesec().getText().isEmpty() || prf.getjTextFieldDan().getText().isEmpty())
                            && prf.getjComboBoxKupci().getSelectedItem() == null && prf.getjComboBoxProdavci().getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(prf, "Morate uneti barem jedan kriterijum", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        if (!prf.getjTextFieldRacunID().getText().isEmpty()) {
                            racun.setRacunID(Integer.parseInt(prf.getjTextFieldRacunID().getText()));
                        }

                        if (!prf.getjTextFieldUkupanIznos().getText().isEmpty()) {
                            racun.setUkupanIznos(Double.parseDouble(prf.getjTextFieldUkupanIznos().getText()));
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(prf, "Neispravan format numeričkih vrednosti", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!prf.getjTextFieldGodina().getText().isEmpty() && !prf.getjTextFieldMesec().getText().isEmpty() && !prf.getjTextFieldDan().getText().isEmpty()) {
                        String godina = prf.getjTextFieldGodina().getText();
                        String mesec = prf.getjTextFieldMesec().getText();
                        String dan = prf.getjTextFieldDan().getText();

                        String datumString = godina + "-" + mesec + "-" + dan;
                        LocalDate localDate = LocalDate.parse(datumString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Date datum = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        racun.setDatum(datum);

                    }

                    racun.setProdavacID((Prodavac) prf.getjComboBoxProdavci().getSelectedItem());
                    racun.setKupacID((Kupac) prf.getjComboBoxKupci().getSelectedItem());
                    List<Racun> racuni = Komunikacija.getInstance().pretraziRacune(racun);
                    if (racuni.isEmpty()) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    ModelTabeleRacuni modelTabeleRacuni = (ModelTabeleRacuni) prf.getjTableRacuni().getModel();
                    modelTabeleRacuni.setLista(racuni);
                    modelTabeleRacuni.fireTableDataChanged();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        prf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                prf.getjTextFieldRacunID().setText("");
                prf.getjTextFieldDan().setText("");
                prf.getjTextFieldMesec().setText("");
                prf.getjTextFieldGodina().setText("");
                prf.getjTextFieldUkupanIznos().setText("");

            }
        });
    }

    private void addMouseListener() {
        prf.getjTableRacuni().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // gde je kliknut mis
                int red = prf.getjTableRacuni().getSelectedRow();
                if (red != -1) {

                    try {
                        ModelTabeleRacuni mtr = (ModelTabeleRacuni) prf.getjTableRacuni().getModel();
                        Racun racun = mtr.getLista().get(red);
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
    }

    private void pripremiFormu() {
        try {
            List<Racun> racuni = komunikacija.Komunikacija.getInstance().ucitajRacune();
            ModelTabeleRacuni mtr = new ModelTabeleRacuni(racuni);
            prf.getjTableRacuni().setModel(mtr);
            List<Prodavac> sviProdavci = komunikacija.Komunikacija.getInstance().ucitajProdavce();
            prf.getjComboBoxProdavci().removeAllItems();

            for (Prodavac p : sviProdavci) {
                prf.getjComboBoxProdavci().addItem(p);
            }
            prf.getjComboBoxProdavci().setSelectedItem(null);

            List<Kupac> sviKupci = komunikacija.Komunikacija.getInstance().ucitajKupce();
            prf.getjComboBoxKupci().removeAllItems();
            for (Kupac k : sviKupci) {
                prf.getjComboBoxKupci().addItem(k);
            }
            prf.getjComboBoxKupci().setSelectedItem(null);
        } catch (Exception ex) {
            Logger.getLogger(PrikazProdavacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
