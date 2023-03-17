package ru.otus.services.processors;

import ru.otus.api.model.SensorData;

import java.util.Comparator;

public class ComparingSensorDataByTime implements Comparator<SensorData> {

    @Override
    public int compare(SensorData o1, SensorData o2) {
        return  o1.getMeasurementTime().compareTo(o2.getMeasurementTime());
    }
}

