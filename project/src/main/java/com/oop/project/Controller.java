package com.oop.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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


    ArrayList<SerializeController> serializers = new ArrayList<>();
    ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<>();

    @FXML
    private void saveBtnClick() {
        Main.saveStage.show();
    }


    @FXML
    private void loadFromFileBtnCLick() {
        FileChooser fileChooser = new FileChooser();
        filters.clear();
        initOpenFilters();
        fileChooser.getExtensionFilters().addAll(filters);
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            loadDataFromFile(file);
        }
    }

    public byte[] readDataFromFile(File file) {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            data = new byte[(int) file.length()];
            fis.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void loadDataFromFile(File file) {
        String[] fileData = file.getName().split("\\.");
        Plugin plugin = null;
        String ext = "";
        for (Plugin pl: plugins.values()
             ) {
            if(fileData[fileData.length-1].equalsIgnoreCase(pl.getExt().substring(2))){
                plugin = pl;
                break;
            }
        }
        byte[] data = readDataFromFile(file);
        if(plugin != null) {
            data = plugin.decrypt(data);
            ext = fileData[fileData.length-2];
        } else {
            ext = fileData[fileData.length-1];
        }
        SerializeController serializer = null;
        for (SerializeController serializeController: serializers
             ) {
            if(ext.equalsIgnoreCase(serializeController.getExt().substring(1))){
                serializer = serializeController;
                break;
            }
        }
        if(serializer != null) {
            crud.setUsers(serializer.loadDataFromByteArray(data));
            reloadTable();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Загрузка");
        alert.setContentText("Данные загружены из файла!");
        alert.showAndWait();
    }

    private void initOpenFilters() {
        for (SerializeController serializeController: serializers
             ) {
            ArrayList<String> filt = new ArrayList<>();
            filt.add("*" + serializeController.getExt());
            for (Plugin pl: plugins.values()
                 ) {
                filt.add("*" + serializeController.getExt() + pl.getExt().substring(1));
            }
            filters.add(new FileChooser.ExtensionFilter(serializeController.getInfo(), filt.toArray(String[]::new)));
        }
    }


    public static final HashMap<String, Plugin> plugins = new HashMap<>();

    private void loadPlugins() {
        File dir = new File("D:\\plugins");
        try {
            List<URL> urls = new ArrayList<>();
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                urls.add(file.toURI().toURL());
            }
            URLClassLoader classLoader = new URLClassLoader(urls.toArray(new URL[0]));

            for (File file : Objects.requireNonNull(dir.listFiles())) {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (entry.getName().endsWith(".class")) {
                        String className = entry.getName().replace('/', '.').substring(0, entry.getName().length() - 6);
                        Class<?> cl = classLoader.loadClass(className);
                        if (Plugin.class.isAssignableFrom(cl)) {
                            Plugin plugin = (Plugin) cl.getDeclaredConstructor().newInstance();
                            plugins.put(plugin.getDescr(), plugin);
                        }
                    }
                }
                jarFile.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initFilters() {
        for (SerializeController controller : serializers) {
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter(controller.getInfo(), controller.getExtention());
            filters.add(filter);
        }
    }


    @FXML
    private void showUserButtonClick() {
        showLabel.setText("");
        showLabel.setVisible(true);
        User user = usersTable.getSelectionModel().getSelectedItem();
        showLabel.setText(user.toString());
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
        createLoadUserHelper(user);
        applyCancelButtonsVisible(true);
    }


    private final ObservableList<Transaction> transactionData = FXCollections.observableArrayList();
    private final ObservableList<User> userObservableList = FXCollections.observableArrayList();
    private final ObservableList<Perm> permissionsList = FXCollections.observableArrayList();

    public void initialize() {

        serializers.add(new BinaryDataController());
        serializers.add(new TextDataController());
        serializers.add(new JSONDataController());

        loadPlugins();

        initFilters();

        Task.initialiseTasks();
        transactionsTable.setItems(transactionData);

        tableColumnFROM.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
        tableColumnTO.setCellValueFactory(cellData -> cellData.getValue().toProperty());
        tableColumnAMOUNT.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        usersTable.setItems(userObservableList);
        usersColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        permissionTable.setItems(permissionsList);
        permissionColumn.setCellValueFactory(cellData -> cellData.getValue().permissionProperty());

        crud.hardInitialise();
        reloadTable();

    }


    public void reloadTable() {
        userObservableList.clear();
        for (User us : crud.getUsers()
        ) {
            userObservableList.add(us);
        }
        userObservableList.sort(Comparator.comparing(User::getName));
        usersTable.refresh();
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
        if (isTransactionFieldFine()) {
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
        if (edit.getText().length() == 0) {
            flag = false;
        } else {
            if (edit.getText().endsWith(".near")) {
                if (edit.getText().split("\\.").length != 2) {
                    flag = false;
                }
            } else if (edit.getText().contains("\\.") || walletEdit.getText().length() != 64) {
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
        User user = UserFactory.createUser(userTypeDropBox.valueProperty().getValue());
        boolean flag = true;
        switch (action) {
            case UPDATE_USER -> {
                action = Action.CREATE_USER;
                flag = createLoadUserHelper(user);
                crud.deleteUser(userBuffer);
                userObservableList.remove(userBuffer);
                TableView.TableViewSelectionModel selectionModel = usersTable.getSelectionModel();
                if (!selectionModel.isEmpty()) {
                    int index = selectionModel.getSelectedIndex();
                    if (index < usersTable.getItems().size() - 1) {
                        selectionModel.selectNext();
                    }
                }
            }
            case CREATE_USER -> {
                flag = createLoadUserHelper(user);
            }
        }
        if (flag) {
            clearFields();
            hideElements();
        }
    }

    private boolean developerUIController(User user) {
        if (action == Action.UPDATE_USER) {
            return loadDeveloper(user);
        } else {
            return addDeveloper(user);
        }
    }

    private boolean addDeveloper(User user) {
        if (!isUserFieldsFine() | !isDeveloperFieldsFine()) return false;
        user.setName(nameField.getText());
        user.setBirth(birthDateEdite.getValue());
        ((Developer) user).setStat(new Stats(reviewCountEdit.getText(), taskCountEdit.getText()));
        ((Developer) user).setWallet(walletEdit.getText());
        for (Transaction transaction : transactionData
        ) {
            ((Developer) user).addTransaction(transaction);
        }

        crud.addDeveloper((Developer) user);
        userObservableList.add(user);
        reloadTable();
        return true;
    }

    private boolean loadDeveloper(User user) {
        setUserUIVisible(true);
        setDeveloperUIVisible(true);

        String userName = user.getName().split(":")[1].trim();
        nameField.setText(userName);
        birthDateEdite.setValue(user.getBirth());

        userTypeDropBox.setValue(userTypeDropBox.getItems().get(0));
        Developer developer = (Developer) user;
        reviewCountEdit.setText(String.valueOf(developer.getStats().getCountReview()));
        taskCountEdit.setText(String.valueOf(developer.getStats().getCountTasks()));
        walletEdit.setText(developer.getWallet());
        transactionData.addAll(developer.getTransactions());
        return true;
    }

    private boolean moderatorUIController(User user) {
        if (action == Action.UPDATE_USER) {
            return loadModerator(user);
        } else {
            return addModerator(user);
        }
    }

    private boolean addModerator(User user) {
        if (!isUserFieldsFine() | !isAdminFieldsFine() | !isModeratorFieldsFine()) return false;
        user.setName(nameField.getText());
        user.setBirth(birthDateEdite.getValue());

        ((Admin) user).setRoot(rootAccessCheckBox.isSelected());
        ((Admin) user).setSalary(salaryEdit.getText());
        ArrayList<Integer> taskIds = new ArrayList<>();
        if (nightSkyCheckBox.isSelected()) taskIds.add(Task.nightSky.getIndex());
        if (moonDanceCheckBox.isSelected()) taskIds.add(Task.moonDance.getIndex());
        if (sunShineCheckBox.isSelected()) taskIds.add(Task.sunShine.getIndex());
        if (acadeCheckBox.isSelected()) taskIds.add(Task.acade.getIndex());
        ((Admin) user).setTaskIDs(taskIds);
        ((Moderator) user).setRang(moderatorRangEdit.getText());

        crud.addModerator((Moderator) user);
        userObservableList.add(user);
        reloadTable();
        return true;
    }

    private boolean loadModerator(User user) {
        setUserUIVisible(true);
        setAdminUIVisible(true);
        setModeratorUIVisible(true);

        String userName = user.getName().split(":")[1].trim();
        nameField.setText(userName);
        birthDateEdite.setValue(user.getBirth());

        userTypeDropBox.setValue(userTypeDropBox.getItems().get(1));
        salaryEdit.setText(String.valueOf(((Admin) user).getSalary()));
        rootAccessCheckBox.setSelected(((Admin) user).isRoot());
        checkTaskCheckBox(nightSkyCheckBox, ((Admin) user).getTaskIDs(), Task.nightSky.getIndex());
        checkTaskCheckBox(moonDanceCheckBox, ((Admin) user).getTaskIDs(), Task.moonDance.getIndex());
        checkTaskCheckBox(acadeCheckBox, ((Admin) user).getTaskIDs(), Task.acade.getIndex());
        checkTaskCheckBox(sunShineCheckBox, ((Admin) user).getTaskIDs(), Task.sunShine.getIndex());
        try {
            userTypeDropBox.setValue(userTypeDropBox.getItems().get(1));
        } catch (Exception e) {
        }
        moderatorRangEdit.setText(String.valueOf(((Moderator) user).getRang()));
        return true;
    }

    private void checkTaskCheckBox(CheckBox checkBox, ArrayList<Integer> taskIds, int taskId) {
        checkBox.setSelected(taskIds.contains(taskId));
    }

    public boolean technicalAdminUIController(User user) {
        if (action == Action.UPDATE_USER) {
            return loadTechnicalAdmin(user);
        } else if (action == Action.CREATE_USER) {
            return addTechnicalAdmin(user);
        }
        return true;
    }

    private boolean addTechnicalAdmin(User user) {
        if (!isUserFieldsFine() | !isAdminFieldsFine() | !isTechnicalAdminFieldsFine()) return false;
        user.setName(nameField.getText());
        user.setBirth(birthDateEdite.getValue());

        ((Admin) user).setRoot(rootAccessCheckBox.isSelected());
        ((Admin) user).setSalary(salaryEdit.getText());
        ArrayList<Integer> taskIds = new ArrayList<>();
        if (nightSkyCheckBox.isSelected()) taskIds.add(Task.nightSky.getIndex());
        if (moonDanceCheckBox.isSelected()) taskIds.add(Task.moonDance.getIndex());
        if (sunShineCheckBox.isSelected()) taskIds.add(Task.sunShine.getIndex());
        if (acadeCheckBox.isSelected()) taskIds.add(Task.acade.getIndex());
        ((Admin) user).setTaskIDs(taskIds);

        for (Perm perm : permissionsList
        ) {
            ((TechnicalAdmin) user).addPermission(perm);
        }
        crud.addTechnicalAdmin((TechnicalAdmin) user);
        userObservableList.add(user);
        reloadTable();
        return true;
    }

    private boolean loadTechnicalAdmin(User user) {
        setUserUIVisible(true);
        setAdminUIVisible(true);
        setTechnicalAdminUIVisible(true);

        String userName = user.getName().split(":")[1].trim();
        nameField.setText(userName);
        birthDateEdite.setValue(user.getBirth());

        userTypeDropBox.setValue(userTypeDropBox.getItems().get(1));
        salaryEdit.setText(String.valueOf(((Admin) user).getSalary()));
        rootAccessCheckBox.setSelected(((Admin) user).isRoot());
        checkTaskCheckBox(nightSkyCheckBox, ((Admin) user).getTaskIDs(), Task.nightSky.getIndex());
        checkTaskCheckBox(moonDanceCheckBox, ((Admin) user).getTaskIDs(), Task.moonDance.getIndex());
        checkTaskCheckBox(acadeCheckBox, ((Admin) user).getTaskIDs(), Task.acade.getIndex());
        checkTaskCheckBox(sunShineCheckBox, ((Admin) user).getTaskIDs(), Task.sunShine.getIndex());

        userTypeDropBox.setValue(userTypeDropBox.getItems().get(1));
        permissionsList.addAll(((TechnicalAdmin) user).getPermissions());
        return true;
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
            rootAccessCheckBox.selectedProperty().set(false);
        }
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

    private boolean createLoadUserHelper(User user) {
        switch (user.userType) {
            case TECHNICAL_ADMIN -> {
                return technicalAdminUIController(user);
            }
            case MODERATOR -> {
                return moderatorUIController(user);
            }
            case DEVELOPER -> {
                return developerUIController(user);
            }
        }
        return false;
    }

    @FXML
    void userTypeDropBoxChange() {
        action = Action.UPDATE_USER;
        hideElements();
        clearFields();
        User user = UserFactory.createUser(userTypeDropBox.valueProperty().getValue());
        createLoadUserHelper(user);
        action = Action.CREATE_USER;
        applyCancelButtonsVisible(true);
    }


    @FXML
    protected void onDragDropDropped(DragEvent event){
        if(event.getDragboard().hasFiles()){
            List<File> files = event.getDragboard().getFiles();
            if (files.size() > 0) {
                File file = files.get(0);
                loadDataFromFile(file);
            }

        }
    }

    @FXML
    protected void onDragDropEntered(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

}