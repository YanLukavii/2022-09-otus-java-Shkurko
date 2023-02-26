package ru.otus.services;

public class ClientsAuthServiceImpl implements ClientsAuthService {

    private final String LOGIN = "usr";
    private final String PASSWORD = "pwd";

    public ClientsAuthServiceImpl() {
    }

    @Override
    public boolean authenticate(String login, String password) {
        return (login.equals(LOGIN) && password.equals(PASSWORD));
    }
}
