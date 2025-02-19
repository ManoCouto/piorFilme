package com.example.model;

import java.util.List;

public class AwardIntervalResponse {
    private List<AwardInterval> min;
    private List<AwardInterval> max;

    public List<AwardInterval> getMax() {
        return max;
    }

    public void setMax(List<AwardInterval> max) {
        this.max = max;
    }

    public List<AwardInterval> getMin() {
        return min;
    }

    public void setMin(List<AwardInterval> min) {
        this.min = min;
    }
}