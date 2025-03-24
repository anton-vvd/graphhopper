package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Point {
    private List<List<Double>> coordinates;
    private String type;

    @JsonProperty("coordinates")
    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
} 