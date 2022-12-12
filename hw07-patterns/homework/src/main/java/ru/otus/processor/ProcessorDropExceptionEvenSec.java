package ru.otus.processor;

import ru.otus.model.Message;

//todo 3
public class ProcessorDropExceptionEvenSec implements Processor {

    DateTimeProvider dateTimeProvider;

    public ProcessorDropExceptionEvenSec(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getTime().getSecond() % 2 == 0) {
            throw new EvenDropException();
        }
        return message;
    }


}
