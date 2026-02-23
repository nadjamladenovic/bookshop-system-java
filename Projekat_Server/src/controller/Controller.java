/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Prodavac;
import operacija.login.LoginOperacija;

/**
 *
 * @author Nadja
 */
public class Controller {

    private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Prodavac login(Prodavac p) throws Exception {
          //da proveri dalje u bazi  
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(p, null);
        System.out.println("KLASA CONTROLLER login: " + operacija.getProdavac());
        
        return operacija.getProdavac(); // vraca ga dalje u OKZ
    }
}
