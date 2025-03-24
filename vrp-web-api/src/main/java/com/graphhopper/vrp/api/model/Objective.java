package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Objective {
    private String type;
    private String value;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
} 