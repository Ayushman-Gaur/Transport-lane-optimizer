package com.transporter_assignment.transporter.assignment.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class Transporter {

    @NotNull
    @Positive
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    @Valid
    private List<LaneQuote> laneQuotes;


    public Transporter() {
    }

    public Transporter(Integer id, String name, List<LaneQuote> laneQuotes) {
        this.id = id;
        this.name = name;
        this.laneQuotes = laneQuotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LaneQuote> getLaneQuotes() {
        return laneQuotes;
    }

    public void setLaneQuotes(List<LaneQuote> laneQuotes) {
        this.laneQuotes = laneQuotes;
    }

    @Override
    public String toString() {
        return "Transporter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", laneQuotes=" + laneQuotes +
                '}';
    }
}
