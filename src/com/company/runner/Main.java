package com.company.runner;

import com.company.builders.HostelBuilder;
import com.company.builders.VisitorBuilder;
import com.company.constants.Constants;
import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.people.Visitor;
import com.company.builders.StudentBuilder;
import com.company.people.inhabitants.Student;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;
import com.company.util.Calculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hostel hostel = new Hostel();
        new HostelBuilder().build(hostel);
        List<Student> studentList = null;
        Commandant commandant = new Commandant(hostel);
        Security security = new Security();

        Calculations calculations = new Calculations(hostel);
        int monthCounter = 1;

        while (true) {
            System.out.println("\nMonth " + monthCounter + "\n");

            if ((monthCounter - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
                studentList = new ArrayList<>(Constants.STUDENTS_AMOUNT.getValue());
                new StudentBuilder().build(studentList);

                for (Student student : studentList) {
                    commandant.settle(student);
                }

                System.out.println("Students are settled!");
                commandant.makeHeadman();
            }

            // students clean up room
            for (Floor floor : hostel.getFloorList()) {
                for (Room room : floor.getRoomList()) {
                    for (Student student : room.getStudentList()) {
                        boolean cleanOrNot = ((int) (Math.random() * 2)) == 1;

                        if (cleanOrNot) {
                            student.cleanUp(hostel);
                        }
                    }
                }
            }

            //headmen check rooms
            for (Floor floor : hostel.getFloorList()) {
                if (floor.getHeadman() != null) {

                    floor.getHeadman().checkRooms();
                }
            }
            System.out.println("Rooms are checked!");

            // students pay for hostel
            for (Floor floor : hostel.getFloorList()) {
                for (Room room : floor.getRoomList()) {
                    for (Student student : room.getStudentList()) {
                        boolean payOrNot = ((int) (Math.random() * 2)) == 1;

                        if (payOrNot) {
                            student.setHostelPaid(true);
                        }
                    }
                }
            }

            // create new visitors
            List<Visitor> visitorList = new ArrayList<>(Constants.VISITORS_AMOUNT.getValue());
            new VisitorBuilder().build(visitorList);

            // check visitors passes
            security.checkPass(visitorList);

            // is student expelled
            if (monthCounter % (Constants.YEAR_LENGTH.getValue() / 2) == 0) {
                for (Floor floor : hostel.getFloorList()) {
                    for (Room room : floor.getRoomList()) {
                        for (Student student : room.getStudentList()) {
                            boolean expelledOrNot = ((int) (Math.random() * 2)) == 1;

                            if (expelledOrNot) {
                                student.setExpelled(true);
                            }
                        }
                    }
                }
            }

            if (monthCounter == Constants.YEAR_LENGTH.getValue()) {
                calculations.increaseCourseNumber();

                System.out.print("Continue? (y / n): ");
                String answer = new Scanner(System.in).nextLine().toLowerCase();
                while (!answer.equals("y") && !answer.equals("n")) {
                    System.out.print("Invalid answer! Try again: ");
                    answer = new Scanner(System.in).nextLine().toLowerCase();
                }

                if (answer.equals("n")) {
                    break;
                }
            }

            commandant.evict();
            monthCounter++;

            for (Floor floor : hostel.getFloorList()) {
                for (Room room : floor.getRoomList()) {
                    boolean isRoomClean = (int) (Math.random() * 2) == 1;

                    if (!isRoomClean) {
                        room.setCleaned(false);
                    }
                }
            }
        }
    }
}
