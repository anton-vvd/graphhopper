package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Unassigned {
    private List<Service> services;
    private List<Shipment> shipments;
    private List<Object> breaks;
    private List<Object> details;

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonProperty("shipments")
    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    @JsonProperty("breaks")
    public List<Object> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<Object> breaks) {
        this.breaks = breaks;
    }

    @JsonProperty("details")
    public List<Object> getDetails() {
        return details;
    }

    public void setDetails(List<Object> details) {
        this.details = details;
    }
} 