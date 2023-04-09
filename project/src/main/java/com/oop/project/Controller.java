package com.oop.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.time.LocalDate;

public class Controller extends Main {

    @FXML
    private CheckBox acadeCheckBox;

    @FXML
    private CheckBox accessedTasksCheckBox;

    @FXML
    private Label accessedTasksLabel;

    @FXML
    private Pane accessedTasksTable;

    @FXML
    private Button addPermissionButton;

    @FXML
    private TextField addPermissionEdit;

    @FXML
    private Label addPermissionLabel;

    @FXML
    private Button addTransactionButton;

    @FXML
    private ComboBox<?> adminTypeDropBox;

    @FXML
    private Label adminTypeLabel;

    @FXML
    private TextField amountEdit;

    @FXML
    private Label amountLabel;

    @FXML
    private Button applyButton;

    @FXML
    private DatePicker birthDateEdite;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deletePermissionButton;

    @FXML
    private Label deletePermissionLabel;

    @FXML
    private Button deleteTransactionButton;

    @FXML
    private Label deleteTransactionLabel;

    @FXML
    private TextField moderatorRangEdit;

    @FXML
    private Label moderatorRangLabel;

    @FXML
    private CheckBox moonDanceCheckBox;

    @FXML
    private TextField nameField;

    @FXML
    private CheckBox nightSkyCheckBox;

    @FXML
    private TableView<?> permissionTable;

    @FXML
    private TextField reviewCountEdit;

    @FXML
    private Label reviewCountLabel;

    @FXML
    private CheckBox rootAccessCheckBox;

    @FXML
    private TextField salaryEdit;

    @FXML
    private Label salaryLabel;

    @FXML
    private CheckBox sunShineCheckBox;

    @FXML
    private TextField taskCountEdit;

    @FXML
    private Label taskCountLabel;

    @FXML
    private Label transactionsLabel;

    @FXML
    private TableView<?> transactionsTable;

    @FXML
    private ComboBox<?> userTypeDropBox;

    @FXML
    private TableColumn<?, ?> usersTable;

    @FXML
    private TextField walletEdit;

    @FXML
    private TextField walletFromEdit;

    @FXML
    private Label walletFromLabel;

    @FXML
    private Label walletLabel;

    @FXML
    private TextField walletToEdit;

    @FXML
    private Label walletToLabel;


    @FXML
    void userTypeDropBoxChange() {
        switch (userTypeDropBox.valueProperty().getValue().toString()) {
            case "Developer":
                loadDeveloperUI();
                break;
            case "Admin":
                loadAdminUI();
                break;
            default:
                return;
        }
    }

    @FXML
    void adminTypeDropBoxChange() {
        try {
            switch (adminTypeDropBox.valueProperty().getValue().toString()) {
                case "TechnicalAdmin":
                    loadTechnicalAdminUI();
                    break;
                case "Moderator":
                    loadModeratorUI();
                    break;
                default:
                    return;
            }
        } catch (Exception e) {
        }
    }

    @FXML
    void checkAllTasks() {
        boolean status = accessedTasksCheckBox.isSelected();
        nightSkyCheckBox.setSelected(status);
        moonDanceCheckBox.setSelected(status);
        sunShineCheckBox.setSelected(status);
        acadeCheckBox.setSelected(status);
    }

    @FXML
    void applyButtonPress() {
        resetShadows();
        User user = null;
        switch (userTypeDropBox.valueProperty().getValue().toString()) {
            case "Developer":
                if (!isDeveloperFieldsFine()) {

                } else {
                    user = UserFactory.createUser(UserType.DEVELOPER);
                }
                break;
            case "Admin":
                switch (adminTypeDropBox.valueProperty().getValue().toString()) {
                    case "TechnicalAdmin":
                        if (!isTechnicalAdminFieldsFine()) {

                        } else {
                            user = UserFactory.createUser(UserType.TECHNICAL_ADMIN);
                        }
                        break;
                    case "Moderator":
                        if (!isModeratorFieldsFine()) {

                        } else {
                            user = UserFactory.createUser(UserType.MODERATOR);
                        }
                        break;
                }
                break;
            default:
                return;
        }
    }

    private void resetShadows() {
        nameField.setStyle(null);
        birthDateEdite.setStyle(null);
        moderatorRangEdit.setStyle(null);
        salaryEdit.setStyle(null);
        walletEdit.setStyle(null);
        taskCountEdit.setStyle(null);
        reviewCountEdit.setStyle(null);
    }

    private boolean isUserFieldsFine() {
        boolean flag = true;
        try {
            if (nameField.getText().toString().length() == 0) {
                nameField.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                flag = false;
            }

            for (char ch : nameField.getText().toCharArray()
            ) {
                if (Character.isDigit(ch)) {
                    nameField.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                    flag =  false;
                }
            }

            LocalDate date = birthDateEdite.getValue();
            if (date == null || date.isAfter(LocalDate.now())) {
                birthDateEdite.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                flag = false;
            }
        } catch (Exception e) {
            birthDateEdite.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            flag = false;
        }
        return flag;
    }

