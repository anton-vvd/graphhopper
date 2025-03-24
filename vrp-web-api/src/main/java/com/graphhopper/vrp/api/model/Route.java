package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Route {
    private String vehicleId;
    private Integer distance;
    private Integer transportTime;
    private Integer completionTime;
    private Integer waitingTime;
    private Integer serviceDuration;
    private Integer preparationTime;
    private List<Point> points;
    private List<Activity> activities;

    @JsonProperty("vehicle_id")
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("distance")
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @JsonProperty("transport_time")
    public Integer getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Integer transportTime) {
        this.transportTime = transportTime;
    }

    @JsonProperty("completion_time")
    public Integer getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Integer completionTime) {
        this.completionTime = completionTime;
    }

    @JsonProperty("waiting_time")
    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    @JsonProperty("service_duration")
    public Integer getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    @JsonProperty("preparation_time")
    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    @JsonProperty("points")
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @JsonProperty("activities")
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
} 