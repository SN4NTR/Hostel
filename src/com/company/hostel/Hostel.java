package com.company.hostel;

import com.company.constants.Constants;
import com.company.people.staff.Commandant;
import com.company.people.staff.Security;

import java.util.ArrayList;
import java.util.List;

public class Hostel {
    private List<Floor> floorList = new ArrayList<>(Constants.FLOORS_IN_HOSTEL.getValue());
    private Commandant commandant;
    private Security security;

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }

    public Commandant getCommandant() {
        return commandant;
    }

    public void setCommandant(Commandant commandant) {
        this.commandant = commandant;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
