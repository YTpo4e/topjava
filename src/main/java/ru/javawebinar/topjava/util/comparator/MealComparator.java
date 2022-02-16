package ru.javawebinar.topjava.util.comparator;

import ru.javawebinar.topjava.model.Meal;

import java.util.Comparator;

public class MealComparator implements Comparator<Meal> {

    @Override
    public int compare(Meal o1, Meal o2) {
        return o2.getDateTime().compareTo(o1.getDateTime());
    }
}
