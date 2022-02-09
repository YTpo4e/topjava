package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoIml;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static String INSERT_OR_EDIT = "/mealForm.jsp";
    private static String LIST_MEALS = "/meals.jsp";
    private final MealDao mealDao = new MealDaoIml();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action != null && action.equals("delete")) {
            log.info("Request to delete meal");
            UUID id = UUID.fromString(req.getParameter("id"));
            mealDao.deleteById(id);
            forward = LIST_MEALS;
            req.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.of(0, 0),
                    LocalTime.of(23, 0), 2000));
        } else if (action != null && action.equals("create")) {
            log.info("Request to create/update meal");
            forward = INSERT_OR_EDIT;
            String stringId = req.getParameter("id");
            Meal newMeal = new Meal();
            if (stringId != null) {
                newMeal = mealDao.getById(UUID.fromString(stringId));
            }
            req.setAttribute("meal", newMeal);
        } else {
            log.info("Request get meals");
            forward = LIST_MEALS;
            req.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.of(0, 0),
                    LocalTime.of(23, 0), 2000));
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Request create new meal: method post");
        req.setCharacterEncoding("UTF8");

        String stringId = req.getParameter("id");
        UUID id = null;
        if (stringId != null) {
            id = UUID.fromString(stringId);
        }
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        mealDao.save(new Meal(id, dateTime, description, calories));

        resp.sendRedirect("meals");
    }
}
