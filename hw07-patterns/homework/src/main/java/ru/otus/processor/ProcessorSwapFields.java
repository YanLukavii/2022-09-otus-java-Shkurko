package ru.otus.processor;

import ru.otus.model.Message;

//todo 2
public class ProcessorSwapFields implements Processor {
    @Override
    public Message process(Message message) {
        var temp = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(temp).build();
    }
}
