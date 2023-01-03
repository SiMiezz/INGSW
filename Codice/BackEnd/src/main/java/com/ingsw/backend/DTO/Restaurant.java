package com.ingsw.backend.DTO;

public class Restaurant {
    private String name,description,locality;
    private int tables,seats;
    private boolean tourist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isTourist() {
        return tourist;
    }

    public void setTourist(boolean tourist) {
        this.tourist = tourist;
    }
}
