import classes.Database;
import controller.MahasiswaController;
import controller.NilaiController;
import java.sql.Connection;
import model.MahasiswaModel;
import model.NilaiModel;
import view.FormMahasiswa;
import view.FormNilai;

public class Main {
    private static MahasiswaController mahasiswaController;
    private static NilaiController nilaiController;

    public static void main(String[] args) {
        try {
            Connection connection = Database.getConnection();
            MahasiswaModel mahasiswaModel = new MahasiswaModel(connection);
            NilaiModel nilaiModel = new NilaiModel(connection);
            FormMahasiswa formMahasiswa = new FormMahasiswa();
            FormNilai formNilai = new FormNilai();

            mahasiswaController = new MahasiswaController(mahasiswaModel, formMahasiswa);
            nilaiController = new NilaiController(nilaiModel, mahasiswaModel, formNilai);

            
            formNilai.setVisible(true); 
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
