package com.company.hostel;

import com.company.constants.Constants;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;

import java.util.ArrayList;
import java.util.List;

public class Hostel {
    private List<Floor> floorList = new ArrayList<>(Constants.FLOORS_IN_HOSTEL.getValue());
    private Commandant commandant = new Commandant(this);
    private Security security = new Security();

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }

    public Commandant getCommandant() {
        return commandant;
    }

    public Security getSecurity() {
        return security;
    }
}
