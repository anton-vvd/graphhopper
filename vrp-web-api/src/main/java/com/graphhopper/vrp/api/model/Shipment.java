package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Shipment {
    private String id;
    private String name;
    private Integer priority;
    private ShipmentLocation pickup;
    private ShipmentLocation delivery;
    private List<Integer> size;
    private List<String> requiredSkills;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("pickup")
    public ShipmentLocation getPickup() {
        return pickup;
    }

    public void setPickup(ShipmentLocation pickup) {
        this.pickup = pickup;
    }

    @JsonProperty("delivery")
    public ShipmentLocation getDelivery() {
        return delivery;
    }

    public void setDelivery(ShipmentLocation delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("size")
    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    @JsonProperty("required_skills")
    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
} 