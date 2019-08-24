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
    private Security security;
    private Commandant commandant;

    public Timelapse(Hostel hostel, Security security, Commandant commandant) {
        this.hostel = hostel;
        this.security = security;
        this.commandant = commandant;
    }

    public void start() {
        int month = 1;

        while (true) {
            System.out.println("Month " + month);

            List<Student> studentList;
            if ((month - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
                studentList = new ArrayList<>();
                settleStudent(month, studentList);

                if (needNewHeadman()) {
                    System.out.println();
                    commandant.makeHeadman();
                }
            }

            System.out.println("\nRoom cleaning...\n");
            roomCleaning();
            System.out.println("\nRoom checking...\n");
            roomChecking();
            System.out.println("\nPaying for hostel...\n");
            payingForHostel();

            int visitorsAmount = (int) (1 + Math.random() * Constants.MAX_VISITORS.getValue());
            List<Visitor> visitorList = new ArrayList<>(visitorsAmount);
            new VisitorBuilder().build(visitorList, visitorsAmount);
            System.out.println("\nChecking visitors passes...\n");
            security.checkPass(visitorList);

            if (month % (Constants.YEAR_LENGTH.getValue() / 2) == 0) {
                System.out.println("\nSession time...\n");
                isSessionPassed();
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

            month++;
            setRoomCleanness();
            setHostelPayment();

            if ((month - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
                System.out.print("Continue? (y / n): ");
                if ("n".equals(getAnswer("y", "n"))) {
                    break;
                }

                resetObservations();
            }
        }
    }

    private void settleStudent(int month, List<Student> studentList) {
        System.out.println("\nStudent's settlement...\n");

        if (month == 1) {
            new StudentBuilder().build(studentList, Constants.STUDENTS_AMOUNT.getValue());

            for (Student student : studentList) {
                commandant.settle(student);
            }
        } else {
            System.out.print("\nAdd new students? (y / n): ");
            if ("y".equals(getAnswer("y", "n"))) {
                addNewStudents(studentList);

                for (Student student : studentList) {
                    commandant.settle(student);
                }
            }
        }
    }

    private void addNewStudents(List<Student> studentList) {
        int freePlaces = hostel.getFreePlaces();

        if (freePlaces == 0) {
            System.out.println("There is no free places!");
        } else {
            System.out.print(freePlaces + " free places in the hotel." + "\nEnter students number: ");
            Scanner scanner = new Scanner(System.in);
            int studentsAmount = scanner.nextInt();
            while (studentsAmount <= 0 || studentsAmount > freePlaces) {
                System.out.print("Invalid value! Try again: ");
                studentsAmount = scanner.nextInt();
            }

            new StudentBuilder().build(studentList, studentsAmount);
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
                boolean isRoomClean = (int) (Math.random() * 2) == 1;

                if (isRoomClean) {
                    room.setCleaned(true);
                } else {
                    room.setCleaned(false);
                }
            }
        }
    }

    private void isSessionPassed() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean expelledOrNot = ((int) (Math.random() * 2)) == 1;

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

    private void payingForHostel() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean payOrNot = ((int) (Math.random() * 2)) == 1;

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

    private void setHostelPayment() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    student.setHostelPaid(false);
                }
            }
        }
    }

    private void roomChecking() {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getHeadman() != null) {
                floor.getHeadman().checkRooms();
            }
        }
    }

    private void roomCleaning() {
        for (Floor floor : hostel.getFloorList()) {
            for (Room room : floor.getRoomList()) {
                for (Student student : room.getStudentList()) {
                    boolean cleanOrNot = ((int) (Math.random() * 2)) == 1;

                    if (cleanOrNot) {
                        student.cleanUp(hostel);
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
                if (!room.isCleaned()) {
                    for (Student student : room.getStudentList()) {
                        if (!student.isExpelled()) {
                            student.setCourse(student.getCourse() + 1);
                        }
                    }
                }
            }
        }
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
