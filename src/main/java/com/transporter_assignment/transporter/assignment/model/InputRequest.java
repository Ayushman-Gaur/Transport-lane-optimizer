package com.transporter_assignment.transporter.assignment.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class InputRequest {
    @NotNull
    @Valid
    private List<Lane> lanes;
    @NotNull
    @Valid
    private List<Transporter> transporters;

    public InputRequest() {
    }

    public InputRequest(List<Lane> lanes, List<Transporter> transporters) {
        this.lanes = lanes;
        this.transporters = transporters;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public void setLanes(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public List<Transporter> getTransporters() {
        return transporters;
    }

    public void setTransporters(List<Transporter> transporters) {
        this.transporters = transporters;
    }
}
