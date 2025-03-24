package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipmentLocation {
    private Address address;

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
} 