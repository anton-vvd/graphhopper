package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Vehicle {
    private String vehicleId;
    private String typeId;
    private Address startAddress;
    private Long earliestStart;
    private Long latestEnd;
    private Integer maxJobs;
    private List<String> skills;

    @JsonProperty("vehicle_id")
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("start_address")
    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

    @JsonProperty("earliest_start")
    public Long getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(Long earliestStart) {
        this.earliestStart = earliestStart;
    }

    @JsonProperty("latest_end")
    public Long getLatestEnd() {
        return latestEnd;
    }

    public void setLatestEnd(Long latestEnd) {
        this.latestEnd = latestEnd;
    }

    @JsonProperty("max_jobs")
    public Integer getMaxJobs() {
        return maxJobs;
    }

    public void setMaxJobs(Integer maxJobs) {
        this.maxJobs = maxJobs;
    }

    @JsonProperty("skills")
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
} 