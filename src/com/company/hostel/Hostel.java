package com.company.hostel;

import com.company.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class Hostel {
    private List<Floor> floorList = new ArrayList<>(Constants.FLOORS_IN_HOSTEL.getValue());

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }
}
