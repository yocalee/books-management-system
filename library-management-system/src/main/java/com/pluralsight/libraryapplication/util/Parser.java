package com.pluralsight.libraryapplication.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Parser {
    private Gson gson;

    public Parser(Gson gson) {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public <E> E fromJson(JsonElement element, Class<E> clazz) {
        return gson.fromJson(element, clazz);
    }

    public <E> void toJson(String filePath, E entity) throws IOException {
        String s = gson.toJson(entity);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        try {
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
