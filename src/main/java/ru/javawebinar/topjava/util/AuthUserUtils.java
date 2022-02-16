package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

public class AuthUserUtils {

    public static final int DEFAULT_USER_ID = 1;
    public static final Role DEFAULT_USER_ROLE = Role.USER;
    public static final User DEFAULT_USER = new User(1, "test", "test@gmail.com", "1234", DEFAULT_USER_ROLE);

    private AuthUserUtils() {

    }

}
