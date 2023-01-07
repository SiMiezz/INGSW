package com.ingsw.backend.Model;

import java.util.Date;

public class Stats {

    //REFERENCES
    private Restaurant restaurant;
    private Element element;

    // ATTRIBUTES
    private Date from,to;
    private int frequency;

    //CONSTRUCTORS

    public Stats(Restaurant restaurant, Date from, Date to, int frequency) {
        this.restaurant = restaurant;
        this.from = from;
        this.to = to;
        this.frequency = frequency;
    }

    public Stats() {
    }

    //GETTERS AND SETTERS

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
