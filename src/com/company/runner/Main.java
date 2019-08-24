package com.company.runner;

import com.company.builders.HostelBuilder;
import com.company.constants.Constants;
import com.company.hostel.Hostel;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;
import com.company.logic.Timelapse;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Number of floors in the hostel: ");
        int floorsNumber = getValue();
        Constants.FLOORS_IN_HOSTEL.setValue(floorsNumber);
        System.out.print("Number of rooms on the floor: ");
        int roomsOnFloor = getValue();
        Constants.ROOMS_ON_FLOOR.setValue(roomsOnFloor);
        System.out.print("Number of students in the room: ");
        int studentsInRoom = getValue();
        Constants.STUDENTS_IN_ROOM.setValue(studentsInRoom);
        Constants.STUDENTS_AMOUNT.setValue(floorsNumber * roomsOnFloor * studentsInRoom);

        Hostel hostel = new Hostel();
        HostelBuilder hostelBuilder = new HostelBuilder();
        hostelBuilder.build(hostel);

        Commandant commandant = hostel.getCommandant();
        Security security = hostel.getSecurity();

        Timelapse timelapse = new Timelapse(hostel, security, commandant);

        int month = 1;
        while (true) {
            timelapse.startYear(month);
            month++;

            if ((month - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("\nContinue? (y / n): ");
                String answer = scanner.nextLine().toLowerCase();
                while (!"y".equals(answer) && !"n".equals(answer)) {
                    System.out.print("Invalid answer! Try again: ");
                    answer = scanner.nextLine().toLowerCase();
                }
                if ("n".equals(answer)) {
                    break;
                }
            }
        }
    }

    private static int getValue() {
        Scanner scanner = new Scanner(System.in);

        int value = scanner.nextInt();
        while (value <= 0) {
            System.out.print("Invalid value! Try again: ");
            scanner.nextLine();
            value = scanner.nextInt();
        }

        return value;
    }
}
