package com.oop.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class CRUD implements Serializable {



    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> newUsers) {
        users.clear();
        users.addAll(newUsers);
    }

    public CRUD() {}

    public void hardInitialise() {
        //Developers
        ArrayList<Character> arr = new ArrayList<>();
        for (Character ch: "abcdefghijklmnop".toCharArray()
             ) {
            arr.add(ch);
        }
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Developer developer = new Developer();
            developer.setName("Developer " + arr.get(i + 1));
            developer.setBirth(User.generateRandomDate());
            developer.setWallet("developer" + arr.get(i) + ".near");
            developer.setStat(new Stats(random.nextInt(100), random.nextInt(100)));
            for (int j = 0; j < random.nextInt(5) + 2; j++) {
                Transaction transaction = new Transaction();
                transaction.setAmount(random.nextFloat() * 15 + 10);
                transaction.setWalletFrom("nearcrowd.near");
                transaction.setWalletTo(developer.getWallet());
                developer.addTransaction(transaction);
            }
            users.add(developer);
        }
        //Technical Admins
        for (int i = 0; i < 5; i++) {
            TechnicalAdmin technicalAdmin = new TechnicalAdmin();
            technicalAdmin.setName("TechnocalAdmin " + arr.get(i));
            technicalAdmin.setBirth(User.generateRandomDate());
            var taskIds = new ArrayList<Integer>() {{
                add(0);
                add(2);
            }};
            technicalAdmin.setTaskIDs(taskIds);
            technicalAdmin.setSalary(random.nextInt(5000));
            technicalAdmin.setRoot(random.nextBoolean());
            for (int j = 0; j < 3; j++) {
                technicalAdmin.addPermission("permission" + j);
            }
            users.add(technicalAdmin);
        }
        //Moderators
        for (int i = 0; i < 5; i++) {
            Moderator moderator = new Moderator();
            moderator.setName("Moderator " + arr.get(i));
            moderator.setBirth(User.generateRandomDate());
            moderator.setRoot(random.nextBoolean());
            moderator.setSalary(random.nextInt(1500) + 5);
            var taskIds = new ArrayList<Integer>() {{
                add(0);
                add(1);
            }};
            moderator.setTaskIDs(taskIds);
            moderator.setRang(random.nextInt(3) + 1);

            users.add(moderator);
        }

    }

    //create
    public void addDeveloper(Developer developer) {
        users.add(developer);
    }

    public void addTechnicalAdmin(TechnicalAdmin technicalAdmin) {
        users.add(technicalAdmin);
    }

    public void addModerator(Moderator moderator) {
        users.add(moderator);
    }

    //redact
    public void redactDeveloper(Developer oldDeveloper, Developer newDeveloper) {
        int oldDeveloperId = users.indexOf(oldDeveloper);
        users.set(oldDeveloperId, newDeveloper);
    }

    public void redactTechnicalAdmin(TechnicalAdmin oldTechnicalAdmin, TechnicalAdmin newTechnicalAdmin) {
        int oldTechnicalAdminId = users.indexOf(oldTechnicalAdmin);
        users.set(oldTechnicalAdminId, newTechnicalAdmin);
    }

    public void redactModerator(Moderator oldModerator, Moderator newModerator) {
        int oldModeratorId = users.indexOf(oldModerator);
        users.set(oldModeratorId, oldModerator);
    }

    //delete
    public void deleteUser(User user) {
        users.remove(user);
    }


    public void printTable() {
        for (User user: users
             ) {
            System.out.println(user.toString());
        }
    }


    public void add(User user) {
        users.add(user);
    }
}
