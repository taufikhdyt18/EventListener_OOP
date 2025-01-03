package model;

public class Nilai {
    private int id;
    private int mahasiswaId;
    private String mataKuliah;
    private int semester;
    private float nilaiTugas;
    private float nilaiUts;
    private float nilaiUas;
    private float nilaiAkhir;
    private String grade;
    
    public Nilai() {}
    
    public Nilai(int id, int mahasiswaId, String mataKuliah, int semester,
                float nilaiTugas, float nilaiUts, float nilaiUas) {
        this.id = id;
        this.mahasiswaId = mahasiswaId;
        this.mataKuliah = mataKuliah;
        this.semester = semester;
        this.nilaiTugas = nilaiTugas;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
        this.calculateNilaiAkhir();
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getMahasiswaId() { return mahasiswaId; }
    public void setMahasiswaId(int mahasiswaId) { this.mahasiswaId = mahasiswaId; }
    
    public String getMataKuliah() { return mataKuliah; }
    public void setMataKuliah(String mataKuliah) { this.mataKuliah = mataKuliah; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public float getNilaiTugas() { return nilaiTugas; }
    public void setNilaiTugas(float nilaiTugas) {
        this.nilaiTugas = nilaiTugas;
        calculateNilaiAkhir();
    }
    
    public float getNilaiUts() { return nilaiUts; }
    public void setNilaiUts(float nilaiUts) {
        this.nilaiUts = nilaiUts;
        calculateNilaiAkhir();
    }
    
    public float getNilaiUas() { return nilaiUas; }
    public void setNilaiUas(float nilaiUas) {
        this.nilaiUas = nilaiUas;
        calculateNilaiAkhir();
    }
    
    public float getNilaiAkhir() { return nilaiAkhir; }
    public String getGrade() { return grade; }
    
    private void calculateNilaiAkhir() {
        nilaiAkhir = (nilaiTugas * 0.3f) + (nilaiUts * 0.3f) + (nilaiUas * 0.4f);
        calculateGrade();
    }
    
    private void calculateGrade() {
        if (nilaiAkhir >= 85) grade = "A";
        else if (nilaiAkhir >= 80) grade = "A-";
        else if (nilaiAkhir >= 75) grade = "B+";
        else if (nilaiAkhir >= 70) grade = "B";
        else if (nilaiAkhir >= 65) grade = "B-";
        else if (nilaiAkhir >= 60) grade = "C+";
        else if (nilaiAkhir >= 55) grade = "C";
        else if (nilaiAkhir >= 40) grade = "D";
        else grade = "E";
    }
}