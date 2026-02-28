/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import cordinator.Cordinator;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Grad;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.ProdavacRS;
import model.Racun;
import model.RadnaSmena;

/**
 *
 * @author Nadja
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() throws IOException {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException e) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Prodavac login(String username, String password) throws Exception {
        Prodavac prodavac = new Prodavac();
        prodavac.setKorisnickoIme(username);
        prodavac.setLozinka(password);

        Zahtev zahtev = new Zahtev(Operacija.LOGIN, prodavac);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        //System.out.println("Podatak u odgovoru je: " + odg.getOdgovor().getClass().getName());
        // Provera: ako je odgovor null, znaci da login nije uspeo (pogresni podaci)
        if (odg.getOdgovor() == null) {
            System.out.println("Login neuspešan: Prodavac nije pronađen.");
            return null;
        }
        //Provera da li je stigao String umesto Prodavca
        if (odg.getOdgovor() instanceof String) {
            // Ako je server poslao poruku o grešci kao String, baci exception sa tim tekstom
            throw new Exception((String) odg.getOdgovor());
        }
        return (Prodavac) odg.getOdgovor();
    }

    public List<Prodavac> ucitajProdavce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PRODAVCE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        return (List<Prodavac>) odg.getOdgovor();
    }

    public void obrisiProdavca(Prodavac p) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.OBRISI_PRODAVCA, p);
    }

    public void dodajProdavca(Prodavac p) throws Exception {
        // posaljiZahtevSaExceptionom(Operacija.DODAJ_PRODAVCA, p);
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_PRODAVCA, p);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("GRESKA");
        } else {
            System.out.println("USPEH");
        }
    }

    public void azurirajProdavca(Prodavac p) throws Exception {
        // 1. Kreiramo zahtev za ažuriranje
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_PRODAVCA, p);

        // 2. Šaljemo ga serveru preko pošiljaoca
        posiljalac.posalji(zahtev);

        // 3. Čekamo odgovor (ovo je ključno - program ovde stoji dok server ne odgovori)
        Odgovor odg = (Odgovor) primalac.primi();

        // 4. Proveravamo da li je odgovor stigao (kao što radiš u dodajProdavca)
        if (odg.getOdgovor() == null) {
            // Ako je null, znači da server nije vratio potvrdu o uspehu
            System.out.println("GRESKA: Prodavac NIJE ažuriran u bazi.");
            // Ovde možeš dodati i neku poruku korisniku (JOptionPane npr.)
        } else {
            // Ako nije null, operacija je prošla na serveru
            System.out.println("USPEH: Prodavac je uspešno ažuriran.");
            Cordinator.getInstance().osveziFormu();
        }

    }

    private void posaljiZahtevSaExceptionom(Operacija operacija, Object param) throws Exception {
        Zahtev zahtev = new Zahtev(operacija, param);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw (odg.getOdgovor() instanceof Exception)
                    ? (Exception) odg.getOdgovor()
                    : new Exception((String) odg.getOdgovor());
        }
    }

    

    public List<Kupac> ucitajKupce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        return (List<Kupac>) odg.getOdgovor();
    }

    public void PromeniKupca(Kupac k) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.PROMENI_KUPCA, k);
        Cordinator.getInstance().osveziFormu();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.OBRISI_KUPCA, k);
    }

    public void dodajKupca(Kupac k) throws Exception {
        /*
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KUPCA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("GRESKA");
        } else {
            System.out.println("USPEH");
        }*/
        posaljiZahtevSaExceptionom(Operacija.DODAJ_KUPCA, k);
    }

    public List<Knjiga> ucitajKnjige() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KNJIGE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        return (List<Knjiga>) odg.getOdgovor();
    }

    public List<RadnaSmena> vratiSmene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SMENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        return (List<RadnaSmena>) odg.getOdgovor();
    }

    public List<Grad> ucitajGrad() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_GRAD, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        return (List<Grad>) odg.getOdgovor();
    }

    public void UbaciProdavacSmena(ProdavacRS smena) throws Exception {
         posaljiZahtevSaExceptionom(Operacija.UBACI_SMENU, smena);
    }
    public List<Racun> ucitajRacune() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            return new ArrayList<>();
        }
        // DODAJ OVU PROVERU DA VIDIŠ ŠTA SERVER ŠALJE
        if (odg.getOdgovor() instanceof String) {
            System.err.println("SERVER VRATIO GREŠKU UMESTO LISTE: " + odg.getOdgovor());
            return new ArrayList<>(); // Ili baci Exception
        }
        return (List<Racun>) odg.getOdgovor();
    }

    public List<Racun> pretraziRacune(Racun racun) {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUN, racun);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) return new ArrayList<>();
        return (List<Racun>) odg.getOdgovor();
    }
}
