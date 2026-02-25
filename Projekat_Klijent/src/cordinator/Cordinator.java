package cordinator;

import forme.DodajProdavcaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazProdavacaForma;
import kontroleri.DodajProdavcaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazProdavacaController;
import model.Prodavac;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nadja
 */
public class Cordinator {

    // ovde cuvam sve informacije koje su mi znacajne npr ko je ulogovani grumer
    private static Cordinator instance;
    private Prodavac ulogovaniProdavac;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazProdavacaController ppController;
    private DodajProdavcaController dpController;

    public Cordinator() {

    }

    public static Cordinator getInstance() {
        if (instance == null) {
            instance = new Cordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriPrikazProdavacaFormu() {
        ppController = new PrikazProdavacaController(new PrikazProdavacaForma());
        ppController.otvoriFormu();
    }

    public Prodavac getUlogovaniProdavac() {
        return ulogovaniProdavac;
    }

    public void setUlogovaniProdavac(Prodavac ulogovaniProdavac) {
        this.ulogovaniProdavac = ulogovaniProdavac;
    }

    public void otvoriDodajProdavcaFormu() {
        dpController = new DodajProdavcaController(new DodajProdavcaForma());
        dpController.otvoriFormu();
    }

}
