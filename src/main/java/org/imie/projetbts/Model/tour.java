package org.imie.projetbts.Model;

public class tour {
    public String passageDate;
    public String location;
    public int parkingTime;
    public String createdAt;
    public String updatedAt;

    public tour(String passageDate, String location, int parkingTime) {
        this.passageDate = passageDate;
        this.location = location;
        this.parkingTime = parkingTime;
    }

    public String getPassageDate() {
        return passageDate;
    }

    public void setPassageDate(String passageDate) {
        this.passageDate = passageDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
