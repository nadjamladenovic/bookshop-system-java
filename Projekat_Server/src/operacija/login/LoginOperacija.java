/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import java.util.List;
import model.Prodavac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nadja
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Prodavac prodavac;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        // iz DBB da ucita sve grumere i da ja prodjem kroz tu listu i vidim da li postoji grumer sa prosledjenim param
        List<Prodavac> sviProdavci = broker.getAll((Prodavac) param, "");
        System.out.println("Klasa LoginOperacija " + sviProdavci);

        if (sviProdavci.contains((Prodavac) param)) {
            for (Prodavac p : sviProdavci) {
                if (p.equals((Prodavac) param)) {
                    prodavac = p;
                    return;
                }
            }
        } else {
            prodavac = null;
        }
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

}
