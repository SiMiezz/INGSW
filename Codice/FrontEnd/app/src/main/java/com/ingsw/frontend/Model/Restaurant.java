package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    //PRIMARY KEY
    @Expose
    @SerializedName("name")
    private String name;

    //ATTRIBUTES
    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("locality")
    private String locality;

    @Expose
    @SerializedName("tables")
    private int tables;

    @Expose
    @SerializedName("seats")
    private int seats;

    @Expose
    @SerializedName("touristic")
    private boolean touristic;

    //REFERENCES
    @Expose
    @SerializedName("user_List")
    private List<User> userList = new ArrayList<>();

    @Expose
    @SerializedName("tables_List")
    private List<TableRestaurant> tablesList = new ArrayList<>();

    @Expose
    @SerializedName("menu_id")
    private Menu menu;

    //CONSTRUCTORS
    public Restaurant() {
    }

    public Restaurant(String name, String description, String locality, int tables, int seats, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
        this.tables = tables;
        this.seats = seats;
        this.touristic = touristic;
    }

    //GETTERS AND SETTERS

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

    public boolean isTouristic() {
        return touristic;
    }

    public void setTouristic(boolean touristic) {
        this.touristic = touristic;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<TableRestaurant> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<TableRestaurant> tablesList) {
        this.tablesList = tablesList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
