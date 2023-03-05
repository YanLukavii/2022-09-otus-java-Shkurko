package ru.otus.services;

public interface ClientsAuthService {
    boolean authenticate(String login, String password);
}
