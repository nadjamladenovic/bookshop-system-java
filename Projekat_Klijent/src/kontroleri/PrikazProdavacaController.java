/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.PrikazProdavacaForma;
import forme.model.ModelTabeleProdavac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class PrikazProdavacaController {

    private final PrikazProdavacaForma ppf;

    public PrikazProdavacaController(PrikazProdavacaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }
//metoda za dugme

    private void addActionListeners() {
        ppf.addBtnobrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTableProdavci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Niste selektovali red, sistem ne moze da obrise prodavca","UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    ModelTabeleProdavac mtp = (ModelTabeleProdavac) ppf.getjTableProdavci().getModel();
                    Prodavac p = mtp.getLista().get(red); // uzimamo prodavca koji je selektovan

                    try {
                        Komunikacija.getInstance().obrisiProdavca(p);
                        JOptionPane.showMessageDialog(ppf, "sistem je uspesno obrisao prodavca",
                                "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); // osvezavanje tabele
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(ppf, "sistem ne moze da obrise prodavca",
                                "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private void pripremiFormu() {
        try {
            List<Prodavac> prodavci = komunikacija.Komunikacija.getInstance().ucitajProdavce();
            ModelTabeleProdavac mtp = new ModelTabeleProdavac(prodavci);
            ppf.getjTableProdavci().setModel(mtp);
        } catch (Exception ex) {
            Logger.getLogger(PrikazProdavacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
