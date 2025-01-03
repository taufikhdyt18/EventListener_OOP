package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Nilai;

public class FormNilai extends JFrame {
    private JTextField txtId;
    private JTextField txtMahasiswaId;
    private JTextField txtMataKuliah;
    private JTextField txtSemester;
    private JTextField txtNilaiTugas;
    private JTextField txtNilaiUts;
    private JTextField txtNilaiUas;
    private JTextField txtNilaiAkhir;
    private JTextField txtGrade;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JTable tableNilai;
    private DefaultTableModel tableModel;
    
    public FormNilai() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Form Nilai Mahasiswa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtMahasiswaId = new JTextField();
        txtMataKuliah = new JTextField();
        txtSemester = new JTextField();
        txtNilaiTugas = new JTextField();
        txtNilaiUts = new JTextField();
        txtNilaiUas = new JTextField();
        txtNilaiAkhir = new JTextField();
        txtNilaiAkhir.setEditable(false);
        txtGrade = new JTextField();
        txtGrade.setEditable(false);
        
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Mahasiswa ID:"));
        formPanel.add(txtMahasiswaId);
        formPanel.add(new JLabel("Mata Kuliah:"));
        formPanel.add(txtMataKuliah);
        formPanel.add(new JLabel("Semester:"));
        formPanel.add(txtSemester);
        formPanel.add(new JLabel("Nilai Tugas:"));
        formPanel.add(txtNilaiTugas);
        formPanel.add(new JLabel("Nilai UTS:"));
        formPanel.add(txtNilaiUts);
        formPanel.add(new JLabel("Nilai UAS:"));
        formPanel.add(txtNilaiUas);
        formPanel.add(new JLabel("Nilai Akhir:"));
        formPanel.add(txtNilaiAkhir);
        formPanel.add(new JLabel("Grade:"));
        formPanel.add(txtGrade);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Table
        String[] columns = {"ID", "Mahasiswa ID", "Mata Kuliah", "Semester", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Grade"};
        tableModel = new DefaultTableModel(columns, 0);
        tableNilai = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableNilai);
        
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
    
    public Nilai getNilai() {
        Nilai n = new Nilai();
        n.setId(txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText()));
        n.setMahasiswaId(Integer.parseInt(txtMahasiswaId.getText()));
        n.setMataKuliah(txtMataKuliah.getText());
        n.setSemester(Integer.parseInt(txtSemester.getText()));
        n.setNilaiTugas(Float.parseFloat(txtNilaiTugas.getText()));
        n.setNilaiUts(Float.parseFloat(txtNilaiUts.getText()));
        n.setNilaiUas(Float.parseFloat(txtNilaiUas.getText()));
        return n;
    }
    
    public void setNilaiList(List<Nilai> nilaiList) {
        tableModel.setRowCount(0);
        for (Nilai n : nilaiList) {
            tableModel.addRow(new Object[]{
                n.getId(),
                n.getMahasiswaId(),
                n.getMataKuliah(),
                n.getSemester(),
                n.getNilaiTugas(),
                n.getNilaiUts(),
                n.getNilaiUas(),
                n.getNilaiAkhir(),
                n.getGrade()
            });
        }
    }
    
    public void clearForm() {
        txtId.setText("");
        txtMahasiswaId.setText("");
        txtMataKuliah.setText("");
        txtSemester.setText("");
        txtNilaiTugas.setText("");
        txtNilaiUts.setText("");
        txtNilaiUas.setText("");
        txtNilaiAkhir.setText("");
        txtGrade.setText("");
    }
    
    public int getSelectedNilaiId() {
        int row = tableNilai.getSelectedRow();
        return row >= 0 ? (Integer) tableModel.getValueAt(row, 0) : 0;
    }
}