package com.company.people.staff;

import com.company.people.Visitor;

import java.util.List;

public class Security implements Administration {

    public void checkPass(List<Visitor> visitorList) {
        for (Visitor visitor : visitorList) {
            if (visitor.getPass()) {
                System.out.println("This visitor can enter into the hostel!");
            } else {
                System.out.println("This visitor can't enter into the hostel!");
            }
        }
    }

    public void helpEvict(String reason, int roomNumber) {
        System.out.println("Student has been evicted from room #" + roomNumber +
                " because of " + reason);
    }
}
