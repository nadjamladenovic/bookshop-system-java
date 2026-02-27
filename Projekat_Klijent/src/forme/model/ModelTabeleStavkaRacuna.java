/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaRacuna;

/**
 *
 * @author Nadja
 */
public class ModelTabeleStavkaRacuna extends AbstractTableModel {

    List<StavkaRacuna> lista;
    String[] kolone = {"racunID", "rb", "iznos", "kolicina", "cena", "Naziv knjige","Autor knjige"};

    public ModelTabeleStavkaRacuna(List<StavkaRacuna> lista) {
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
        StavkaRacuna sR = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sR.getRacunID();
            case 1:
                return sR.getRb();
            case 2:
                return sR.getIznos();
            case 3:
                return sR.getKolicina();
            case 4:
                return sR.getCena();
            case 5:
                return sR.getKnjigaID().getNaziv();
            case 6:
                return sR.getKnjigaID().getAutor();
            default:
                return "NA";
        }
    }
    public void dodajStavku(StavkaRacuna sr) {
        int trRb=lista.size()+1;
        sr.setRb(trRb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaRacuna s) {
        lista.remove(s);
        fireTableDataChanged();

    }

}
