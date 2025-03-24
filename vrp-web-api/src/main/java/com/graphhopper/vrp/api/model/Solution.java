package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Solution {
    private Integer costs;
    private Integer distance;
    private Integer time;
    private Integer transportTime;
    private Integer completionTime;
    private Integer maxOperationTime;
    private Integer waitingTime;
    private Integer serviceDuration;
    private Integer preparationTime;
    private Integer noVehicles;
    private Integer noUnassigned;
    private List<Route> routes;
    private Unassigned unassigned;

    @JsonProperty("costs")
    public Integer getCosts() {
        return costs;
    }

    public void setCosts(Integer costs) {
        this.costs = costs;
    }

    @JsonProperty("distance")
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    @JsonProperty("max_operation_time")
    public Integer getMaxOperationTime() {
        return maxOperationTime;
    }

    public void setMaxOperationTime(Integer maxOperationTime) {
        this.maxOperationTime = maxOperationTime;
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

    @JsonProperty("no_vehicles")
    public Integer getNoVehicles() {
        return noVehicles;
    }

    public void setNoVehicles(Integer noVehicles) {
        this.noVehicles = noVehicles;
    }

    @JsonProperty("no_unassigned")
    public Integer getNoUnassigned() {
        return noUnassigned;
    }

    public void setNoUnassigned(Integer noUnassigned) {
        this.noUnassigned = noUnassigned;
    }

    @JsonProperty("routes")
    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @JsonProperty("unassigned")
    public Unassigned getUnassigned() {
        return unassigned;
    }

    public void setUnassigned(Unassigned unassigned) {
        this.unassigned = unassigned;
    }
} 