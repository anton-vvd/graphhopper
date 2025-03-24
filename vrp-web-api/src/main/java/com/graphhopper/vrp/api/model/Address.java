package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String locationId;
    private Double lon;
    private Double lat;

    @JsonProperty("location_id")
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
} 