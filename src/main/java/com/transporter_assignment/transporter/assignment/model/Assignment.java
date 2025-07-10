package com.transporter_assignment.transporter.assignment.model;

public class Assignment {
    private Integer laneId;
    private Integer transporterId;

    // Constructors
    public Assignment() {}

    public Assignment(Integer laneId, Integer transporterId) {
        this.laneId = laneId;
        this.transporterId = transporterId;
    }

    // Getters and Setters
    public Integer getLaneId() { return laneId; }
    public void setLaneId(Integer laneId) { this.laneId = laneId; }

    public Integer getTransporterId() { return transporterId; }
    public void setTransporterId(Integer transporterId) { this.transporterId = transporterId; }
}
