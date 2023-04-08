package com.oop.project;

import java.util.ArrayList;
import java.util.Random;

public class CRUD {

    private ArrayList<User> users = new ArrayList<>();

    public CRUD() {
        Task.initialiseTasks();
    }

    public void hardInitialise() {
        //Developers
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Developer developer = new Developer();
            developer.setName("Developer " + (i + 1));
            developer.setBirth(User.generateRandomDate());
            developer.setWallet("developer" + i + ".near");
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
            technicalAdmin.setName("TechnocalAdmin" + i);
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
            moderator.setName("Moderator" + i);
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
    public void deleteDeveloper(Developer developer) {
        users.remove(developer);
    }

    public void deleteTechnicalAdmin(TechnicalAdmin technicalAdmin) {
        users.remove(technicalAdmin);
    }

    public void deleteModerator(Moderator moderator) {
        users.remove(moderator);
    }

    public void printTable() {
        for (User user: users
             ) {
            System.out.println(user.toString());
        }
    }


}
