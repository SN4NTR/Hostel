package com.company.constants;

public enum Constants {
    FLOORS_IN_HOSTEL(4),
    ROOMS_ON_FLOOR(4),
    STUDENTS_IN_ROOM(3),
    COURSES_NUMBER(4),
    MAX_OBSERVATIONS(2),
    MIN_COURSE(2),
    STUDENTS_AMOUNT(ROOMS_ON_FLOOR.value * STUDENTS_IN_ROOM.value * FLOORS_IN_HOSTEL.value),
    MAX_VISITORS(5),
    YEAR_LENGTH(12),
    CHANCE(95);

    private int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
