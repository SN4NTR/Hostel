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
        Hostel hostel = new Hostel();
        HostelBuilder hostelBuilder = new HostelBuilder();
        hostelBuilder.build(hostel);

        Commandant commandant = new Commandant(hostel);
        Security security = new Security();

        Timelapse timelapse = new Timelapse(hostel, security, commandant);

        int month = 1;
        while (true) {
            timelapse.startYear(month);
            month++;

            if ((month - 1) % Constants.YEAR_LENGTH.getValue() == 0) {
                System.out.print("\nContinue? (y / n): ");
                Scanner scanner = new Scanner(System.in);
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
}
