package com.company.hostel;

import com.company.constants.Constants;
import com.company.people.inhabitants.Student;

import java.util.ArrayList;
import java.util.List;

public class Room extends Floor {
    private List<Student> studentList = new ArrayList<>(Constants.STUDENTS_IN_ROOM.getValue());
    private int number;
    private boolean isCleaned;
    private int freePlaces = Constants.STUDENTS_IN_ROOM.getValue();

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Student student) {
        studentList.add(student);
        freePlaces--;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isCleaned() {
        return isCleaned;
    }

    public void setCleaned(boolean isCleaned) {
        this.isCleaned = isCleaned;
    }

    @Override
    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}
