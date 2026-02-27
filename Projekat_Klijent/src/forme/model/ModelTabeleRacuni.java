/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Racun;

/**
 *
 * @author Nadja
 */
public class ModelTabeleRacuni extends AbstractTableModel {

    List<Racun> lista;
    String[] kolone = {"ID", "Datum izdavanja", "Ukupan iznos", "Status", "Prodavac", "Kupac"};

    public ModelTabeleRacuni(List<Racun> lista) {
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
        
        Racun r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getRacunID();
            case 1:
                return r.getDatum();
            case 2:
                return r.getUkupanIznos();
            case 3:
                return r.getStatus();
            case 4:
                return r.getProdavacID().getImePrezime();
            case 5:
                return r.getKupacID().getImePrezime();
            default:
                return "NA";
        }
    }

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(List<Racun> lista) {
        this.lista = lista;
    }

}
