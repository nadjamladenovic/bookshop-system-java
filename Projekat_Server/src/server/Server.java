/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Nadja
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> kupci;

    public Server() {
        kupci = new ArrayList<>();
    }

    @Override
    public void run() { //pokreni server
        try {
            serverSocket = new ServerSocket(9000);
            while (!kraj) {
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                kupci.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : kupci) {
                k.prekini();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
