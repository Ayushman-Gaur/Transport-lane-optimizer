package com.transporter_assignment.transporter.assignment.controller;

import com.transporter_assignment.transporter.assignment.model.AssignmentRequest;
import com.transporter_assignment.transporter.assignment.model.AssignmentResponse;
import com.transporter_assignment.transporter.assignment.model.InputRequest;
import com.transporter_assignment.transporter.assignment.repo.DataRepository;
import com.transporter_assignment.transporter.assignment.service.OptimizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transporters")
public class TransporterController {
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private OptimizationService optimizationService;

    @PostMapping("/input")
    public ResponseEntity<Map<String, String>> submitInputData(@Valid @RequestBody InputRequest request) {
        try {
            // Save the input data
            dataRepository.saveLanes(request.getLanes());
            dataRepository.saveTransporters(request.getTransporters());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Input data saved successfully.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Failed to save input data: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/assignment")
    public ResponseEntity<AssignmentResponse> getOptimizedAssignment(@Valid @RequestBody AssignmentRequest request) {
        try {
            if (!dataRepository.hasData()) {
                AssignmentResponse response = new AssignmentResponse();
                response.setStatus("error");
                return ResponseEntity.badRequest().body(response);
            }

            AssignmentResponse response = optimizationService.optimizeAssignments(request.getMaxTransporters());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AssignmentResponse response = new AssignmentResponse();
            response.setStatus("error");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/data")
    public ResponseEntity<Map<String, String>> clearData() {
        dataRepository.clearData();
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Data cleared successfully.");
        return ResponseEntity.ok(response);
    }
}
