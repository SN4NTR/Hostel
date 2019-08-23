package com.company.people.inhabitants;

import com.company.hostel.Floor;
import com.company.hostel.Hostel;
import com.company.hostel.Room;

public class Student {
    private int course;
    private int observations;
    private boolean isExpelled;
    private boolean isHostelPaid = true;
    private int floorNumber;
    private int roomNumber;

    public void cleanUp(Hostel hostel) {
        for (Floor floor : hostel.getFloorList()) {
            if (floor.getNumber() == floorNumber) {
                for (Room room : floor.getRoomList()) {
                    if (room.getNumber() == roomNumber) {
                        if (!room.isCleaned()) {
                            room.setCleaned(true);
                            System.out.println("Room #" + roomNumber + " is cleaned!");
                        } else {
                            System.out.println("Room #" + roomNumber + " is already cleaned!");
                        }
                    }
                }
            }
        }
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getObservations() {
        return observations;
    }

    public void setObservations(int observations) {
        this.observations = observations;
    }

    public boolean isExpelled() {
        return isExpelled;
    }

    public void setExpelled(boolean isExpelled) {
        this.isExpelled = isExpelled;
    }

    public boolean isHostelPaid() {
        return isHostelPaid;
    }

    public void setHostelPaid(boolean isHostelPaid) {
        this.isHostelPaid = isHostelPaid;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
