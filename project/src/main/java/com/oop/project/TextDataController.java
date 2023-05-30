package com.oop.project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TextDataController extends SerializeController {

    private String ext;
    private String extention = "*.txt";
    private String info = "Текстовые файлы";

    public String getExtention() {
        return extention;
    }

    public String getInfo() {
        return info;
    }

    public TextDataController() {
        ext = ".txt";
    }

    @Override
    public String getExt() {
        return ext;
    }

    @Override
    public byte[] saveDataToByteArray(ArrayList<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(serialize(user) + "\n");
        }
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static final String delimeter = "/--/";
    public static final String subdelimeter = "/-/";

    private static String serialize(User user) {
        String data = "";
        data += user.userType + delimeter;
        data += user.name + delimeter;
        data += user.birth.toString() + delimeter;
        switch (user.userType) {
            case DEVELOPER -> {
                data += "trasactions";
                for (Transaction transaction : ((Developer) user).transactions) {
                    data += ":" + transaction.amount + subdelimeter + transaction.walletTo + subdelimeter + transaction.walletFrom;
                }
                data += delimeter;
                data += ((Developer) user).wallet + delimeter;
                data += ((Developer) user).stats.countReview + delimeter;
                data += ((Developer) user).stats.countTasks;
            }
            case TECHNICAL_ADMIN -> {
                data += ((Admin) user).isRoot ? "1" : "0";
                data += delimeter;
                data += ((Admin) user).salary + delimeter;
                for (int id : ((Admin) user).taskIDs
                ) {
                    data += String.valueOf(id);
                }
                data += delimeter + "permissions";
                for (Perm perm : ((TechnicalAdmin) user).permissoins
                ) {
                    data += ":" + perm.name;
                }
            }
            case MODERATOR -> {
                data += ((Admin) user).isRoot ? "1" : "0";
                data += delimeter;
                data += ((Admin) user).salary + delimeter;
                for (int id : ((Admin) user).taskIDs
                ) {
                    data += String.valueOf(id);
                }
                data += delimeter;
                data += ((Moderator) user).rang;
            }
        }
        return data;
    }

    @Override
    public ArrayList<User> loadDataFromByteArray(byte[] array) {
        ArrayList<User> users = new ArrayList<>();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(array);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bais))) {
            String line = "";
            do {
                line = bufferedReader.readLine();
                if (line == null) break;
                if (line.length() < 2) break;

                String[] data = line.split(delimeter);
                User user = UserFactory.createUser(data[0]); // userType
                user.name = data[1]; //name
                user.birth = LocalDate.parse(data[2]); //birth

                switch (user.userType) {
                    case DEVELOPER -> {
                        String[] transactions = data[3].split(":"); //transactions
                        for (int i = 1; i < transactions.length; i++) {
                            String[] transactionData = transactions[i].split(subdelimeter);
                            Transaction transaction = new Transaction(
                                    transactionData[2],
                                    transactionData[1],
                                    transactionData[0]
                            );
                            ((Developer) user).addTransaction(transaction);
                        }
                        ((Developer) user).wallet = data[4]; //wallet
                        ((Developer) user).stats = new Stats(
                                data[5],
                                data[6]
                        );
                    }
                    case TECHNICAL_ADMIN -> {
                        ((Admin) user).isRoot = data[3].equalsIgnoreCase("1"); //isRoot
                        ((Admin) user).salary = Double.parseDouble(data[4]); // salary
                        ((Admin) user).taskIDs = (ArrayList<Integer>) data[5].chars().map(
                                Character::getNumericValue).boxed().collect(Collectors.toList()); // taskIds

                        String[] permissions = data[6].split(":"); //permissions
                        ((TechnicalAdmin) user).permissoins = new ArrayList<Perm>();
                        for (int i = 1; i < permissions.length; i++) {
                            ((TechnicalAdmin) user).permissoins.add(new Perm(permissions[i]));
                        }
                    }
                    case MODERATOR -> {
                        ((Admin) user).isRoot = data[3].equalsIgnoreCase("1"); //isRoot
                        ((Admin) user).salary = Double.parseDouble(data[4]); // salary
                        ((Admin) user).taskIDs = (ArrayList<Integer>) data[5].chars().map(
                                Character::getNumericValue).boxed().collect(Collectors.toList()); // taskIds

                        ((Moderator) user).rang = Integer.parseInt(data[6]); //rang
                    }
                }
                users.add(user);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


}
