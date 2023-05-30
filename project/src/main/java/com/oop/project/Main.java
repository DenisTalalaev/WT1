package com.oop.project;



import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;

public class Main extends Application {

    public static CRUD crud = new CRUD();
    public static Stage mainStage;
    public static Stage saveStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 805, 640);
        stage.setTitle("OOP");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        saveStage = new Stage();
        FXMLLoader laFxmlLoader1 = new FXMLLoader(Main.class.getResource("save.fxml"));
        Scene scene1 = new Scene(laFxmlLoader1.load(), 330, 140);
        saveStage.setTitle("save");
        saveStage.setResizable(false);
        saveStage.setScene(scene1);

    }

    public static void main(String[] args) {
        launch();
    }
}