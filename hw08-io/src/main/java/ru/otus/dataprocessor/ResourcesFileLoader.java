package ru.otus.dataprocessor;

import com.google.gson.Gson;
import jakarta.json.Json;
import ru.otus.model.Measurement;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {

  private final String filename;

    public ResourcesFileLoader(String fileName) {
        this.filename = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        List<Measurement> loadedMeasurementList = new ArrayList<>();
        try {
            try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader()
                    .getResourceAsStream(filename))) {
                var jsonFromTheFile = jsonReader.read();
                Object[] obj = jsonFromTheFile.asJsonArray().toArray();
                var gson = new Gson();
                for (Object object:obj) {
                    loadedMeasurementList.add(gson.fromJson(object.toString(),Measurement.class));
                }
            }
        }catch (NullPointerException e) {
            throw new FileProcessException("Вернул нулл при загрузке json`a из файла");
        }

        return loadedMeasurementList;
    }
}
