package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class VehicleType {
    private String typeId;
    private List<Integer> capacity;
    private String profile;

    @JsonProperty("type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("capacity")
    public List<Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(List<Integer> capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
} 