package com.oop.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class Controller extends Main {



    @FXML
    private Label accessedTasksLabel;

    @FXML
    private Pane accessedTasksTable;

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
    private Button addPermissionButton;

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
    private TextField nameField;

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
