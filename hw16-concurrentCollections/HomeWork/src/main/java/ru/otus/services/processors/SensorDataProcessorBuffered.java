package ru.otus.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.SensorDataProcessor;
import ru.otus.api.model.SensorData;
import ru.otus.lib.SensorDataBufferedWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Этот класс нужно реализовать
public class SensorDataProcessorBuffered implements SensorDataProcessor {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);

    private final int bufferSize;
    private final SensorDataBufferedWriter writer;
    private final PriorityBlockingQueue<SensorData> dataBuffer;
    private final List<SensorData> bufferedData;

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
        this.dataBuffer = new PriorityBlockingQueue<>(bufferSize, new ComparingSensorDataByTime());
        this.bufferedData = new ArrayList<>();
    }

    @Override
    public void process(SensorData data) {
        dataBuffer.put(data);
        if (dataBuffer.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        if (dataBuffer.isEmpty()) {
            return;
        }
        try {bufferedData.clear();
             dataBuffer.drainTo(bufferedData);
             writer.writeBufferedData(bufferedData);
        } catch (Exception e) {
            log.error("Ошибка в процессе записи буфера", e);
        }
    }

    @Override
    public void onProcessingEnd() {
        bufferedData.clear();
        flush();
    }
}