package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    private double costs;
    private double distance;
    private double duration;
    private double waitingTime;
    private double serviceDuration;
    private double amount;
    private double pieces;
    private double times;
    private double distance;
    private double waitingTime;
    private double serviceDuration;
    private double amount;
    private double costs;
    private double pieces;

    @JsonProperty("costs")
    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    @JsonProperty("distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @JsonProperty("duration")
    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @JsonProperty("waiting_time")
    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    @JsonProperty("service_duration")
    public double getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(double serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("pieces")
    public double getPieces() {
        return pieces;
    }

    public void setPieces(double pieces) {
        this.pieces = pieces;
    }

    @JsonProperty("times")
    public double getTimes() {
        return times;
    }

    public void setTimes(double times) {
        this.times = times;
    }
} 