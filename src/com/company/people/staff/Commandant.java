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

    public int getHostelFreePlaces() {
        int freePlaces = 0;

        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                freePlaces += Constants.STUDENTS_IN_ROOM.getValue() - room.getStudentList().size();
            }
        }

        return freePlaces;
    }

    public void makeHeadman() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getHeadman() == null) {
                for (Room room : floor.getRoomList()) {
                    if (floor.getHeadman() == null) {
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
                                System.out.println("Headman is set on floor #" + floor.getNumber());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void settle(Student student) {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                if (room.getFreePlaces() > 0) {
                    student.setFloorNumber(floor.getNumber());
                    student.setRoomNumber(room.getNumber());
                    room.addStudent(student);
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
                    String reason = null;
                    boolean needEvict = false;

                    if (studentList.get(i).isExpelled()) {
                        needEvict = true;
                        reason = "expelling!";
                    } else if (!studentList.get(i).isHostelPaid()) {
                        needEvict = true;
                        reason = "non-payment!";
                    } else if (studentList.get(i).getCourse() > Constants.COURSES_NUMBER.getValue()) {
                        needEvict = true;
                        reason = "graduation!";
                    } else if (studentList.get(i).getObservations() == Constants.MAX_OBSERVATIONS.getValue()) {
                        needEvict = true;
                        reason = studentList.get(i).getObservations() + " observations!";
                    }

                    if (needEvict) {
                        new Security().helpEvict(reason, room.getNumber());

                        if (studentList.get(i) instanceof Headman) {
                            floor.setHeadman(null);
                        }

                        room.setFreePlaces(room.getFreePlaces() + 1);
                        studentList.remove(i);
                        i--;
                    } else {
                        System.out.println("Student stay live in room #" + room.getNumber());
                    }
                }
            }
        }
    }
}
