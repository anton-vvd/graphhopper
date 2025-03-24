package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Configuration {
    private Routing routing;

    @JsonProperty("routing")
    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public static class Routing {
        private Boolean calcPoints;
        private List<String> snapPreventions;

        @JsonProperty("calc_points")
        public Boolean getCalcPoints() {
            return calcPoints;
        }

        public void setCalcPoints(Boolean calcPoints) {
            this.calcPoints = calcPoints;
        }

        @JsonProperty("snap_preventions")
        public List<String> getSnapPreventions() {
            return snapPreventions;
        }

        public void setSnapPreventions(List<String> snapPreventions) {
            this.snapPreventions = snapPreventions;
        }
    }
} 