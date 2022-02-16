package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.AuthUserUtils;
import ru.javawebinar.topjava.util.comparator.UserComparator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        save(AuthUserUtils.DEFAULT_USER);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete user by {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.getAndIncrement());
            repository.put(user.getId(), user);
        }
        repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get user by {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> sortedUserByEmail = new ArrayList<>(repository.values());
        sortedUserByEmail.sort(new UserComparator());
        return sortedUserByEmail;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new NotFoundException(String.format("User by email '%s' not found", email)));
    }
}
