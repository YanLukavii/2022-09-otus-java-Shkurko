package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader()
                .getResourceAsStream(filename))) {
            var jsonFromTheFile = jsonReader.read();
            ObjectMapper objectMapper = new ObjectMapper();
            Object[] obj = jsonFromTheFile.asJsonArray().toArray();
            JsonNode jsonNode;
            for (Object object : obj) {
                jsonNode = objectMapper.readTree(object.toString());
                loadedMeasurementList.add(new Measurement(jsonNode.get("name").asText(),jsonNode.get("value").doubleValue()));
            }
        } catch (NullPointerException e) {
            throw new FileProcessException("Проблема с файлом", e);
        } catch (JsonProcessingException e) {
            throw new FileProcessException("Проблема в содержимом файла", e);
        }
        return loadedMeasurementList;
    }
}
