package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.UUID;

public interface MealDao {
    List<Meal> getAll();

    Meal getById(UUID id);

    Meal save(Meal meal);

    Meal deleteById(UUID id);
}
