package ru.otus.ProcessorTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.EvenDropException;
import ru.otus.processor.ProcessorDropExceptionEvenSec;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

//todo 3
public class ProcessorDropExceptionEvenSecTest {

    Message message;

    @BeforeEach
    void initMessage() {
        message = new Message.Builder(1).field10("2").build();
    }

    @Test
    @DisplayName("Тест на нечетных секундах")
    void testOddSec() {
        int seconds = 23;
        new LocalDateTimeSpy(seconds);
        var process = new ProcessorDropExceptionEvenSec(LocalDateTimeSpy::now);

        assertThat(message).isEqualTo(process.process(message));
    }

    @Test
    @DisplayName("Тест на четных секундах")
    void testEvenSec() {
        int seconds = 22;
        new LocalDateTimeSpy(seconds);
        var processor = new ProcessorDropExceptionEvenSec(LocalDateTimeSpy::now);

        assertThatExceptionOfType(EvenDropException.class).isThrownBy(() -> processor.process(message));
    }
}
