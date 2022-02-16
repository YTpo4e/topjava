package ru.javawebinar.topjava.util.exception;

public class AuthException extends RuntimeException {
    public AuthException(int id) {
        super(String.format("User by %d does not have access to the resource", id));
    }
}
