package com.oop.project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class JSONDataController extends SerializeController implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>  {
    private String ext;

    private String extention = "*.json";
    private String info = "JSON файлы";

    public String getExtention() {
        return extention;
    }

    public String getInfo() {
        return info;
    }

    public JSONDataController(){
        ext = ".json";
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final RuntimeTypeAdapterFactory<User> typeAdapterFactory = RuntimeTypeAdapterFactory.of(User.class, "type")
            .registerSubtype(Developer.class, "developer").registerSubtype(Admin.class, "admin")
            .registerSubtype(Moderator.class, "moderator").registerSubtype(TechnicalAdmin.class, "TechnicalAdmin");

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JSONDataController())
            .registerTypeAdapterFactory(typeAdapterFactory)
            .create();

    @Override
    public String getExt() {
        return ext;
    }

    @Override
    public byte[] saveDataToByteArray(ArrayList<User> users) {
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        String jsonData = gson.toJson(users, type);
        return jsonData.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ArrayList<User> loadDataFromByteArray(byte[] data) {
        String jsonData = new String(data, StandardCharsets.UTF_8);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        return gson.fromJson(jsonData, type);
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
