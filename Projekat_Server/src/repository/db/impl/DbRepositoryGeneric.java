/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.ApstraktniDomenskiObjekat;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Nadja
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        // TREBA DA VRATI SVE EL LISTE, TABELE
        //select * from kupac
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement(); // izvrsavanje upita
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;

    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        //INSERT INTO KUPAC (IME,PRERZIME) VALUES ('Nadja','M')
        /* String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()
                + ") VALUES ( " + param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println("IZVRŠAVAM UPIT: " + upit); // Ovo moraš videti u konzoli!
        //System.out.println(upit);
        /* Statement st = DbConnectionFactory.getInstance().getConnection().createStatement(); // vraca br redova
        st.executeUpdate(upit);
        st.close();
         */
 /* Connection conn = DbConnectionFactory.getInstance().getConnection();
        Statement st = conn.createStatement();
        int uspeh = st.executeUpdate(upit);

        System.out.println("BROJ DODATIH REDOVA: " + uspeh); // Ako je 0, upit nije prošao. Ako je 1, prošao je!
        st.close();*/
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()
                + ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";

        // ISPIŠI UPIT DA VIDIŠ ŠTA TAČNO IDE U BAZU
        System.out.println("DEBUG BROKER: " + upit);

        // OVO JE KLJUČNO: Uzimaš konekciju koja je već u transakciji
        Connection conn = DbConnectionFactory.getInstance().getConnection();

        Statement st = conn.createStatement();
        int rows = st.executeUpdate(upit);

        System.out.println("DEBUG BROKER: Dodato redova: " + rows);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu()
                + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement(); // vraca br redova
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement(); // vraca br redova
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        // TREBA DA VRATI SVE EL LISTE, TABELE
        //select * from kupac
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM";

        return lista;
    }

    @Override
    public int addReturnKey(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele()
                + " (" + param.vratiKoloneZaUbacivanje()
                + ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";

        PreparedStatement ps = DbConnectionFactory.getInstance()
                .getConnection()
                .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS); // on vraca id od novog objekta

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        rs.close();
        ps.close();

        return generatedId;
    }

}
