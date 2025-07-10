package com.transporter_assignment.transporter.assignment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LaneQuote {
    @NotNull
    @Positive
    private Integer laneId;
    @NotNull
    @Positive
    private Integer quote;

    public LaneQuote() {
    }

    public LaneQuote(Integer laneId, Integer quote) {
        this.laneId = laneId;
        this.quote = quote;
    }

    public Integer getLaneId() {
        return laneId;
    }

    public void setLaneId(Integer laneId) {
        this.laneId = laneId;
    }

    public Integer getQuote() {
        return quote;
    }

    public void setQuote(Integer quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "LaneQuote{" +
                "laneId=" + laneId +
                ", quote=" + quote +
                '}';
    }
}
