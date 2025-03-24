package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Request {
    private List<Vehicle> vehicles;
    private List<VehicleType> vehicleTypes;
    private List<Service> services;
    private List<Shipment> shipments;
    private List<Objective> objectives;
    private Configuration configuration;

    @JsonProperty("vehicles")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @JsonProperty("vehicle_types")
    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

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

    @JsonProperty("objectives")
    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    @JsonProperty("configuration")
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
} 