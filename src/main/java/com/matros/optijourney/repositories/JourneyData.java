package com.matros.optijourney.repositories;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class JourneyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;

    @ElementCollection
    private List<String> waypoints;

    private String metric;

    public JourneyData() {}

    public JourneyData(String origin, List<String> waypoints, String destination, String metric) {
        this.origin = origin;
        this.waypoints = waypoints;
        this.destination = destination;
        this.metric = metric;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<String> waypoints) {
        this.waypoints = waypoints;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
