package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Activity {
    private String type;
    private String id;
    private String locationId;
    private Address address;
    private Long arrTime;
    private String arrDateTime;
    private Long endTime;
    private String endDateTime;
    private Integer waitingTime;
    private Integer distance;
    private Integer drivingTime;
    private Integer preparationTime;
    private List<Integer> loadBefore;
    private List<Integer> loadAfter;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("location_id")
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("arr_time")
    public Long getArrTime() {
        return arrTime;
    }

    public void setArrTime(Long arrTime) {
        this.arrTime = arrTime;
    }

    @JsonProperty("arr_date_time")
    public String getArrDateTime() {
        return arrDateTime;
    }

    public void setArrDateTime(String arrDateTime) {
        this.arrDateTime = arrDateTime;
    }

    @JsonProperty("end_time")
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("end_date_time")
    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @JsonProperty("waiting_time")
    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    @JsonProperty("distance")
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @JsonProperty("driving_time")
    public Integer getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(Integer drivingTime) {
        this.drivingTime = drivingTime;
    }

    @JsonProperty("preparation_time")
    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    @JsonProperty("load_before")
    public List<Integer> getLoadBefore() {
        return loadBefore;
    }

    public void setLoadBefore(List<Integer> loadBefore) {
        this.loadBefore = loadBefore;
    }

    @JsonProperty("load_after")
    public List<Integer> getLoadAfter() {
        return loadAfter;
    }

    public void setLoadAfter(List<Integer> loadAfter) {
        this.loadAfter = loadAfter;
    }
} 