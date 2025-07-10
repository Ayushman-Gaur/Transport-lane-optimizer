package com.transporter_assignment.transporter.assignment.service;

import com.transporter_assignment.transporter.assignment.model.*;
import com.transporter_assignment.transporter.assignment.repo.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptimizationService {
    @Autowired
    private DataRepository dataRepository;

    public AssignmentResponse optimizeAssignments(Integer maxTransporters) {
        List<Lane> lanes = dataRepository.getLanes();
        List<Transporter> transporters = dataRepository.getTransporters();

        if (lanes == null || transporters == null) {
            throw new IllegalStateException("No data available. Please submit input data first.");
        }

        // Create cost matrix: transporter -> lane -> cost
        Map<Integer, Map<Integer, Integer>> costMatrix = buildCostMatrix(transporters);

        // Find optimal assignment using greedy approach with backtracking
        OptimizationResult result = findOptimalAssignment(lanes, transporters, costMatrix, maxTransporters);

        return new AssignmentResponse(
                "success",
                result.totalCost,
                result.assignments,
                result.selectedTransporters
        );
    }

    private Map<Integer, Map<Integer, Integer>> buildCostMatrix(List<Transporter> transporters) {
        Map<Integer, Map<Integer, Integer>> costMatrix = new HashMap<>();

        for (Transporter transporter : transporters) {
            Map<Integer, Integer> laneCosts = new HashMap<>();
            for (LaneQuote quote : transporter.getLaneQuotes()) {
                laneCosts.put(quote.getLaneId(), quote.getQuote());
            }
            costMatrix.put(transporter.getId(), laneCosts);
        }

        return costMatrix;
    }

    private OptimizationResult findOptimalAssignment(
            List<Lane> lanes,
            List<Transporter> transporters,
            Map<Integer, Map<Integer, Integer>> costMatrix,
            Integer maxTransporters) {

        Set<Integer> laneIds = lanes.stream().map(Lane::getId).collect(Collectors.toSet());
        List<Integer> transporterIds = transporters.stream().map(Transporter::getId).collect(Collectors.toList());

        // Try all combinations of transporters up to maxTransporters
        OptimizationResult bestResult = null;
        int bestCost = Integer.MAX_VALUE;

        // Generate all possible combinations of transporters
        for (int numTransporters = 1; numTransporters <= Math.min(maxTransporters, transporterIds.size()); numTransporters++) {
            List<List<Integer>> combinations = generateCombinations(transporterIds, numTransporters);

            for (List<Integer> selectedTransporters : combinations) {
                OptimizationResult result = assignLanesToTransporters(laneIds, selectedTransporters, costMatrix);

                if (result != null && result.totalCost < bestCost) {
                    bestCost = result.totalCost;
                    bestResult = result;
                }
            }
        }

        if (bestResult == null) {
            throw new IllegalStateException("No valid assignment found. Some lanes cannot be covered by available transporters.");
        }

        return bestResult;
    }

    private OptimizationResult assignLanesToTransporters(
            Set<Integer> laneIds,
            List<Integer> selectedTransporters,
            Map<Integer, Map<Integer, Integer>> costMatrix) {

        List<Assignment> assignments = new ArrayList<>();
        int totalCost = 0;

        // For each lane, find the cheapest transporter among selected ones
        for (Integer laneId : laneIds) {
            Integer bestTransporter = null;
            int bestCost = Integer.MAX_VALUE;

            for (Integer transporterId : selectedTransporters) {
                Map<Integer, Integer> transporterCosts = costMatrix.get(transporterId);
                if (transporterCosts != null && transporterCosts.containsKey(laneId)) {
                    int cost = transporterCosts.get(laneId);
                    if (cost < bestCost) {
                        bestCost = cost;
                        bestTransporter = transporterId;
                    }
                }
            }

            if (bestTransporter == null) {
                // This combination cannot cover all lanes
                return null;
            }

            assignments.add(new Assignment(laneId, bestTransporter));
            totalCost += bestCost;
        }

        return new OptimizationResult(totalCost, assignments, selectedTransporters);
    }

    private List<List<Integer>> generateCombinations(List<Integer> items, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinationsHelper(items, k, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void generateCombinationsHelper(
            List<Integer> items,
            int k,
            int start,
            List<Integer> current,
            List<List<Integer>> combinations) {

        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < items.size(); i++) {
            current.add(items.get(i));
            generateCombinationsHelper(items, k, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }

    private static class OptimizationResult {
        final int totalCost;
        final List<Assignment> assignments;
        final List<Integer> selectedTransporters;

        OptimizationResult(int totalCost, List<Assignment> assignments, List<Integer> selectedTransporters) {
            this.totalCost = totalCost;
            this.assignments = assignments;
            this.selectedTransporters = selectedTransporters;
        }
    }
}
