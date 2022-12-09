package ru.otus.ProcessorTests;

import ru.otus.processor.DateTimeProvider;

import java.time.LocalDateTime;

//todo 3
public class LocalDateTimeSpy implements DateTimeProvider {

    static LocalDateTime localDateTime;

    public LocalDateTimeSpy(int second) {
        localDateTime = LocalDateTime.of(1, 1, 1, 1, 1, second);
    }

    public static LocalDateTime now() {
        return localDateTime;
    }

    @Override
    public LocalDateTime getTime() {
        return localDateTime;
    }
}
