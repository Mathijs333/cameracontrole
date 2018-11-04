package be.kdg.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * @author Mathijs Constantin
 * @version 1.0 3/11/2018 19:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDTO {
    @NotEmpty
    private int bufferTime;
    @NotEmpty
    private int timeframeEmission;
    @NotEmpty
    private int retryDelay;
    @NotEmpty
    private int retryCount;

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
}
