/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import model.Prodavac;

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
        
    }

    public void otvoriFormu() {
        Prodavac ulogovani=cordinator.Cordinator.getInstance().getUlogovaniProdavac();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(ulogovani.getImePrezime());
    }

}
