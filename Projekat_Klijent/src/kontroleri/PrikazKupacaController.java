/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazKupacaForma;
import forme.model.ModelTabeleKupci;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Grad;
import model.Kupac;

/**
 *
 * @author Nadja
 */
public class PrikazKupacaController {

    private final PrikazKupacaForma pkf;

    public PrikazKupacaController(PrikazKupacaForma pkf) {
        this.pkf = pkf;
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu(); // metoda koja ce da ucitava listu svih pacijenata
        pkf.setVisible(true);
        /// KLIJ ZAHTEV da ucita vlasnike iz baze
    }

    private void addActionListener() {
        pkf.addBtnDetaljiKupcaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate odabrati kupca", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleKupci mtk = (ModelTabeleKupci) pkf.getjTableKupci().getModel();
                        Kupac k = mtk.getLista().get(red);

                        JOptionPane.showMessageDialog(pkf, "Sistem je našao kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        Cordinator.getInstance().dodajParam("kupac", k);

                        Cordinator.getInstance().otvoriDetaljiKupcaFormu();

                    } catch (Exception ex) {
                        Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate odabrati kupca", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleKupci mtk = (ModelTabeleKupci) pkf.getjTableKupci().getModel();
                        Kupac k = mtk.getLista().get(red);

                        JOptionPane.showMessageDialog(pkf, "Sistem je našao kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        cordinator.Cordinator.getInstance().dodajParam("kupac", k);

                        Cordinator.getInstance().otvoriObrisiKupcaFormu();
                        pripremiFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        pkf.addBtnPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate odabrati kupca", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleKupci mtk = (ModelTabeleKupci) pkf.getjTableKupci().getModel();
                        Kupac k = mtk.getLista().get(red); // ovde uzimam pacijenta iz tabele i treba da ga posljem drugoj formi

                        JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        cordinator.Cordinator.getInstance().dodajParam("kupac", k);

                        Cordinator.getInstance().otvoriPromeniKupcaFormu();
                        pripremiFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezime = pkf.getjTextFieldImePrezime().getText().trim();
                String email = pkf.getjTextFieldEmail().getText().trim();
                String brojTelefona = pkf.getjTextFieldBrojTelefona().getText().trim();
                String adresa = pkf.getjTextFieldAdresa().getText().trim();
                Grad g = (Grad) pkf.getjComboBoxGradovi().getSelectedItem();

                if (imePrezime.equals("") && email.equals("") && brojTelefona.equals("") && adresa.equals("") && g == null) {
                    JOptionPane.showMessageDialog(pkf, "Morate uneti barem jedan kriterijum", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }

                ModelTabeleKupci mtk = (ModelTabeleKupci) pkf.getjTableKupci().getModel();
                mtk.pretrazi(imePrezime, email, brojTelefona, adresa, g);
                List<Kupac> lista = mtk.getLista();
                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje kupca po zadatim kriterjumima", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupce po zadatim kriterjumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                pkf.getjTextFieldImePrezime().setText("");
                pkf.getjTextFieldEmail().setText("");
                pkf.getjTextFieldBrojTelefona().setText("");
                pkf.getjTextFieldAdresa().setText("");
            }
        });
    }

    private void pripremiFormu() {
        try {
            // i za kombobox
            List<Kupac> kupci = komunikacija.Komunikacija.getInstance().ucitajKupce();
            ModelTabeleKupci mtk = new ModelTabeleKupci(kupci);
            pkf.getjTableKupci().setModel(mtk);

            List<Grad> grad = komunikacija.Komunikacija.getInstance().ucitajGrad();
            for (Grad g : grad) {
                pkf.getjComboBoxGradovi().addItem(g);
            }
            pkf.getjComboBoxGradovi().setSelectedItem(null);
        } catch (Exception ex) {
            Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void osveziFormu() {
        pripremiFormu();
    }

}
