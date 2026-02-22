/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Nadja
 */
public interface Repository<T> {// parametar t kao da je genericiki
    // u njega treba da se smeste sve metode, operacije koje cu moci da radim nad bilo kojim entitetom u bazi
    //CRUD operacije

    List<T> getAll(T param, String uslov) throws Exception; // pretraga

    void add(T param) throws Exception;

    void edit(T param) throws Exception;

    void delete(T param) throws Exception;

    List<T> getAll();

    int addReturnKey(T param) throws Exception; //ubacivanje el u bazu i vraca mi id tog el
}
