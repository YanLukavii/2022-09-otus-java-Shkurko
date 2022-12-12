package ru.otus.ProcessorTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.model.Message;
import ru.otus.processor.DateTimeProvider;
import ru.otus.processor.EvenDropException;
import ru.otus.processor.ProcessorDropExceptionEvenSec;
import java.time.LocalDateTime;
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
        DateTimeProvider dateTimeProvider = Mockito.mock(DateTimeProvider.class);
        Mockito.when(dateTimeProvider.getTime())
                .thenReturn(LocalDateTime.of(2022, 1, 1, 1, 1, 23));
        var process = new ProcessorDropExceptionEvenSec(dateTimeProvider);

        assertThat(message).isEqualTo(process.process(message));
    }

    @Test
    @DisplayName("Тест на четных секундах")
    void testEvenSec() {
        DateTimeProvider dateTimeProvider = Mockito.mock(DateTimeProvider.class);
        Mockito.when(dateTimeProvider.getTime())
                .thenReturn(LocalDateTime.of(1, 1, 1, 1, 1, 22));
        var processor = new ProcessorDropExceptionEvenSec(dateTimeProvider);

        assertThatExceptionOfType(EvenDropException.class).isThrownBy(() -> processor.process(message));
    }
}
