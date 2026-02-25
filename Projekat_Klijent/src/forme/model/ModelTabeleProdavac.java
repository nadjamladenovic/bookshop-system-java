/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Prodavac;

/**
 *
 * @author Nadja
 */
public class ModelTabeleProdavac extends AbstractTableModel{
    List<Prodavac> lista;
    String []kolone={"ID","Ime i prezime","Email","Korisnicko ime"};

    public ModelTabeleProdavac(List<Prodavac> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public int getRowCount() {
       return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodavac p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getProdavacID();
            case 1:
                return p.getImePrezime();
            case 2:
                return p.getEmail();
            case 3:
                return p.getKorisnickoIme();
            default:
                return "NA";
        }
    }

    public List<Prodavac> getLista() {
        return lista;
    }

    public void setLista(List<Prodavac> lista) {
        this.lista = lista;
    }

    public void pretrazi(String imePrezime, String email) {
        List<Prodavac> filteredList = lista.stream()
                .filter(p -> (imePrezime == null || imePrezime.isEmpty() || p.getImePrezime().toLowerCase().contains(imePrezime.toLowerCase())))
                .filter(p -> (email == null || email.isEmpty() || p.getEmail().toLowerCase().contains(email.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}
