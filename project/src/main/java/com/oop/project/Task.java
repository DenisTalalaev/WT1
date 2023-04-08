package com.oop.project;

import java.util.ArrayList;

public class Task {
    private String name;
    private String about;
    private double rewardPerTask;
    private double rewardPerReview;
    private int index;

    public static final Task nightSky = new Task();
    public static final Task moonDance = new Task();
    public static final Task sunShine = new Task();
    public static final Task acade = new Task();

    public static String taskNamesByList(ArrayList<Integer> ids){
        String string = "";
        for (int id: ids
             ) {
            string += Task.ordinate(id).name + " ";
        }
        return string.trim();
    }

    public static void initialiseTasks(){
        nightSky.name = "NightSky";
        nightSky.about = "Создайте автономный модуль генерации заданий для обучения нейросети по заданному описанию.";
        nightSky.rewardPerTask = 3.5;
        nightSky.rewardPerReview = 0.7;
        nightSky.index = 0;

        moonDance.name = "MoonDance";
        moonDance.about = "Решите представленную алгоритмическую задачу из спортивного программирования с описанием мыслительного процесса.";
        moonDance.rewardPerTask = 3.5;
        moonDance.rewardPerReview = 0.5;
        moonDance.index = 1;

        sunShine.name = "SunShine";
        sunShine.about = "Напишите уникальный текст на представленную тему не менее чем на 2000 символов и создайте 5 вопросов, которые проверят понимание прочитанного текста. Уникальность текста должна быть не менее 60%.";
        sunShine.rewardPerTask = 1.1;
        sunShine.rewardPerReview = 0.33;
        sunShine.index = 2;
        
        acade.name = "Acade";
        acade.about = "Разметьте представленную главу учебника по программированию в формате Acade и создайте 3 задачи на понимание прочитанного и 2 алгоритмические задачи по описанной теме.";
        acade.rewardPerTask = 12;
        acade.rewardPerReview = 3.5;
        acade.index = 3;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public double getRewardPerTask() {
        return rewardPerTask;
    }

    public double getRewardPerReview() {
        return rewardPerReview;
    }

    public int getIndex() {
        return index;
    }

    public static Task ordinate(int index){
        switch (index){
            case 0: return Task.nightSky;
            case 1: return Task.moonDance;
            case 2: return Task.sunShine;
            case 3: return Task.acade;
        }
        return new Task();
    }
}
