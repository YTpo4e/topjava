package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.dto.MealTo;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal getById(int id) {
        log.info("Get meal by id {} for user with id {}", id, authUserId());
        return service.getById(id, authUserId());
    }

    public List<MealTo> getAll() {
        log.info("Get all meals by user id {}", authUserId());
        return service.getAll(authUserId());
    }

    public Meal save(MealTo meal) {
        log.info("Save meal for user by id {}", authUserId());
        return service.save(meal, authUserId());
    }

    public Meal update(MealTo meal) {
        log.info("Update meal by id {} for user by id {}", meal.getId(), authUserId());
        return service.save(meal, authUserId());
    }

    public boolean delete(int id) {
        log.info("Delete meal by id {} for user by id {}", id, authUserId());
        return service.delete(id, authUserId());
    }

    public List<MealTo> getWithFilterDateTime(LocalDateTime start, LocalDateTime end) {
        log.info("Get meals by user {} from {} to {}", authUserId(), start, end);
        return service.getWithFilterDateTime(start, end, authUserId());
    }

}
