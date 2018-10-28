package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mathijs Constantin
 * @version 1.0 12/10/2018 12:55
 */
@Data
@Entity
public class Fine {
    @Id
    @GeneratedValue
    private Long id;
    private int amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fineData")
    private Boolean approved;
    private String comment;
    private String licensePlate;
    private LocalDateTime timestamp;
    private String fineType;
    private int value;
    private int maxValue;

    public Fine(int amount, String licensePlate, LocalDateTime timestamp, String fineType, int value, int maxValue) {
        this.amount = amount;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
        this.fineType = fineType;
        this.value = value;
        this.maxValue = maxValue;
    }

    public Fine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getFineType() {
        return fineType;
    }

    public void setFineType(String fineType) {
        this.fineType = fineType;
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

    @Override
    public String toString() {
        return String.format("\nOvertreding: %s, boete: %d", getFineType(), getAmount());
    }
}
