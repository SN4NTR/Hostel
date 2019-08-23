package com.company.util;

import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.people.inhabitants.Student;

public class Calculations {
    private Hostel hostel;

    public Calculations(Hostel hostel) {
        this.hostel = hostel;
    }

    public void increaseCourseNumber() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                if (!room.isCleaned()) {
                    for (Student student : room.getStudentList()) {
                        student.setCourse(student.getCourse() + 1);
                    }
                }
            }
        }
    }
}
