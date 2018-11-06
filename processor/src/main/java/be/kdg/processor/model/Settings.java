package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 3/11/2018 18:23
 */
@Data
@Entity
public class Settings {
    @Id
    private Long id = (long)1;
    private int bufferTime;
    private int timeframeEmission;
    private int retryDelay;
    private int retryCount;
    private HashMap<String, Integer> violationFactors;

    public Settings(int bufferTime, int timeframeEmission, int retryDelay, int retryCount, HashMap<String, Integer> violationFactors) {
        this.bufferTime = bufferTime;
        this.timeframeEmission = timeframeEmission;
        this.retryDelay = retryDelay;
        this.retryCount = retryCount;
        this.violationFactors = violationFactors;
    }

    public Settings() {

    }

    public Long getId() {
        return id;
    }

    public int getBufferTime() {
        return bufferTime;
    }

    public void setBufferTime(int bufferTime) {
        this.bufferTime = bufferTime;
    }

    public int getTimeframeEmission() {
        return timeframeEmission;
    }

    public void setTimeframeEmission(int timeframeEmission) {
        this.timeframeEmission = timeframeEmission;
    }

    public int getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(int retryDelay) {
        this.retryDelay = retryDelay;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Map<String, Integer> getViolationFactors() {
        return violationFactors;
    }

    public void setViolationFactors(HashMap<String, Integer> violationFactors) {
        this.violationFactors = violationFactors;
    }
}
