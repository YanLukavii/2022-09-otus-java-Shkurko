package ru.otus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.model.Client;
import ru.otus.services.DBServiceClient;

@RestController
public class ClientRestController {

    private final DBServiceClient dbServiceClient;

    public ClientRestController(DBServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @PostMapping("/api/client")
    public Client saveClient(@RequestBody Client client) {
        return dbServiceClient.saveClient(client);
    }
}

