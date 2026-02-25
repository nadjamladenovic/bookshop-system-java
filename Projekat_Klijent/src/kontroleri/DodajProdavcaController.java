/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DodajProdavcaForma;
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
    }
    public void otvoriFormu(){
        
        dpf.setVisible(true);
    }
}
