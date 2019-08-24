package com.company.runner;

import com.company.builders.HostelBuilder;
import com.company.hostel.Hostel;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;
import com.company.logic.Timelapse;

public class Main {

    public static void main(String[] args) {
        Hostel hostel = new Hostel();
        HostelBuilder hostelBuilder = new HostelBuilder();
        hostelBuilder.build(hostel);

        Commandant commandant = new Commandant(hostel);
        Security security = new Security();

        Timelapse timelapse = new Timelapse(hostel, security, commandant);
        timelapse.start();
    }
}
