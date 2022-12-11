package ru.otus.dataprocessor;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class FileSerializer implements Serializer {

    String filename;

    public FileSerializer(String fileName) {
        this.filename = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        var gson = new Gson();
        try (var out = new PrintWriter(new FileWriter(filename))) {
            out.write(gson.toJson(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
