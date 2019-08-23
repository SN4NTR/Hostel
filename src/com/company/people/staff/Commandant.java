package com.company.people.staff;

import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.constants.Constants;
import com.company.people.inhabitants.Student;

import java.util.List;

public class Commandant implements Administration {
    private Hostel hostel;

    public Commandant(Hostel hostel) {
        this.hostel = hostel;
    }

    public void makeHeadman() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getHeadman() == null) {
                for (Room room : floor.getRoomList()) {
                    List<Student> studentList = room.getStudentList();

                    for (int i = 0; i < studentList.size(); i++) {
                        if (studentList.get(i).getCourse() >= Constants.MIN_COURSE.getValue()) {
                            Student headman = new Headman(hostel);
                            headman.setCourse(studentList.get(i).getCourse());
                            headman.setObservations(studentList.get(i).getObservations());
                            headman.setExpelled(studentList.get(i).isExpelled());
                            headman.setHostelPaid(studentList.get(i).isHostelPaid());
                            headman.setFloorNumber(studentList.get(i).getFloorNumber());
                            headman.setRoomNumber(studentList.get(i).getRoomNumber());

                            floor.setHeadman(headman);
                            studentList.set(i, headman);
                        }
                    }
                }
            }
        }

        System.out.println("Headmen are set!");
    }

    public void settle(Student student) {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                if (room.getFreePlaces() > 0) {
                    student.setFloorNumber(floor.getNumber());
                    student.setRoomNumber(room.getNumber());
                    room.setStudentList(student);
                    System.out.println("Student has been settled in room #" + room.getNumber());
                    return;
                }
            }
        }

        System.out.println("There is no free places :(");
    }

    public void evict() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                List<Student> studentList = room.getStudentList();

                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).isExpelled() || !studentList.get(i).isHostelPaid() ||
                            studentList.get(i).getCourse() > Constants.COURSES_NUMBER.getValue() ||
                            studentList.get(i).getObservations() == Constants.MAX_OBSERVATIONS.getValue()) {

                        if (studentList.get(i) instanceof Headman) {
                            floor.setHeadman(null);
                        }

                        studentList.remove(i);
                        new Security().helpEvict();
                        System.out.println("Student has been evicted from room #" + room.getNumber() + "!");
                    }
                }
            }
        }
    }
}
