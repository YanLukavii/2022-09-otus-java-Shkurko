package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//todo 4
public class HistoryListener implements Listener, HistoryReader {

    Map<Long, Message> historyMap = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message messageCopy = msg.toBuilder().build();
        List<String> dataCopy = List.copyOf(messageCopy.getField13().getData());
        ObjectForMessage obj = new ObjectForMessage();
        obj.setData(dataCopy);
        historyMap.put(messageCopy.getId(), messageCopy.toBuilder().field13(obj).build());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(historyMap.get(id));
    }
}
