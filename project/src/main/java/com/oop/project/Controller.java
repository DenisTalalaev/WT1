package com.oop.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.security.Permission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller extends Main {
    enum Action {
        CREATE_USER,
        DELETE_USER,
        UPDATE_USER,
        SHOW_USER
    }

    private User userBuffer;
    private Action action;


    @FXML
    private Button showBtn;

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
    private ComboBox<String> adminTypeDropBox;

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
    private Label birthDateLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createBtn;

    @FXML
    private Button deleteBtn;

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
    private Label nameLabel;

    @FXML
    private CheckBox nightSkyCheckBox;

    @FXML
    private TableView<Perm> permissionTable;

    @FXML
    private TableColumn<Perm, String> permissionColumn;

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
    private TextArea showLabel;

    @FXML
    private CheckBox sunShineCheckBox;

    @FXML
    private TableColumn<Transaction, String> tableColumnFROM;

    @FXML
    private TableColumn<Transaction, String> tableColumnTO;

    @FXML
    private TableColumn<Transaction, Double> tableColumnAMOUNT;

    @FXML
    private TextField taskCountEdit;

    @FXML
    private Label taskCountLabel;

    @FXML
    private Label transactionsLabel;

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Button updateBtn;

    @FXML
    private ComboBox<String> userTypeDropBox;

    @FXML
    private Label userTypeLabel;

    @FXML
    private TableColumn<User, String> usersColumn;

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
    private ScrollPane showPanel;


    @FXML
    private void showUserButtonClick(){
        showLabel.setText("");
        showLabel.setVisible(true);
        User user = usersTable.getSelectionModel().getSelectedItem();
        if(user instanceof Developer){
            for (String str: ((Developer)user).toStringArrayList()
                 ) {
                showLabel.setText(showLabel.getText() + str);
            }
        }

        if(user instanceof TechnicalAdmin){
            showLabel.setText(((TechnicalAdmin) user).toString());
        }

        if(user instanceof Moderator){
            showLabel.setText(((Moderator) user).toString());
        }
    }

    @FXML
    private void createBtnClick() {
        showLabel.setVisible(false);
        cancelButton.setVisible(true);
        transactionData.clear();
        action = Action.CREATE_USER;
        setUserUIVisible(true);
    }

    @FXML
    private void deleteBtnClick() {
        showLabel.setVisible(false);
        hideElements();
        clearFields();
        User user = usersTable.getSelectionModel().getSelectedItem();
        crud.deleteUser(user);
        userObservableList.remove(user);
    }

    @FXML
    private void updateBtnClick() {
        showLabel.setVisible(false);
        transactionData.clear();
        permissionsList.clear();
        User user = usersTable.getSelectionModel().getSelectedItem();
        userBuffer = user;
        action = Action.UPDATE_USER;
        loadUser(user);
    }

    private void loadUser(User user) {
        setUserUIVisible(true);
        nameField.setText(user.getName().split(":")[1].trim());
        birthDateEdite.setValue(user.getBirth());
        if (user instanceof Admin) {
            userTypeDropBox.setValue(userTypeDropBox.getItems().get(1));
            setAdminUIVisible(true);
            salaryEdit.setText(String.valueOf(((Admin) user).getSalary()));
            rootAccessCheckBox.setSelected(((Admin) user).isRoot());
            if (((Admin) user).getTaskIDs().contains(Task.nightSky.getIndex())) {
                nightSkyCheckBox.setSelected(true);
            } else {
                nightSkyCheckBox.setSelected(false);
            }
            if (((Admin) user).getTaskIDs().contains(Task.moonDance.getIndex())) {
                moonDanceCheckBox.setSelected(true);
            } else {
                moonDanceCheckBox.setSelected(false);
            }
            if (((Admin) user).getTaskIDs().contains(Task.acade.getIndex())) {
                acadeCheckBox.setSelected(true);
            } else {
                acadeCheckBox.setSelected(false);
            }
            if (((Admin) user).getTaskIDs().contains(Task.sunShine.getIndex())) {
                sunShineCheckBox.setSelected(true);
            } else {
                sunShineCheckBox.setSelected(false);
            }
            if (user instanceof TechnicalAdmin) {
                setTechnicalAdminUIVisible(true);
                adminTypeDropBox.setValue(adminTypeDropBox.getItems().get(0));
                permissionsList.addAll(((TechnicalAdmin) user).getPermissoins());
            } else if (user instanceof Moderator) {
                setModeratorUIVisible(true);
                adminTypeDropBox.setValue(adminTypeDropBox.getItems().get(1));
                moderatorRangEdit.setText(String.valueOf(((Moderator) user).getRang()));
            }
        } else if (user instanceof Developer) {
            setDeveloperUIVisible(true);
            userTypeDropBox.setValue(userTypeDropBox.getItems().get(0));
            reviewCountEdit.setText(String.valueOf(((Developer) user).getStats().getCountReview()));
            taskCountEdit.setText(String.valueOf(((Developer) user).getStats().getCountTasks()));
            walletEdit.setText(((Developer) user).getWallet());
            transactionData.addAll(((Developer) user).getTransactions());

        }
        applyCancelButtonsVisible(true);
    }

    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();
    private ObservableList<User> userObservableList = FXCollections.observableArrayList();
    private ObservableList<Perm> permissionsList = FXCollections.observableArrayList();


    public void initialize() {
        transactionsTable.setItems(transactionData);

        tableColumnFROM.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
        tableColumnTO.setCellValueFactory(cellData -> cellData.getValue().toProperty());
        tableColumnAMOUNT.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        usersTable.setItems(userObservableList);
        usersColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        permissionTable.setItems(permissionsList);
        permissionColumn.setCellValueFactory(cellData -> cellData.getValue().permissionProperty());

        crud.hardInitialise();
        for (User us : crud.getUsers()
        ) {
            userObservableList.add(us);
        }
        userObservableList.sort(Comparator.comparing(User::getName));
    }

    @FXML
    private void addPermissionButtonClick() {
        permissionsList.add(new Perm(addPermissionEdit.getText().toString()));
    }


    @FXML
    private void deletePermissionButtonClick() {
        Perm selected = permissionTable.getSelectionModel().getSelectedItem();
        permissionsList.remove(selected);
    }


    @FXML
    private void deleteTransactionButtonClick() {
        Transaction selected = transactionsTable.getSelectionModel().getSelectedItem();
        transactionData.remove(selected);
    }

    @FXML
    private void cancelButtonClick() {
        hideElements();
        clearFields();
        action = Action.SHOW_USER;
    }


    @FXML
    private void addTransactionButtonClick() {
        walletFromEdit.setStyle(null);
        walletToEdit.setStyle(null);
        amountEdit.setStyle(null);
        if (!isTransactionFieldFine()) {
            return;
        } else {
            Transaction transaction = new Transaction(walletFromEdit.getText(), walletToEdit.getText(), amountEdit.getText());
            transactionData.add(transaction);
            transactionsTable.setItems(transactionData);
        }
    }

    private boolean isTransactionFieldFine() {
        boolean flag = true;
        if (!isWalletFine(walletToEdit)) {
            flag = false;
            walletToEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        if (!isWalletFine(walletFromEdit)) {
            flag = false;
            walletFromEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        try {
            double temp = Double.parseDouble(amountEdit.getText().toString());
            if (temp < 0.0) {
                flag = false;
                amountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        } catch (Exception e) {
            flag = false;
            amountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }
        return flag;
    }

    private boolean isWalletFine(TextField edit) {
        boolean flag = true;
        if (edit.getText().toString().length() == 0) {
            flag = false;
        } else {
            if (edit.getText().toString().endsWith(".near")) {
                if (edit.getText().toString().split("\\.").length != 2) {
                    flag = false;
                }
            } else if (edit.getText().toString().contains("\\.") || walletEdit.getText().toString().length() != 64) {
                flag = false;
            }
        }
        return flag;
    }

    private void setUserUIVisible(boolean status) {
        nameLabel.setVisible(status);
        nameField.setVisible(status);
        birthDateEdite.setVisible(status);
        birthDateLabel.setVisible(status);
        userTypeLabel.setVisible(status);
        userTypeDropBox.setVisible(status);
    }

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
                    return;
                } else {
                    user = UserFactory.createUser(UserType.DEVELOPER);
                }
                break;
            case "Admin":
                switch (adminTypeDropBox.valueProperty().getValue().toString()) {
                    case "TechnicalAdmin":
                        if (!isTechnicalAdminFieldsFine()) {
                            return;
                        } else {
                            user = UserFactory.createUser(UserType.TECHNICAL_ADMIN);
                        }
                        break;
                    case "Moderator":
                        if (!isModeratorFieldsFine()) {
                            return;
                        } else {
                            user = UserFactory.createUser(UserType.MODERATOR);
                        }
                        break;
                }
                break;
            default:
                return;
        }

        switch (action) {
            case UPDATE_USER -> {
                crud.deleteUser(userBuffer);
                userObservableList.remove(userBuffer);
                createUserHelper(user);

                TableView.TableViewSelectionModel selectionModel = usersTable.getSelectionModel();
                if (!selectionModel.isEmpty()) {
                    int index = selectionModel.getSelectedIndex();
                    if (index < usersTable.getItems().size() - 1) {
                        selectionModel.selectNext();
                    }
                }

                break;
            }
            case CREATE_USER -> {
                createUserHelper(user);
                break;
            }
        }

    }

    private void createUserHelper(User user) {
        user.setName(nameField.getText());
        user.setBirth(birthDateEdite.getValue());
        if (user instanceof Developer) {
            ((Developer) user).setStat(new Stats(reviewCountEdit.getText(), taskCountEdit.getText()));
            ((Developer) user).setWallet(walletEdit.getText());
            for (Transaction transaction : transactionData
            ) {
                ((Developer) user).addTransaction(transaction);
            }
            crud.addDeveloper((Developer) user);
        }
        if (user instanceof Admin) {
            ((Admin) user).setRoot(rootAccessCheckBox.isSelected());
            ((Admin) user).setSalary(salaryEdit.getText());
            ArrayList<Integer> taskIds = new ArrayList<>();
            if (nightSkyCheckBox.isSelected()) taskIds.add(Task.nightSky.getIndex());
            if (moonDanceCheckBox.isSelected()) taskIds.add(Task.moonDance.getIndex());
            if (sunShineCheckBox.isSelected()) taskIds.add(Task.sunShine.getIndex());
            if (acadeCheckBox.isSelected()) taskIds.add(Task.acade.getIndex());
            ((Admin) user).setTaskIDs(taskIds);
        }
        if (user instanceof TechnicalAdmin) {
            for (Perm perm : permissionsList
            ) {
                ((TechnicalAdmin) user).addPermission(perm);
            }
            crud.addTechnicalAdmin((TechnicalAdmin) user);
        }
        if (user instanceof Moderator) {
            ((Moderator) user).setRang(moderatorRangEdit.getText());
            crud.addModerator((Moderator) user);
        }

        userObservableList.add(user);
        userObservableList.sort(Comparator.comparing(User::getName));

        clearFields();
        hideElements();
    }

    private void hideElements() {
        setUserUIVisible(false);
        setAdminUIVisible(false);
        setModeratorUIVisible(false);
        setDeveloperUIVisible(false);
        setTechnicalAdminUIVisible(false);
        applyCancelButtonsVisible(false);
    }

    private void clearFields() {
        nameField.setText("");
        birthDateEdite.setValue(null);
        salaryEdit.setText("");
        reviewCountEdit.setText("");
        taskCountEdit.setText("");
        walletEdit.setText("");
        walletToEdit.setText("");
        walletFromEdit.setText("");
        amountEdit.setText("");
        addPermissionEdit.setText("");
        moderatorRangEdit.setText("");
        transactionData.clear();
        permissionsList.clear();
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
                    flag = false;
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
        if (!flag)
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
        try {
            if (taskCountEdit.getText().toString().length() == 0) {
                flag = false;
                taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
            int num = Integer.parseInt(taskCountEdit.getText().toString());
            if (num < 0) {
                flag = false;
                taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        } catch (Exception e) {
            flag = false;
            taskCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        try {
            if (reviewCountEdit.getText().toString().length() == 0) {
                flag = false;
                reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
            int num = Integer.parseInt(reviewCountEdit.getText().toString());
            if (num < 0) {
                flag = false;
                reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
            }
        } catch (Exception e) {
            flag = false;
            reviewCountEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        }

        if (walletEdit.getText().toString().length() == 0) {
            flag = false;
            walletEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        } else {
            if (walletEdit.getText().toString().endsWith(".near")) {
                if (walletEdit.getText().toString().split("\\.").length != 2) {
                    flag = false;
                    walletEdit.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
                }
            } else if (walletEdit.getText().toString().contains("\\.") || walletEdit.getText().toString().length() != 64) {
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
