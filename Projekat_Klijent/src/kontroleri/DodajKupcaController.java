/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajKupcaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Grad;
import model.Kupac;

/**
 *
 * @author Nadja
 */
public class DodajKupcaController {

    private final DodajKupcaForma dkf;

    public DodajKupcaController(DodajKupcaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKupca(e);
            }

            private void dodajKupca(ActionEvent e) {
                String imePrezime = dkf.getjTextFieldImePrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                String brojTelefona = dkf.getjTextFieldBrojTelefona().getText().trim();
                String adresa = dkf.getjTextFieldAdresa().getText().trim();
                Grad grad = (Grad) dkf.getjComboBoxGrad().getSelectedItem();

                if (imePrezime.equals("") || email.equals("") || brojTelefona.equals("") || adresa.equals("") || grad.equals("")) {
                    JOptionPane.showMessageDialog(dkf, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Kupac k = new Kupac(-1, imePrezime, email, brojTelefona, adresa, grad);
                try {
                    Komunikacija.getInstance().dodajKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je uspeo da doda kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem nije uspeo da doda kupca", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni(e);
            }

            private void promeni(ActionEvent e) {
                String imePrezime = dkf.getjTextFieldImePrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                String brojTelefona = dkf.getjTextFieldBrojTelefona().getText().trim();
                String adresa = dkf.getjTextFieldAdresa().getText().trim();
                Grad grad = (Grad) dkf.getjComboBoxGrad().getSelectedItem();

                Kupac k = new Kupac(-1, imePrezime, email, brojTelefona, adresa, grad);
                try {
                    Komunikacija.getInstance().PromeniKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dkf, exp.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                String imePrezime = dkf.getjTextFieldImePrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                String brojTelefona = dkf.getjTextFieldBrojTelefona().getText().trim();
                String adresa = dkf.getjTextFieldAdresa().getText().trim();
                Grad grad = (Grad) dkf.getjComboBoxGrad().getSelectedItem();

                Kupac k = new Kupac(-1, imePrezime, email, brojTelefona, adresa, grad);

                try {
                    Komunikacija.getInstance().obrisiKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je obrisao kupca", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception exp) {        // exp.getMessage()
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da obrise kupca", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        dkf.getjComboBoxGrad().removeAllItems();
        List<Grad> grad = komunikacija.Komunikacija.getInstance().ucitajGrad();
        for (Grad g : grad) {
            dkf.getjComboBoxGrad().addItem(g);
        }

        switch (mod) {
            case DODAJ:
                dkf.getjButtonPromeni().setVisible(false);
                dkf.getjButtonObrisi().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                dkf.getjComboBoxGrad().setSelectedItem(null);
                break;

            case PROMENI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonObrisi().setVisible(false);
                dkf.getjButtonPromeni().setVisible(true);

                Kupac kupacPromeni = (Kupac) Cordinator.getInstance().vratiParam("kupac");
                dkf.getjTextFieldImePrezime().setText(kupacPromeni.getImePrezime());
                dkf.getjTextFieldEmail().setText(kupacPromeni.getEmail());
                dkf.getjTextFieldBrojTelefona().setText(kupacPromeni.getBrojtelefona());
                dkf.getjTextFieldAdresa().setText(kupacPromeni.getAdresa());
                dkf.getjComboBoxGrad().setSelectedItem(kupacPromeni.getGradID());
                dkf.getjTextFieldIDKupca().setText(kupacPromeni.getKupacID() + "");
                dkf.getjTextFieldIDKupca().setEditable(false);
                break;

            case OBRISI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonPromeni().setVisible(false);
                dkf.getjButtonObrisi().setVisible(true);

                Kupac kupacObrisi = (Kupac) Cordinator.getInstance().vratiParam("kupac");
                dkf.getjTextFieldImePrezime().setText(kupacObrisi.getImePrezime());
                dkf.getjTextFieldEmail().setText(kupacObrisi.getEmail());
                dkf.getjTextFieldBrojTelefona().setText(kupacObrisi.getBrojtelefona());
                dkf.getjTextFieldAdresa().setText(kupacObrisi.getAdresa());
                dkf.getjComboBoxGrad().setSelectedItem(kupacObrisi.getGradID());
                dkf.getjTextFieldIDKupca().setText(kupacObrisi.getKupacID() + "");

                dkf.getjTextFieldImePrezime().setEditable(false);
                dkf.getjTextFieldEmail().setEditable(false);
                dkf.getjTextFieldBrojTelefona().setEditable(false);
                dkf.getjTextFieldAdresa().setEditable(false);
                dkf.getjComboBoxGrad().setEnabled(false);
                dkf.getjTextFieldIDKupca().setEditable(false);
                break;

            case DETALJI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonPromeni().setVisible(false);
                dkf.getjButtonObrisi().setVisible(false);

                Kupac kupacDetalji = (Kupac) Cordinator.getInstance().vratiParam("kupac");
                dkf.getjTextFieldImePrezime().setText(kupacDetalji.getImePrezime());
                dkf.getjTextFieldEmail().setText(kupacDetalji.getEmail());
                dkf.getjTextFieldBrojTelefona().setText(kupacDetalji.getBrojtelefona());
                dkf.getjTextFieldAdresa().setText(kupacDetalji.getAdresa());
                dkf.getjComboBoxGrad().setSelectedItem(kupacDetalji.getGradID());
                dkf.getjTextFieldIDKupca().setText(kupacDetalji.getKupacID() + "");

                dkf.getjTextFieldImePrezime().setEditable(false);
                dkf.getjTextFieldEmail().setEditable(false);
                dkf.getjTextFieldBrojTelefona().setEditable(false);
                dkf.getjTextFieldAdresa().setEditable(false);
                dkf.getjComboBoxGrad().setEnabled(false);
                dkf.getjTextFieldIDKupca().setEditable(false);
                break;

            default:
                throw new AssertionError();
        }
    }
}
