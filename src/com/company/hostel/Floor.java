package com.company.hostel;

import com.company.people.inhabitants.Student;
import com.company.constants.Constants;
import com.company.people.staff.Headman;

import java.util.ArrayList;
import java.util.List;

public class Floor extends Hostel {
    private List<Room> roomList = new ArrayList<>(Constants.ROOMS_ON_FLOOR.getValue());
    private int number;
    private Student headman;

    public List<Room> getRoomList() {
        return roomList;
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Headman getHeadman() {
        return (Headman) headman;
    }

    public void setHeadman(Student headman) {
        this.headman = headman;
    }
}
