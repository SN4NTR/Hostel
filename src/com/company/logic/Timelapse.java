package com.company.logic;

import com.company.builders.StudentBuilder;
import com.company.builders.VisitorBuilder;
import com.company.constants.Constants;
import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.people.Visitor;
import com.company.people.inhabitants.Student;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Timelapse {
    private Hostel hostel;
    private Commandant commandant;
    private Security security;

    public Timelapse(Hostel hostel) {
        this.hostel = hostel;
        commandant = hostel.getCommandant();
        security = hostel.getSecurity();
    }

    public void startYear(int month) {
        System.out.println("\nMonth " + month);

        List<Student> studentList;
        if ((month - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
            System.out.println("\nStudent's settlement...\n");
            studentList = new ArrayList<>();
            settleStudent(studentList);

            if (needNewHeadman()) {
                System.out.println();
                commandant.makeHeadman();
            }

            resetObservations();
        }

        System.out.println("\nRooms cleaning...\n");
        clearRoom();
        System.out.println("\nRooms checking...\n");
        checkRoom();
        System.out.println("\nPaying for hostel...\n");
        payForHostel();

        int visitorsAmount = (int) (1 + Math.random() * Constants.MAX_VISITORS.getValue());
        List<Visitor> visitorList = new ArrayList<>(visitorsAmount);
        new VisitorBuilder().build(visitorList, visitorsAmount);

        System.out.println("\nChecking visitors passes...\n");
        security.checkPass(visitorList);

        if (month % (Constants.YEAR_LENGTH.getValue() / 2) == 0) {
            System.out.println("\nSession time...\n");
            passSession();
        }

        if (month % Constants.YEAR_LENGTH.getValue() == 0) {
            increaseCourseNumber();
        }

        System.out.println("\nEvicting...\n");
        commandant.evict();
        if (needNewHeadman()) {
            System.out.println();
            commandant.makeHeadman();
        }

        setRoomCleanness();
        resetHostelPayment();
    }

    private void settleStudent(List<Student> studentList) {
        System.out.print("Add new students? (y / n): ");
        if ("y".equals(getAnswer("y", "n"))) {
            int freePlaces = commandant.getHostelFreePlaces();

            if (freePlaces == 0) {
                System.out.println("There is no free places!");
            } else {
                System.out.print(freePlaces + " free places in the hotel.");
                System.out.print("\nEnter number of students: ");
                Scanner scanner = new Scanner(System.in);
                int studentsAmount = scanner.nextInt();

                while (studentsAmount < 0 || studentsAmount > freePlaces) {
                    System.out.print("Invalid value! Try again: ");
                    studentsAmount = scanner.nextInt();
                }

                scanner.nextLine();
                System.out.println();
                new StudentBuilder().build(studentList, studentsAmount);
            }
        }

        for (Student student : studentList) {
            commandant.settle(student);
        }
    }

    private boolean needNewHeadman() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getHeadman() == null) {
                return true;
            }
        }

        return false;
    }

    private void resetObservations() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    student.setObservations(0);
                }
            }
        }
    }

    private void setRoomCleanness() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                boolean isRoomClean = !getRandom();

                if (isRoomClean) {
                    room.setCleaned(true);
                } else {
                    room.setCleaned(false);
                }
            }
        }
    }

    private void passSession() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean expelledOrNot = !getRandom();

                    if (expelledOrNot) {
                        System.out.println("Student has failed the session!");
                        student.setExpelled(true);
                    } else {
                        System.out.println("Student has passed the session!");
                    }
                }
            }
        }
    }

    private void payForHostel() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean payOrNot = getRandom();

                    if (payOrNot) {
                        System.out.println("Student has paid for hostel!");
                        student.setHostelPaid(true);
                    } else {
                        System.out.println("Student forgot to pay for hostel!");
                    }
                }
            }
        }
    }

    private void resetHostelPayment() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    student.setHostelPaid(false);
                }
            }
        }
    }

    private void checkRoom() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getHeadman() != null) {
                floor.getHeadman().checkRooms();
            } else {
                System.out.println("No any headmen on the floor #" + floor.getNumber());
            }
        }
    }

    private void clearRoom() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean clearOrNot = getRandom();

                    if (clearOrNot) {
                        student.cleanUpRoom(hostel);
                    } else {
                        System.out.println("Student forgot to clean up!");
                    }
                }
            }
        }
    }

    private void increaseCourseNumber() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    if (!student.isExpelled()) {
                        student.setCourse(student.getCourse() + 1);
                    }
                }
            }
        }
    }

    private boolean getRandom() {
        return ((int) (Math.random() * 100) < Constants.CHANCE.getValue());
    }

    private String getAnswer(String variant1, String variant2) {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();

        while (!variant1.equals(answer) && !variant2.equals(answer)) {
            System.out.print("Invalid answer! Try again: ");
            answer = scanner.nextLine().toLowerCase();
        }

        return answer;
    }
}
