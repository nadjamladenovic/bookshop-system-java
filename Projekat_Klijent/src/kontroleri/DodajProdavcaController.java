/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajProdavcaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class DodajProdavcaController {
    private final DodajProdavcaForma dpf;

    public DodajProdavcaController(DodajProdavcaForma dpf) {
        this.dpf = dpf;
        addActionListener();
    }

    private void addActionListener() {
        dpf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajProdavca(e);
            }

            private void dodajProdavca(ActionEvent e) {
                String imePrezime = dpf.getjTextFieldIme().getText().trim();
                String email = dpf.getjTextFieldEmail().getText().trim();
                String korisnickoIme = dpf.getjTextFieldKorisnickoIme().getText().trim();
                String lozinka = dpf.getjTextFieldLozinka().getText().trim();
                
                if(imePrezime.equals("") || email.equals("") || korisnickoIme.equals("") || lozinka.equals("")){
                    JOptionPane.showMessageDialog(dpf, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Prodavac p = new Prodavac(-1, imePrezime, email, korisnickoIme, lozinka);
                try {
                    Komunikacija.getInstance().dodajProdavca(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je uspeo da doda prodavca", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem nije uspeo da doda prodavca", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dpf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                azuriraj(e);
            }

            private void azuriraj(ActionEvent e) {
                int id = Integer.parseInt(dpf.getjTextFieldID().getText());
                String imePrezime = dpf.getjTextFieldIme().getText().trim();
                String email = dpf.getjTextFieldEmail().getText().trim();
                String korisnickoIme = dpf.getjTextFieldKorisnickoIme().getText().trim();
                String lozinka = dpf.getjTextFieldLozinka().getText().trim();

                Prodavac p = new Prodavac(id,imePrezime, email, korisnickoIme, lozinka);
                try {
                    Komunikacija.getInstance().azurirajProdavca(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je uspeo da azurira prodavca", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog(dpf, "Sistem nije uspeo da azurira prodavca", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dpf.getjButtonAzuriraj().setVisible(false);
                dpf.getjButtonDodaj().setVisible(true); 
                dpf.getjButtonDodaj().setEnabled(true);
                dpf.getjTextFieldID().setEnabled(false);
                break;
                
            case PROMENI:
                dpf.getjButtonDodaj().setVisible(false);
                dpf.getjButtonAzuriraj().setVisible(true);
                dpf.getjButtonAzuriraj().setEnabled(true);
                dpf.getjTextFieldID().setEnabled(true);
                Prodavac p = (Prodavac) Cordinator.getInstance().vratiParam("prodavac"); 
                dpf.getjTextFieldID().setText(String.valueOf(p.getProdavacID()));
                dpf.getjTextFieldIme().setText(p.getImePrezime());
                dpf.getjTextFieldEmail().setText(p.getEmail());
                dpf.getjTextFieldKorisnickoIme().setText(p.getKorisnickoIme());
                dpf.getjTextFieldLozinka().setText(p.getLozinka());
                break;
                
            default:
                throw new AssertionError();
        }
    }
}
