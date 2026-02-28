package cordinator;

import forme.DodajKupcaForma;
import forme.DodajProdavcaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKupacaForma;
import forme.PrikazProdavacaForma;
import forme.PrikazRacunaForma;
import forme.UbaciSmenaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajKupcaController;
import kontroleri.DodajProdavcaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazKupacaController;
import kontroleri.PrikazProdavacaController;
import kontroleri.PrikazRacunaController;
import kontroleri.UbaciSmenuController;
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
    private Map<String, Object> parametri;
    private PrikazRacunaController prController;
    private UbaciSmenuController usController;
    private DodajKupcaController dkController;
    private PrikazKupacaController pkController;

    public Cordinator() {
        parametri = new HashMap<>();
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
        dpController.otvoriFormu(FormaMod.DODAJ);
    }
    // dodavanje parametara

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    // vracanje parametara
    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriIzmeniProdavcaFormu() {
        dpController = new DodajProdavcaController(new DodajProdavcaForma());
        dpController.otvoriFormu(FormaMod.PROMENI);
    }

    public void osveziFormu() {
        if (ppController != null) {
            ppController.osveziFormu();
        }
    }

    public void otvoriPrikazRacunaFormu() {
        prController = new PrikazRacunaController(new PrikazRacunaForma());
        prController.otvoriFormu();
    }

    public void otvoriUbaciSmenuForma() {
        usController = new UbaciSmenuController(new UbaciSmenaForma());
        usController.otvoriFormu();
    }

    public void otvoriDodajKupcaFormu() {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikazKupacaFormu() {
        pkController = new PrikazKupacaController(new PrikazKupacaForma());
        pkController.otvoriFormu();
        // registracija kontrolera
        setPrikazKupcaController(pkController);
    }

    public void otvoriDetaljiKupcaFormu() {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.DETALJI);
    }

    public void otvoriObrisiKupcaFormu() {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.OBRISI);
    }

    private void setPrikazKupcaController(PrikazKupacaController pkController) {
        this.pkController = pkController;
    }

    public void otvoriPromeniKupcaFormu() {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.PROMENI);
    }

    public void otvoriGlavnuFormu(FormaMod formaMod) {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu(formaMod);
    }
}
