package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    private UserMealWithExcess(Builder builder) {
        this.dateTime = builder.dateTime;
        this.description = builder.description;
        this.calories = builder.calories;
        this.excess = builder.excess;
    }


    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public static class Builder {
        private LocalDateTime dateTime = null;
        private String description = "";
        private int calories = 0;
        private boolean excess = false;

        public Builder() {

        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder excess(boolean excess) {
            this.excess = excess;
            return this;
        }

        public UserMealWithExcess build() {
            return new UserMealWithExcess(this);
        }
    }
}
