package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Service {
    private String id;
    private String name;
    private Address address;
    private List<Integer> size;
    private List<TimeWindow> timeWindows;

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

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("size")
    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    @JsonProperty("time_windows")
    public List<TimeWindow> getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(List<TimeWindow> timeWindows) {
        this.timeWindows = timeWindows;
    }
} 