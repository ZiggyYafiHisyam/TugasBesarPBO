package com.tbpboziggy;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class App {
        private static boolean login(String correctUsername, String correctPassword, String inputUsername, String inputPassword) {
        return correctUsername.equals(inputUsername) && correctPassword.equals(inputPassword);
        }

        private static String generateCaptcha() {
        return "Vz07RKP";
        }

        private static boolean checkCaptcha(String correctCaptcha, String inputCaptcha) {
        return correctCaptcha.equalsIgnoreCase(inputCaptcha);
        }

        private static void tambahData(Connection connection) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Masukkan Nama: ");
                String nama = scanner.nextLine();

                System.out.print("Masukkan Nomor HP: ");
                String nomorHp = scanner.nextLine();

                System.out.print("Masukkan Total Transaksi: ");
                double totalTransaksi = scanner.nextDouble();
                scanner.nextLine();

                String sql = "INSERT INTO penjualan (nama, nomorHp, totalTransaksi) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nama);
                preparedStatement.setString(2, nomorHp);
                preparedStatement.setDouble(3, totalTransaksi);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data added to database");
                } else {
                    System.out.println("Failed to add data");
                }
            scanner.close();
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


        private static void tampilkanData(Connection connection) {
            try {
                String sql = "SELECT * FROM penjualan";

            try (Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql)) {
                System.out.println("Data dalam tabel transaksi:");
                while (resultSet.next()) {
                    String nama = resultSet.getString("nama");
                    String nomorHp = resultSet.getString("nomorHp");
                    double totalTransaksi = resultSet.getDouble("totalTransaksi");

                    System.out.println("Nama: " + nama + ", Nomor HP: " + nomorHp + ", Total Transaksi: " + totalTransaksi);
                    }
                }
            } 
            catch (SQLException z) {
            z.printStackTrace();
            }
        }
    

        private static void hapusData(Connection connection, String nama) {
            try {
                String sql = "DELETE FROM penjualan WHERE nama = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nama);
                int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                    System.out.println("Data succesfully deleted");
                    } 
                    else {
                    System.out.println("Failed to delete data");
                    }
                }
            } 
            catch (SQLException e) {
            e.printStackTrace();
            }
        }


        private static void updateData(Connection connection, String nama, double totalTransaksiBaru) {
            try {
                String sql = "UPDATE penjualan SET total_transaksi = ? WHERE nama = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, totalTransaksiBaru);
                preparedStatement.setString(2, nama);
                int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                    System.out.println("Data updated");
                    } 
                    else {
                    System.out.println("Failed to update data");
                    }
                }
            } 
            catch (SQLException e) {
            e.printStackTrace();
            }
        }

        
        public static void main( String[] args ) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/tbpboziggy";
            String dbuser = "root";
            String password = "";
                try {
                    Connection connection = DriverManager.getConnection(url, dbuser, password);
                    System.out.println("Successfully connected to database");
                    connection.close();
                    } 
                    catch (SQLException e) {
                        System.err.println("Failed to connect to database");
                        e.printStackTrace();
                    }

                Scanner scanner = new Scanner(System.in);
        
                String username = "ziggy";
                String pass = "rkp2409";
                boolean lgn=false;

                while (!lgn) {
                    System.out.print("Masukkan username: ");
                    String inputUsername = scanner.nextLine();

                    System.out.print("Masukkan password: ");
                    String inputPassword = scanner.nextLine();

                if (login(username, pass, inputUsername, inputPassword)) {
                    String captcha = generateCaptcha();
                    System.out.println("CAPTCHA: " + captcha);

                    System.out.print("Masukkan CAPTCHA: ");
                    String inputCaptcha = scanner.nextLine();

                    if (checkCaptcha(captcha, inputCaptcha)) {
                        System.out.println("Login berhasil!");
                        lgn=true;
                    } 
                    else {
                    System.out.println("Login gagal. CAPTCHA salah.");
                    }
                } 
                else {
                System.out.println("Login gagal. Periksa kembali username dan password.");
                }
            }

            Date tanggal = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String tanggalTransaksi = dateFormat.format(tanggal);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");
            String waktuTransaksi = timeFormat.format(tanggal);

            try {
            
                System.out.println("");
                VvipMember vvip = new VvipMember(){};
                System.out.print("Masukkan Nama Pelanggan\t: ");
                vvip.namaPelanggan = scanner.nextLine();

                System.out.print("Masukkan Nomor HP\t: ");
                vvip.noHp = scanner.nextLine();

                System.out.print("Masukkan Alamat\t\t: ");
                vvip.alamat = scanner.nextLine();

                System.out.print("Masukkan Kode Barang\t: ");
                vvip.kodeBarang = scanner.nextLine();

                System.out.print("Masukkan Nama Barang\t: ");
                vvip.namaBarang = scanner.nextLine();

                System.out.print("Masukkan Harga Barang\t: ");
                vvip.hargaBarang = scanner.nextLong();

                System.out.print("Masukkan Jumlah Barang\t: ");
                vvip.jumlahBeli = scanner.nextLong();

                vvip.totalBayar = vvip.hargaBarang * vvip.jumlahBeli;

                System.out.println("");
                System.out.println("\t\033[1m\033[38;5;127mZeke Electronics\033[0m");
                System.out.println("Tanggal \t:" + tanggalTransaksi);
                System.out.println("Waktu \t\t:" + waktuTransaksi);
                System.out.println("========================");
                System.out.println("DATA PELANGGAN");
                System.out.println("------------------------");
                System.out.println("Nama Pelanggan\t:" + vvip.namaPelanggan.toUpperCase());
                System.out.println("Nomor Hp\t:"+ vvip.noHp);
                System.out.println("Alamat\t\t:"+ vvip.alamat.toLowerCase());
                System.out.println("++++++++++++++++++++++++");
                System.out.println("DATA PEMBELIAN BARANG");
                System.out.println("------------------------");
                System.out.println("Kode Barang\t:" + vvip.kodeBarang.toUpperCase());
                System.out.println("Nama Barang\t:" + vvip.namaBarang);
                System.out.println("Harga Barang\t:" + vvip.hargaBarang);
                System.out.println("Jumlah Beli\t:" + vvip.jumlahBeli);
                System.out.println("TOTAL BAYAR\t:" + vvip.totalBayar);
                System.out.println("++++++++++++++++++++++++");
                System.out.println("Kasir\t\t:" + vvip.namaKasir.toUpperCase());
                System.out.println("");
            } 
            catch (java.util.InputMismatchException e) {
            System.out.println("Maaf, input tidak valid. Pastikan Anda memasukkan nilai numerik untuk harga dan jumlah barang.");
            }

            try (Connection connection = DriverManager.getConnection(url, dbuser, password)) {
                while (true) {
                    System.out.println("Select Command:");
                    System.out.println("1. Input Data");
                    System.out.println("2. Show Data");
                    System.out.println("3. Delete Data");
                    System.out.println("4. Update Data");
                    System.out.println("5. Exit/Done");
                    System.out.print("Insert your Choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        tambahData(connection);
                        break;
                    case 2:
                        tampilkanData(connection);
                        break;
                    case 3:
                        System.out.print("Masukkan nama untuk menghapus data: ");
                        String namaHapus = scanner.nextLine();
                        hapusData(connection, namaHapus);
                        break;
                    case 4:
                        System.out.print("Masukkan nama untuk mengupdate data: ");
                        String namaUpdate = scanner.nextLine();
                    
                        System.out.print("Masukkan total transaksi baru: ");
                        double totalTransaksiBaru = scanner.nextDouble();
                        scanner.nextLine();
                    
                        updateData(connection, namaUpdate, totalTransaksiBaru);
                        break;
                    case 5:
                        System.out.println("Program selesai.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                    }
                }
            } 
        catch (SQLException e) {
        e.printStackTrace();
        }
        scanner.close();
    }
}