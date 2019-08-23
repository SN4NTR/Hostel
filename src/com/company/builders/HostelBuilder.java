package com.company.builders;

import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;
import com.company.constants.Constants;

public class HostelBuilder {

    public void build(Hostel hostel) {
        for (int i = 0; i < Constants.FLOORS_IN_HOSTEL.getValue(); i++) {
            Floor floor = new Floor();
            floor.setNumber(i + 1);
            hostel.setFloorList(floor);

            for (int j = 0; j < Constants.ROOMS_ON_FLOOR.getValue(); j++) {
                Room room = new Room();
                room.setNumber((i + 1) * 10 + (j + 1));

                boolean isClean = (int) (Math.random() * 2) == 1;
                if (isClean) {
                    room.setCleaned(true);
                }

                floor.setRoomList(room);
            }
        }
    }
}
