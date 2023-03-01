package ru.otus.services;

public class ClientsAuthServiceImpl implements ClientsAuthService {

    private final static String LOGIN = "usr";
    private final static String PASSWORD = "pwd";

    public ClientsAuthServiceImpl() {
    }

    @Override
    public boolean authenticate(String login, String password) {
        return (LOGIN.equals(login) && PASSWORD.equals(password));
    }
}
