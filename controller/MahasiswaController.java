package controller;

import model.*;
import view.FormMahasiswa;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class MahasiswaController {
    private MahasiswaModel model;
    private FormMahasiswa view;
    
    public MahasiswaController(MahasiswaModel model, FormMahasiswa view) {
        this.model = model;
        this.view = view;
        
        // Initialize view event handlers
        this.view.addSaveListener(e -> saveMahasiswa());
        this.view.addDeleteListener(e -> deleteMahasiswa());
        this.view.addClearListener(e -> clearForm());
        
        refreshTable();
    }
    
    private void saveMahasiswa() {
        try {
            Mahasiswa mahasiswa = view.getMahasiswa();
            if (mahasiswa.getId() == 0) {
                model.insert(mahasiswa);
            } else {
                model.update(mahasiswa);
            }
            clearForm();
            refreshTable();
            JOptionPane.showMessageDialog(view, "Data saved successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error saving data: " + ex.getMessage());
        }
    }
    
    private void deleteMahasiswa() {
        try {
            int id = view.getSelectedMahasiswaId();
            if (id > 0) {
                if (model.delete(id)) {
                    clearForm();
                    refreshTable();
                    JOptionPane.showMessageDialog(view, "Data deleted successfully!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error deleting data: " + ex.getMessage());
        }
    }
    
    private void clearForm() {
        view.clearForm();
    }
    
    private void refreshTable() {
        try {
            List<Mahasiswa> mahasiswas = model.findAll();
            view.setMahasiswas(mahasiswas);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error loading data: " + ex.getMessage());
        }
    }
}
