package ru.otus.dataprocessor;

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
}
