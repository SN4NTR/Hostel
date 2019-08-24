package com.company.people.staff;

import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.people.inhabitants.Student;

public class Headman extends Student implements Administration {
    private Hostel hostel;

    public Headman(Hostel hostel) {
        this.hostel = hostel;
    }

    public void checkRooms() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getNumber() == getFloorNumber()) {
                for (Room room : floor.getRoomList()) {
                    if (!room.isCleaned()) {
                        for (Student student : room.getStudentList()) {
                            student.setObservations(student.getObservations() + 1);
                        }

                        System.out.println("Students from room #" + room.getNumber() + " got observations!");
                    } else {
                        System.out.println("Room #" + room.getNumber() + " is clear!");
                    }
                }

                return;
            }
        }
    }
}
