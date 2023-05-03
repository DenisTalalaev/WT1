package com.oop.project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class JSONDataController implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final RuntimeTypeAdapterFactory<User> typeAdapterFactory = RuntimeTypeAdapterFactory.of(User.class, "type")
            .registerSubtype(Developer.class, "developer").registerSubtype(Admin.class, "admin")
            .registerSubtype(Moderator.class, "moderator").registerSubtype(TechnicalAdmin.class, "TechnicalAdmin");

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JSONDataController())
            .registerTypeAdapterFactory(typeAdapterFactory)
            .create();

    public static void saveDataToFile(ArrayList<User> users, File file) {
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(users, type, fw);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<User> loadDataFromFile(File file) {
        ArrayList<User> users = new ArrayList<User>();
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        try (FileReader fr = new FileReader(file)) {
            users = gson.fromJson(fr, type);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String dateString = json.getAsString();
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        String dateString = date.format(formatter);
        return new JsonPrimitive(dateString);
    }
}
