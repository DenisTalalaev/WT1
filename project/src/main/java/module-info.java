module com.oop.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.project to javafx.fxml;
    exports com.oop.project;
}