package com.graphhopper.vrp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Response {
    private List<String> copyrights;
    private String jobId;
    private String status;
    private Long waitingTimeInQueue;
    private Long processingTime;
    private Solution solution;

    @JsonProperty("copyrights")
    public List<String> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<String> copyrights) {
        this.copyrights = copyrights;
    }

    @JsonProperty("job_id")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("waiting_time_in_queue")
    public Long getWaitingTimeInQueue() {
        return waitingTimeInQueue;
    }

    public void setWaitingTimeInQueue(Long waitingTimeInQueue) {
        this.waitingTimeInQueue = waitingTimeInQueue;
    }

    @JsonProperty("processing_time")
    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }

    @JsonProperty("solution")
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
} 