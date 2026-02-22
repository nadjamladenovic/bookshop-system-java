/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author Nadja
 */
public interface DbRepository<T> extends Repository<T> { // obican repos se odnosi na odr objekat u bazi
    // DbRepos u njemu se nalaze metode koje mogu da radim sa bazom
    // povezi, raskini vezu, commit , rollback

    default public void connect() throws Exception {
        DbConnectionFactory.getInstance().getConnection();

    }

    default public void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();

    }
}
