module com.example.javaidm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;


    opens com.example.javaidm to javafx.fxml;
    exports com.example.javaidm;
}