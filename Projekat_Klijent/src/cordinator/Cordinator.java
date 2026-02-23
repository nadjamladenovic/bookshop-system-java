package cordinator;

import forme.LoginForma;
import kontroleri.LoginController;



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
    private LoginController loginController;


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
    
}
