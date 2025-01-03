    package model;

    import classes.BaseModel;
    import classes.RowMapper;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class NilaiModel extends BaseModel<Nilai> {
        public NilaiModel(Connection connection) {
            super(connection);
        }
        
        private RowMapper<Nilai> mapper = rs -> {
            Nilai nilai = new Nilai();
            nilai.setId(rs.getInt("id"));
            nilai.setMahasiswaId(rs.getInt("mahasiswa_id"));
            nilai.setMataKuliah(rs.getString("mata_kuliah"));
            nilai.setSemester(rs.getInt("semester"));
            nilai.setNilaiTugas(rs.getFloat("nilai_tugas"));
            nilai.setNilaiUts(rs.getFloat("nilai_uts"));
            nilai.setNilaiUas(rs.getFloat("nilai_uas"));
            return nilai;
        };
        
        @Override
        public List<Nilai> findAll() throws SQLException {
            List<Nilai> result = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM nilai")) {
                while (rs.next()) {
                    result.add(mapper.mapRow(rs));
                }
            }
            return result;
        }
        
        public List<Nilai> findByMahasiswaId(int mahasiswaId) throws SQLException {
            List<Nilai> result = new ArrayList<>();
            String sql = "SELECT * FROM nilai WHERE mahasiswa_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, mahasiswaId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(mapper.mapRow(rs));
                    }
                }
            }
            return result;
        }
        
        @Override
        public Nilai findById(int id) throws SQLException {
            String sql = "SELECT * FROM nilai WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapper.mapRow(rs);
                    }
                }
            }
            return null;
        }
        
        @Override
        public boolean insert(Nilai nilai) throws SQLException {
            String sql = "INSERT INTO nilai (mahasiswa_id, mata_kuliah, semester, " +
                        "nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, grade) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, nilai.getMahasiswaId());
                stmt.setString(2, nilai.getMataKuliah());
                stmt.setInt(3, nilai.getSemester());
                stmt.setFloat(4, nilai.getNilaiTugas());
                stmt.setFloat(5, nilai.getNilaiUts());
                stmt.setFloat(6, nilai.getNilaiUas());
                stmt.setFloat(7, nilai.getNilaiAkhir());
                stmt.setString(8, nilai.getGrade());
                return stmt.executeUpdate() > 0;
            }
        }
        
        @Override
        public boolean update(Nilai nilai) throws SQLException {
            String sql = "UPDATE nilai SET mata_kuliah=?, semester=?, " +
                        "nilai_tugas=?, nilai_uts=?, nilai_uas=?, " +
                        "nilai_akhir=?, grade=? WHERE id=?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nilai.getMataKuliah());
                stmt.setInt(2, nilai.getSemester());
                stmt.setFloat(3, nilai.getNilaiTugas());
                stmt.setFloat(4, nilai.getNilaiUts());
                stmt.setFloat(5, nilai.getNilaiUas());
                stmt.setFloat(6, nilai.getNilaiAkhir());
                stmt.setString(7, nilai.getGrade());
                stmt.setInt(8, nilai.getId());
                return stmt.executeUpdate() > 0;
            }
        }
        
        @Override
        public boolean delete(int id) throws SQLException {
            String sql = "DELETE FROM nilai WHERE id=?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
        }
    }