package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MealDaoIml implements MealDao {
    private static final Logger log = LoggerFactory.getLogger(MealDaoIml.class);
    private Map<UUID, Meal> storage = new ConcurrentHashMap<>();

    {
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        log.info("Get all meals from storage");
        return new ArrayList<>(storage.values());
    }

    @Override
    public Meal getById(UUID id) {
        log.info("Get meal by id '{}' from storage", id);
        Meal meal = storage.get(id);
        if (meal == null) {
            log.info("Meal by id '{}' not found", id);
        }
        return meal;
    }

    @Override
    public Meal save(Meal savingMeal) {
        log.info("Saving meal '{}'", savingMeal);
        UUID id = UUID.randomUUID();
        if (savingMeal.getId() != null) {
            id = savingMeal.getId();
        } else {
            Meal meal = storage.get(id);
            while (meal != null) {
                id = UUID.randomUUID();
                meal = storage.get(id);
            }
            savingMeal.setId(id);
        }
        Meal savedMeal = storage.put(id, savingMeal);
        log.info("Meal saved by id '{}'", id);
        return savedMeal;
    }

    @Override
    public Meal deleteById(UUID id) {
        log.info("Deleting meal by id");
        Meal removedMeal = storage.remove(id);
        log.info("Meal '{}' deleted by id '{}'", removedMeal, id);
        return removedMeal;
    }
}
