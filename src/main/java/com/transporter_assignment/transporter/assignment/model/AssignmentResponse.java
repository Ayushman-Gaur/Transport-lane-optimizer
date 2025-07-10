package com.transporter_assignment.transporter.assignment.model;

import java.util.List;

public class AssignmentResponse {
    private String status;
    private Integer totalCost;
    private List<Assignment> assignments;
    private List<Integer> selectedTransporters;

    // Constructors
    public AssignmentResponse() {}

    public AssignmentResponse(String status, Integer totalCost,
                              List<Assignment> assignments, List<Integer> selectedTransporters) {
        this.status = status;
        this.totalCost = totalCost;
        this.assignments = assignments;
        this.selectedTransporters = selectedTransporters;
    }

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getTotalCost() { return totalCost; }
    public void setTotalCost(Integer totalCost) { this.totalCost = totalCost; }

    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }

    public List<Integer> getSelectedTransporters() { return selectedTransporters; }
    public void setSelectedTransporters(List<Integer> selectedTransporters) {
        this.selectedTransporters = selectedTransporters;
    }
}
