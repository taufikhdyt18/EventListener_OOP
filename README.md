## Event Listener
| Pertemuan 13  |  Pemrograman Berorientasi Objek  
|-------|---------
| NIM   | 312310576
| Nama  | Taufik Hidayat
| Kelas | TI.23.A6


## Latihan OOP
![image](ss/ss1.png)
STRUKTUR PROYEK
Proyek ini menggunakan arsitektur MVC (Model-View-Controller) dengan struktur folder:


classes: Berisi kelas-kelas dasar dan utilitas
controller: Berisi logic untuk mengontrol alur data
model: Berisi struktur data dan operasi database
view: Berisi tampilan GUI
Main.java: File utama untuk menjalankan aplikasi


PENJELASAN SETIAP PACKAGE DAN FILE

A. Package Classes:

BaseModel.java


Merupakan abstract class yang menjadi dasar untuk semua model
Mengimplementasikan operasi CRUD dasar (Create, Read, Update, Delete)
Menggunakan generic type <T> untuk fleksibilitas tipe data
Method abstract yang harus diimplementasikan:

findAll(): Mengambil semua data
findById(): Mencari data berdasarkan ID
insert(): Menambah data baru
update(): Mengubah data
delete(): Menghapus data




Database.java


Mengatur koneksi ke database MySQL
Menggunakan pattern Singleton untuk koneksi database
Menyimpan konfigurasi database seperti:

DRIVER: MySQL JDBC driver
URL: Alamat database
USERNAME: Username MySQL
PASSWORD: Password MySQL




RowMapper.java


Interface untuk mapping data dari ResultSet ke objek
Memudahkan konversi data dari database ke objek Java
Menggunakan generic type untuk fleksibilitas

B. Package Controller:
MahasiswaController.java

Menghubungkan Model dan View
Menghandle event dari View
Implementasi logika bisnis seperti:

saveMahasiswa(): Menyimpan/update data mahasiswa
deleteMahasiswa(): Menghapus data mahasiswa
clearForm(): Membersihkan form
refreshTable(): Memperbarui tampilan tabel



C. Package Model:

Mahasiswa.java


POJO (Plain Old Java Object) untuk data mahasiswa
Memiliki properti:

id: ID mahasiswa
nim: Nomor Induk Mahasiswa
nama: Nama mahasiswa
jurusan: Jurusan mahasiswa
alamat: Alamat mahasiswa


Dilengkapi dengan getter dan setter


MahasiswaModel.java


Extends BaseModel<Mahasiswa>
Implementasi operasi database untuk entity Mahasiswa
Menggunakan PreparedStatement untuk keamanan dari SQL Injection
Implementasi method CRUD:

findAll(): SELECT semua mahasiswa
findById(): SELECT mahasiswa berdasar ID
insert(): INSERT mahasiswa baru
update(): UPDATE data mahasiswa
delete(): DELETE mahasiswa



D. Package View:
FormMahasiswa.java

Implementasi GUI menggunakan Java Swing
Komponen utama:

JTextField untuk input data
JButton untuk aksi (Save, Delete, Clear)
JTable untuk menampilkan data


Fitur:

Form input data mahasiswa
Tabel daftar mahasiswa
Tombol aksi


Event handling untuk interaksi user

E. Main.java

Entry point aplikasi
Inisialisasi semua komponen:

Membuat koneksi database
Membuat instance Model
Membuat instance View
Membuat instance Controller
Menampilkan GUI




KONFIGURASI DATABASE

sqlCopyCREATE DATABASE akademik_db;
USE akademik_db;

CREATE TABLE mahasiswa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nim VARCHAR(20) NOT NULL UNIQUE,
    nama VARCHAR(100) NOT NULL,
    jurusan VARCHAR(50) NOT NULL,
    alamat TEXT
);

CARA PENGGUNAAN APLIKASI

A. Persiapan:

Install MySQL
Buat database menggunakan script SQL di atas
Install MySQL JDBC Driver
Sesuaikan konfigurasi database di Database.java

B. Menjalankan Aplikasi:

Compile semua file Java
Jalankan Main.java
GUI aplikasi akan muncul

C. Fitur-fitur:

Tambah Mahasiswa:

Isi form dengan data mahasiswa
Klik tombol Save


Edit Mahasiswa:

Pilih data di tabel
Data akan muncul di form
Ubah data
Klik Save


Hapus Mahasiswa:

Pilih data di tabel
Klik Delete


Clear Form:

Klik Clear untuk membersihkan form


KEAMANAN


Menggunakan PreparedStatement untuk mencegah SQL Injection
Validasi input di controller
Error handling untuk operasi database


BEST PRACTICES


Menggunakan MVC pattern
Single Responsibility Principle
Dependency Injection
Generic types untuk fleksibilitas
Proper error handling
Clean code principles


KEBUTUHAN SISTEM


Java Development Kit (JDK)
MySQL Server
MySQL JDBC Driver
IDE Java (opsional)

Aplikasi ini bisa dikembangkan lebih lanjut dengan menambahkan fitur seperti:

Login system
Manajemen user
Reporting
Backup/restore data
Validasi input yang lebih kompleks
UI yang lebih menarik
