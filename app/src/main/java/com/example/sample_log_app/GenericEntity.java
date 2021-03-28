package com.example.sample_log_app;

import android.content.Context;

import com.example.sample_log_app.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Serializable;
import java.io.StringReader;


public abstract class GenericEntity implements Serializable {

    public static <T extends GenericEntity> T asEntity(Class<T> clazz, Context context, String filename) {
        final Gson gson = new Gson();
        final String json = IOUtils.loadJsonFromFile(context, filename);
        final JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, clazz);
    }

    public static String asEntity(GenericEntity clazz) {
        final Gson gson = new Gson();
        return gson.toJson(clazz);
    }

    public static <T extends GenericEntity> T asEntity(Class<T> clazz, String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }


}
