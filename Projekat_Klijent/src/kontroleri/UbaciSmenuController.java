/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.UbaciSmenaForma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;
import model.Prodavac;
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

    }
}