    private boolean isModeratorFieldsFine() {
        boolean flag = isUserFieldsFine() & isAdminFieldsFine();
        try {
            int temp = Integer.parseInt(moderatorRangEdit.getText());
            if (temp > 3 || temp < 0) flag = false;
        } catch (Exception ex) {
            flag = false;
        }
        if(!flag)
            moderatorRangEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        return flag;
    }

    private boolean isAdminFieldsFine() {
        boolean flag = true;
        try {
            if (salaryEdit.getText().toString().length() == 0) {
                salaryEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                flag = false;
            }
            double temp = Double.parseDouble(salaryEdit.getText().toString());
            if (temp < 0.0) {
                salaryEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                flag = false;
            }
        } catch (Exception ex) {
            salaryEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            flag = false;
        }
        return flag;
    }

    private boolean isTechnicalAdminFieldsFine() {
        boolean flag = isUserFieldsFine() & isAdminFieldsFine();
        return flag;
    }

    private boolean isDeveloperFieldsFine() {
        boolean flag = isUserFieldsFine();
        try{
            if(taskCountEdit.getText().toString().length() == 0){
                flag = false;
                taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
            int num = Integer.parseInt(taskCountEdit.getText().toString());
            if (num < 0){
                flag = false;
                taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        } catch ( Exception e){
            flag = false;
            taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        try{
            if(reviewCountEdit.getText().toString().length() == 0){
                flag = false;
                reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
            int num = Integer.parseInt(reviewCountEdit.getText().toString());
            if(num < 0){
                flag = false;
                reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        } catch ( Exception e){
            flag = false;
            reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        if(walletEdit.getText().toString().length() == 0){
            flag = false;
            walletEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        } else {
            if(walletEdit.getText().toString().endsWith(".near")){
                if(walletEdit.getText().toString().split("\\.").length != 2){
                    flag = false;
                    walletEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                }
            } else if(walletEdit.getText().toString().contains("\\.") || walletEdit.getText().toString().length() != 64){
                flag = false;
                walletEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        }
        return flag;
    }


    private void loadModeratorUI() {
        setDeveloperUIVisible(false);
        setTechnicalAdminUIVisible(false);
        setAdminUIVisible(true);
        setModeratorUIVisible(true);
        applyCancelButtonsVisible(true);
    }

    private void loadTechnicalAdminUI() {
        setDeveloperUIVisible(false);
        setModeratorUIVisible(false);
        setAdminUIVisible(true);
        setTechnicalAdminUIVisible(true);
        applyCancelButtonsVisible(true);
    }

    private void loadAdminUI() {
        accessedTasksCheckBox.setSelected(false);
        nightSkyCheckBox.setSelected(false);
        moonDanceCheckBox.setSelected(false);
        sunShineCheckBox.setSelected(false);
        acadeCheckBox.setSelected(false);

        setDeveloperUIVisible(false);
        setModeratorUIVisible(false);
        setTechnicalAdminUIVisible(false);
        setAdminUIVisible(true);
        applyCancelButtonsVisible(false);
    }

    private void loadDeveloperUI() {
        setTechnicalAdminUIVisible(false);
        setModeratorUIVisible(false);
        setAdminUIVisible(false);
        setDeveloperUIVisible(true);
        applyCancelButtonsVisible(true);
    }

    private void setModeratorUIVisible(boolean status) {
        moderatorRangEdit.setVisible(status);
        moderatorRangLabel.setVisible(status);
    }

    private void setTechnicalAdminUIVisible(boolean status) {
        permissionTable.setVisible(status);
        addPermissionLabel.setVisible(status);
        addPermissionEdit.setVisible(status);
        addPermissionButton.setVisible(status);
        deletePermissionLabel.setVisible(status);
        deletePermissionButton.setVisible(status);
    }

    private void setAdminUIVisible(boolean status) {
        if (!status) {
            adminTypeDropBox.getSelectionModel().clearSelection();
            rootAccessCheckBox.selectedProperty().set(false);

        }
        adminTypeDropBox.visibleProperty().set(status);
        adminTypeLabel.setVisible(status);
        salaryEdit.setVisible(status);
        salaryLabel.setVisible(status);
        accessedTasksLabel.setVisible(status);
        accessedTasksTable.setVisible(status);
        rootAccessCheckBox.setVisible(status);
    }

    private void setDeveloperUIVisible(boolean status) {
        reviewCountEdit.setVisible(status);
        reviewCountLabel.setVisible(status);
        taskCountEdit.setVisible(status);
        taskCountLabel.setVisible(status);
        walletEdit.setVisible(status);
        walletLabel.setVisible(status);
        transactionsLabel.setVisible(status);
        transactionsTable.setVisible(status);
        deleteTransactionLabel.setVisible(status);
        deleteTransactionButton.setVisible(status);
        walletFromEdit.setVisible(status);
        walletFromLabel.setVisible(status);
        walletToEdit.setVisible(status);
        walletToLabel.setVisible(status);
        amountLabel.setVisible(status);
        amountEdit.setVisible(status);
        addTransactionButton.setVisible(status);
        addTransactionButton.setVisible(status);
    }

    private void applyCancelButtonsVisible(boolean status) {
        applyButton.setVisible(status);
        cancelButton.setVisible(status);
    }


}
