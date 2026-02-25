/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class LoginController {

    // treba da bude zaduzen za sve sto treba da se prikaze na formi, kontroler treba da kaze formi
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {

        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void prijava(ActionEvent e) throws Exception {
                try {
                    String username = lf.getjTextFieldUsername().getText().trim();            // ova dva podatka saljem na serversku stranu
                    String password = String.valueOf(lf.getjPasswordField().getPassword()).trim();

                    // zahtev na serversku stranu preko komunikacije
                    Komunikacija.getInstance().konekcija();
                    Prodavac ulogovani = Komunikacija.getInstance().login(username, password); // ako mi vrati null nije ulogovan
                    if (ulogovani == null) {
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni", "GRESKA", JOptionPane.ERROR_MESSAGE);

                    } else {
                        cordinator.Cordinator.getInstance().setUlogovaniProdavac(ulogovani);
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        cordinator.Cordinator.getInstance().otvoriGlavnuFormu();
                        lf.dispose();

                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
