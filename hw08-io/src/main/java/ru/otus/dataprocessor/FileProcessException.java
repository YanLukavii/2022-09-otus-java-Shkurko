package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FileProcessException extends RuntimeException {
    public FileProcessException(Exception ex) {
        super(ex);
    }

    public FileProcessException(String msg) {
        super(msg);
    }

    public FileProcessException(String msg, NullPointerException e) {
        super(msg,e);
    }
    public FileProcessException(String msg, JsonProcessingException e) {
        super(msg,e);
    }
}
