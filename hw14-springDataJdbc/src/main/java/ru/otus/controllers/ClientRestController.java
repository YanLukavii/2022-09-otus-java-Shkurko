package ru.otus.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.model.Client;
import ru.otus.services.DBServiceClient;

@RestController
@AllArgsConstructor
public class ClientRestController {

    private final DBServiceClient dbServiceClient;

    @PostMapping("/api/client")
    public Client saveClient(@RequestBody Client client) {
        return dbServiceClient.saveClient(client);
    }
}

