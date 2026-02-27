/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Grad;
import model.Kupac;

/**
 *
 * @author Nadja
 */
public class ModelTabeleKupci extends AbstractTableModel {

    List<Kupac> lista;
    String[] kolone = {"kupacID", "imePrezime", "email", "brojTelefona", "adresa", "nazivGrada"};

    public ModelTabeleKupci(List<Kupac> lista) {
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
        Kupac kupac = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kupac.getKupacID();
            case 1:
                return kupac.getImePrezime();
            case 2:
                return kupac.getEmail();
            case 3:
                return kupac.getBrojtelefona();

            case 4:
                return kupac.getAdresa();
            case 5:
                return kupac.getGradID().getNazivGrada();
            default:
                return "NA";
        }
    }

    public List<Kupac> getLista() {
        return lista;
    }

    public void setLista(List<Kupac> lista) {
        this.lista = lista;
    }

    public void pretrazi(String imePrezime, String email, String brojTelefona, String adresa, Grad grad) {

        List<Kupac> filteredList = lista.stream()
                .filter(v -> (imePrezime == null || imePrezime.isEmpty() || v.getImePrezime().toLowerCase().contains(imePrezime.toLowerCase())))
                .filter(v -> (email == null || email.isEmpty() || v.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(v -> (brojTelefona == null || brojTelefona.isEmpty() || v.getBrojtelefona().contains(brojTelefona)))
                .filter(v -> (adresa == null || adresa.isEmpty() || v.getAdresa().toLowerCase().contains(adresa.toLowerCase())))
                .filter(v -> (grad == null
                || v.getGradID() != null
                && v.getGradID().getNazivGrada().toLowerCase().contains(grad.getNazivGrada().toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filteredList;
        fireTableDataChanged();
    }
}
