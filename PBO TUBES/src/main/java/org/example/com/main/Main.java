package org.example.com.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.com.main.UI.UIManager;
import org.example.com.main.books.Book;
import org.example.com.main.data.Admin;
import org.example.com.main.data.Student;
import org.example.com.main.data.User;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        menu(stage);
    }

    public static void menu(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        VBox hboxBtn = new VBox(10);
        Button btnLogAdmin = new Button("Login As Admin");
        Button btnLogStudent = new Button("Login As Student");
        Button calculateFineButton = new Button("Hitung Denda Keterlambatan");
        Button btnExit = new Button("EXIT");

        double buttonWidth = 150; // Tentukan lebar tombol
        double buttonHeight = 30; // Tentukan tinggi tombol
        btnLogAdmin.setPrefSize(buttonWidth, buttonHeight);
        btnLogStudent.setPrefSize(buttonWidth, buttonHeight);
        calculateFineButton.setOnAction(actionEvent -> hitungDenda(stage));
        btnExit.setPrefSize(buttonWidth, buttonHeight);

        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.getChildren().addAll(btnLogAdmin, btnLogStudent, calculateFineButton, btnExit);
        grid.add(hboxBtn, 1, 3);

        final Text actionTarget = new Text();
        actionTarget.setWrappingWidth(200); // Set a fixed width to prevent layout changes
        grid.add(actionTarget, 1, 6);

        btnLogAdmin.setOnAction(actionEvent -> {
            try {
                Admin.logIn(stage);
            } catch (Exception e) {
                actionTarget.setText("An error occurred: " + e.getMessage());
            }
        });

        btnLogStudent.setOnAction(actionEvent -> {
            try {
                Student.logIn(stage);
            } catch (Exception e) {
                actionTarget.setText("An error occured " + e.getMessage());
            }
        });

        btnExit.setOnAction(actionEvent -> {
            try {
                stage.close();
            } catch (Exception e) {
                actionTarget.setText("An error occured " + e.getMessage());
            }
            ;
        });
        Scene scene = new Scene(grid, UIManager.getWidth(), UIManager.getHeight());
        stage.setTitle("MENU");
        stage.setScene(scene);
        stage.show();
    }

    public static String inputNIM() {
        Scanner inputObj = new Scanner(System.in);
        return inputObj.nextLine();
    }

    public static void addTempStudent(Admin admin, String name, String NIM, String faculty, String program) {
        admin.addStudent(name, NIM, faculty, program);
    }

    public static Student checkNIM(String name, String NIM, String faculty, String program) {
        for (Student x : Admin.getStudentData()) {
            if (x.getNIM().equals(NIM)) {
                return null;
            }
        }
        return new Student(name, NIM, faculty, program);
    }

    public static void addTempBook(Student student, int numberBorrowed, String[][] arr) {
        for (int i = 0; i < numberBorrowed; i++)
            student.choiceBook(arr[i][0], Integer.parseInt(arr[i][1]));
    }

    private static void hitungDenda(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 600);

        Label namaLabel = new Label("Masukkan Nama: ");
        TextField namaField = new TextField();
        namaField.setMinWidth(220);

        Label nimLabel = new Label("Masukkan NIM: ");
        TextField nimField = new TextField();
        nimField.setMinWidth(220);

        Label keterlambatanLabel = new Label("Masukkan Jumlah Hari Keterlambatan: ");
        TextField keterlambatanField = new TextField();
        keterlambatanField.setMinWidth(220);

        Button calculateButton = new Button("Hitung Denda");
        Button backButton = new Button("Kembali");

        calculateButton.setOnAction(event -> {
            String nama = namaField.getText();
            String nim = nimField.getText();
            int jumlahHariKeterlambatan = Integer.parseInt(keterlambatanField.getText());

            Sanksi sanksi = new Sanksi(nama, nim, jumlahHariKeterlambatan);
            sanksi.tampilkanDetail(stage);
        });

        backButton.setOnAction(event -> {
            try {
                menu(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().addAll(namaLabel, namaField, nimLabel, nimField, keterlambatanLabel, keterlambatanField, calculateButton, backButton);

        stage.setScene(scene);
    }

}
