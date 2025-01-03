package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;
import view.FormNilai;

public class NilaiController {
    private NilaiModel model;
    private FormNilai view;
    private MahasiswaModel mahasiswaModel;
    
    public NilaiController(NilaiModel model, MahasiswaModel mahasiswaModel, FormNilai view) {
        this.model = model;
        this.mahasiswaModel = mahasiswaModel;
        this.view = view;
        
        // Initialize view event handlers
        this.view.addSaveListener(e -> saveNilai());
        this.view.addDeleteListener(e -> deleteNilai());
        this.view.addClearListener(e -> clearForm());
        
        refreshTable();
    }
    
    private void saveNilai() {
        try {
            // Validate mahasiswa existence
            Nilai nilai = view.getNilai();
            Mahasiswa mahasiswa = mahasiswaModel.findById(nilai.getMahasiswaId());
            if (mahasiswa == null) {
                JOptionPane.showMessageDialog(view, "Mahasiswa ID tidak ditemukan!");
                return;
            }
            
            // Validate input values
            if (nilai.getNilaiTugas() < 0 || nilai.getNilaiTugas() > 100 ||
                nilai.getNilaiUts() < 0 || nilai.getNilaiUts() > 100 ||
                nilai.getNilaiUas() < 0 || nilai.getNilaiUas() > 100) {
                JOptionPane.showMessageDialog(view, "Nilai harus berada di antara 0-100!");
                return;
            }
            
            if (nilai.getId() == 0) {
                model.insert(nilai);
            } else {
                model.update(nilai);
            }
            clearForm();
            refreshTable();
            JOptionPane.showMessageDialog(view, "Data nilai berhasil disimpan!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Input nilai tidak valid! Pastikan semua field diisi dengan angka yang benar.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error menyimpan data: " + ex.getMessage());
        }
    }
    
    private void deleteNilai() {
        try {
            int id = view.getSelectedNilaiId();
            if (id > 0) {
                int confirm = JOptionPane.showConfirmDialog(view,
                    "Apakah Anda yakin ingin menghapus data nilai ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    if (model.delete(id)) {
                        clearForm();
                        refreshTable();
                        JOptionPane.showMessageDialog(view, "Data nilai berhasil dihapus!");
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error menghapus data: " + ex.getMessage());
        }
    }
    
    private void clearForm() {
        view.clearForm();
    }
    
    private void refreshTable() {
        try {
            List<Nilai> nilaiList = model.findAll();
            view.setNilaiList(nilaiList);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error memuat data: " + ex.getMessage());
        }
    }
    
    public void showForm() {
        view.setVisible(true);
    }
    
    public void loadNilaiMahasiswa(int mahasiswaId) {
        try {
            List<Nilai> nilaiList = model.findByMahasiswaId(mahasiswaId);
            view.setNilaiList(nilaiList);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error memuat data nilai mahasiswa: " + ex.getMessage());
        }
    }
}