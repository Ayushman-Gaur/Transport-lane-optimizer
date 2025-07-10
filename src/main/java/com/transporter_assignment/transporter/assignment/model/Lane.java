package com.transporter_assignment.transporter.assignment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Lane {
    @NotNull
    @Positive
    private Integer id;
    @NotNull
    private String origin;
    @NotNull
    private String destination;

    public Lane() {
    }

    public Lane(Integer id, String origin, String destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Lane{id=" + id + ", origin='" + origin + "', destination='" + destination + "'}";
    }
}
