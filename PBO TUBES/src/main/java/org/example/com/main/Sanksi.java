package org.example.com.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Sanksi {
    private String nama;
    private String nim;
    private int jumlahHariKeterlambatan;
    private final int jumlahHariPinjam = 7;
    private final int dendaPerHari = 1000;

    public Sanksi(String nama, String nim, int jumlahHariKeterlambatan) {
        this.nama = nama;
        this.nim = nim;
        this.jumlahHariKeterlambatan = jumlahHariKeterlambatan;
    }

    public int hitungDenda() {
        return jumlahHariKeterlambatan * dendaPerHari;
    }

    public void tampilkanDetail(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 600);

        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Jumlah Hari Keterlambatan: " + jumlahHariKeterlambatan);
        System.out.println("Denda: Rp " + hitungDenda());
        //ganti semuanya ke label , spt contoh dibawah
        Label denda = new Label("Denda: Rp " + hitungDenda());
        Label jumlah = new Label("Jumlah hari : " + jumlahHariKeterlambatan);

        root.getChildren().addAll(denda, jumlah);

        stage.setScene(scene);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Jumlah Hari Keterlambatan: ");
        int jumlahHariKeterlambatan = scanner.nextInt();

        Sanksi sanksi = new Sanksi(nama, nim, jumlahHariKeterlambatan);
        sanksi.tampilkanDetail(new Stage());

        scanner.close();
    }
}
