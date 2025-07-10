package com.transporter_assignment.transporter.assignment.repo;

import com.transporter_assignment.transporter.assignment.model.Lane;
import com.transporter_assignment.transporter.assignment.model.Transporter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class DataRepository {
    private final Map<String, Object> dataStore = new ConcurrentHashMap<>();

    public void saveLanes(List<Lane> lanes) {
        dataStore.put("lanes", lanes);
    }

    public void saveTransporters(List<Transporter> transporters) {
        dataStore.put("transporters", transporters);
    }

    @SuppressWarnings("unchecked")
    public List<Lane> getLanes() {
        return (List<Lane>) dataStore.get("lanes");
    }

    @SuppressWarnings("unchecked")
    public List<Transporter> getTransporters() {
        return (List<Transporter>) dataStore.get("transporters");
    }

    public boolean hasData() {
        return dataStore.containsKey("lanes") && dataStore.containsKey("transporters");
    }

    public void clearData() {
        dataStore.clear();
    }
}
