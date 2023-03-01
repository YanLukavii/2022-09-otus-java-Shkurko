package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DBServiceClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientsApiServlet extends HttpServlet {

    private static final String NAME_PARAM = "name";
    private static final String ADDRESS_PARAM = "address";
    private static final String PHONE_PARAM = "phone";
    private final DBServiceClient dbServiceClient;
    private final Gson gson;

    public ClientsApiServlet(DBServiceClient dbServiceClient, Gson gson) {

        this.gson = gson;
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Client> clients = dbServiceClient.findAll();
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();

        List<Map<String, Object>> clientList = new ArrayList<>();
        for (Client client : clients) {
            Map<String, Object> clientMap = new HashMap<>();
            clientMap.put("name", client.getName());
            if (client.getAddress() == null) {
                clientMap.put("address", "");
            } else
                clientMap.put("address", client.getAddress().getStreet());

            List<Map<String, String>> numbers = new ArrayList<>();
            for (Phone phone : client.getPhones()) {
                Map<String, String> numberMap = new HashMap<>();
                numberMap.put("number", phone.getNumber());
                numbers.add(numberMap);
            }
            clientMap.put("numbers", numbers);
            clientList.add(clientMap);
        }
        out.print(gson.toJson(clientList));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter(NAME_PARAM);
        String address = request.getParameter(ADDRESS_PARAM);
        String phone = request.getParameter(PHONE_PARAM);
        dbServiceClient.saveClient(new Client(name, new Address(null, address),
                List.of(new Phone(null, phone))));
        response.sendRedirect("/clients");
    }
}
