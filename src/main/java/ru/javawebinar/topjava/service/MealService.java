package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.dto.MealTo;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.createMealWithUserId;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredTos;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal getById(int id, int userId) {
        return repository.get(id, userId);
    }

    public List<MealTo> getAll(int userId) {
        Collection<Meal> all = repository.getAll(userId);
        return getTos(all, authUserCaloriesPerDay());
    }

    public Meal save(MealTo mealTo, int userId) {
        Meal mealWithUserId = createMealWithUserId(mealTo, userId);
        return repository.save(mealWithUserId, userId);
    }

    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    public List<MealTo> getWithFilterDateTime(LocalDateTime start, LocalDateTime end, int userId) {
        Collection<Meal> all = repository.getAll(userId);
        return getFilteredTos(all, authUserCaloriesPerDay(), start.toLocalTime(), end.toLocalTime());
    }
}
