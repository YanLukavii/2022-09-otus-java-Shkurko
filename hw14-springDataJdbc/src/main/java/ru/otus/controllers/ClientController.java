package ru.otus.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.model.Client;
import ru.otus.services.DBServiceClient;

import java.util.List;

@Controller
public class ClientController {

    private final String osData;
    private final String applicationYmlMessage;
    private final DBServiceClient dbServiceClient;

    public ClientController(@Value("${app.client-list-page.msg:Тут может находиться ваша реклама}")
                                    String applicationYmlMessage,
                            @Value("OS: #{T(System).getProperty(\"os.name\")}, " +
                                    "JDK: #{T(System).getProperty(\"java.runtime.version\")}")
                                    String osData,
                            DBServiceClient dbServiceClient) {
        this.applicationYmlMessage = applicationYmlMessage;
        this.osData = osData;
        this.dbServiceClient = dbServiceClient;
    }

    @GetMapping({"/", "/client/list"})
    public String clientsListView(Model model) {
        List<Client> clients = dbServiceClient.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("osData", osData);
        model.addAttribute("applicationYmlMessage", applicationYmlMessage);
        return "clientsList";
    }

    @GetMapping("/client/create")
    public String clientCreateView(Model model) {
        model.addAttribute("client", new Client());
        return "clientCreate";
    }

}
