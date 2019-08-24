package com.company.hostel;

import com.company.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class Hostel {
    private List<Floor> floorList = new ArrayList<>(Constants.FLOORS_IN_HOSTEL.getValue());

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(Floor floor) {
        floorList.add(floor);
    }

    public int getFreePlaces() {
        int freePlaces = 0;

        for (Floor floor : floorList) {
            for (Room room : floor.getRoomList()) {
                freePlaces += Constants.STUDENTS_IN_ROOM.getValue() - room.getStudentList().size();
            }
        }

        return freePlaces;
    }
}
