module com.oop.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires gson.extras;

    opens com.oop.project to com.google.gson, javafx.fxml;
    exports com.oop.project;
}