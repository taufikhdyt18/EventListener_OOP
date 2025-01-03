package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Mahasiswa;

public class FormMahasiswa extends JFrame {
    private JTextField txtId;
    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtJurusan;
    private JTextField txtAngkatan;  // Mengubah txtAlamat menjadi txtAngkatan
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JTable tableMahasiswa;
    private DefaultTableModel tableModel;
    
    public FormMahasiswa() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Form Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtNim = new JTextField();
        txtNama = new JTextField();
        txtJurusan = new JTextField();
        txtAngkatan = new JTextField();  // Menggunakan txtAngkatan untuk angkatan
        
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("NIM:"));
        formPanel.add(txtNim);
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(txtNama);
        formPanel.add(new JLabel("Jurusan:"));
        formPanel.add(txtJurusan);
        formPanel.add(new JLabel("Angkatan:"));  // Mengubah label alamat menjadi angkatan
        formPanel.add(txtAngkatan);  // Mengubah kolom untuk angkatan
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Table
        String[] columns = {"ID", "NIM", "Nama", "Jurusan", "Angkatan"};  // Mengubah kolom alamat menjadi angkatan
        tableModel = new DefaultTableModel(columns, 0);
        tableMahasiswa = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        
        // Add components to frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void addSaveListener(ActionListener listener) {
        btnSave.addActionListener(listener);
    }
    
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public Mahasiswa getMahasiswa() {
        Mahasiswa m = new Mahasiswa();
        m.setId(txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText()));
        m.setNim(txtNim.getText());
        m.setNama(txtNama.getText());
        m.setJurusan(txtJurusan.getText());
        m.setAngkatan(txtAngkatan.getText());  // Mengganti penggunaan txtAlamat menjadi txtAngkatan
        return m;
    }
    
    public void setMahasiswas(List<Mahasiswa> mahasiswas) {
        tableModel.setRowCount(0);
        for (Mahasiswa m : mahasiswas) {
            tableModel.addRow(new Object[]{
                m.getId(),
                m.getNim(),
                m.getNama(),
                m.getJurusan(),
                m.getAngkatan()  // Mengganti kolom alamat menjadi angkatan
            });
        }
    }
    
    public void clearForm() {
        txtId.setText("");
        txtNim.setText("");
        txtNama.setText("");
        txtJurusan.setText("");
        txtAngkatan.setText("");  // Mengganti penggunaan txtAlamat menjadi txtAngkatan
    }
    
    public int getSelectedMahasiswaId() {
        int row = tableMahasiswa.getSelectedRow();
        return row >= 0 ? (Integer) tableModel.getValueAt(row, 0) : 0;
    }
}
