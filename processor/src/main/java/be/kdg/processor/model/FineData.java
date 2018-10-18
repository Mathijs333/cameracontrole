package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mathijs Constantin
 * @version 1.0 17/10/2018 20:44
 */
@Data
@Entity
public class FineData {
    @Id
    @GeneratedValue
    private Long id;
    private String licensePlate;
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_camera")
    private Camera camera;
    private int value;
    private int maxValue;
    private String fineType;

    public FineData() {

    }

    public FineData(String licensePlate, LocalDateTime timestamp, Camera camera, int value, int maxValue, String fineType) {
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
        this.camera = camera;
        this.value = value;
        this.maxValue = maxValue;
        this.fineType = fineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getFineType() {
        return fineType;
    }

    public void setFineType(String fineType) {
        this.fineType = fineType;
    }
}
