package com.oop.project;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveController {

    public static ArrayList<SerializeController> serializers = new ArrayList<>();

    public static ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<>();

    @FXML
    public ComboBox pluginDropBox;

    @FXML
    private void cancelBtnClick() {
        Main.saveStage.hide();
    }

    @FXML
    private void initialize() {
        ObservableList<String> items = pluginDropBox.getItems();
        pluginDropBox.getItems().clear();
        items.add("Сохранить без шифрования");
        for (String plugin : Controller.plugins.keySet()
        ) {
            items.add(plugin);
        }
        serializers.clear();
        serializers.add(new BinaryDataController());
        serializers.add(new TextDataController());
        serializers.add(new JSONDataController());
    }


    private void initFilters(String sh) {
        filters.clear();
        for (SerializeController controller : serializers) {
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter(controller.getInfo(), controller.getExtention() + sh);
            filters.add(filter);
        }
    }

    private void initOpenFilters(){
        filters.clear();
        for (SerializeController controller : serializers) {
            ArrayList<String> tempFilters = new ArrayList<>();
            for (Plugin pl: Controller.plugins.values()
                 ) {
                tempFilters.add(controller.getExtention() + pl.getExt().substring(1));
            }
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter(controller.getInfo(), tempFilters.toArray(new String[0]));
            filters.add(filter);
        }
        System.out.println(Arrays.toString(filters.toArray()));
    }


    @FXML
    private void acceptBtnClick() {
        FileChooser fileChooser = new FileChooser();
        Plugin plugin = null;
        for (Plugin pl :
                Controller.plugins.values()) {
            if (pl.getDescr().equalsIgnoreCase(pluginDropBox.getValue().toString())) {
                plugin = pl;
            }
        }

        if (plugin != null) { // has plugin
            initFilters(plugin.getExt().substring(1));
        } else {
            initFilters("");
        }


        fileChooser.getExtensionFilters().addAll(filters);
        File file = fileChooser.showSaveDialog(Main.mainStage);

        if (file != null) {
            String[] saveData = file.getName().split("\\.");
            String ext = "";
            if (pluginDropBox.getValue().toString().equalsIgnoreCase("Сохранить без шифрования")) {
                ext = saveData[saveData.length - 1];
            } else {
                ext = saveData[saveData.length - 2];
            }

            SerializeController serializer;
            byte[] data = null;
            for (SerializeController serializeController : serializers
            ) {
                System.out.println(serializeController.getExt().substring(1));
                System.out.println(ext);
                if (serializeController.getExt().substring(1).equalsIgnoreCase(ext)) {
                    serializer = serializeController;
                    data = serializer.saveDataToByteArray(Controller.crud.users);
                    break;
                }
            }

            if (plugin != null) {
                data = plugin.encrypt(data);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Сохранение");
            alert.setContentText("Данные сохранены!");
            alert.showAndWait();
            cancelBtnClick();
        }
    }


}
