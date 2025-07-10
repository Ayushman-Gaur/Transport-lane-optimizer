package com.transporter_assignment.transporter.assignment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AssignmentRequest {
    @NotNull
    @Positive
    private Integer maxTransporters;

    public AssignmentRequest() {
    }
    public AssignmentRequest(Integer maxTransporters) {
        this.maxTransporters = maxTransporters;
    }

    public Integer getMaxTransporters() {
        return maxTransporters;
    }

    public void setMaxTransporters(Integer maxTransporters) {
        this.maxTransporters = maxTransporters;
    }
}
