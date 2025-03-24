package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeWindow {
    private Long earliest;
    private Long latest;

    @JsonProperty("earliest")
    public Long getEarliest() {
        return earliest;
    }

    public void setEarliest(Long earliest) {
        this.earliest = earliest;
    }

    @JsonProperty("latest")
    public Long getLatest() {
        return latest;
    }

    public void setLatest(Long latest) {
        this.latest = latest;
    }
} 