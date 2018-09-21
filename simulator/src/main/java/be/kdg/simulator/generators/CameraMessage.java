package be.kdg.simulator.generators;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/09/2018 14:24
 */
public class CameraMessage {
    private int id;
    private String LicensePlate;
    private LocalDateTime TimeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public LocalDateTime getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        TimeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CameraMessage)) return false;
        CameraMessage that = (CameraMessage) o;
        return getId() == that.getId() &&
                Objects.equals(getLicensePlate(), that.getLicensePlate()) &&
                Objects.equals(getTimeStamp(), that.getTimeStamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLicensePlate(), getTimeStamp());
    }

    @Override
    public String toString() {
        return String.format("CameraMessage{ $1 =  LicensePlate= $2  TimeStamp= $3", id, getLicensePlate(), getTimeStamp());
    }

    public CameraMessage(int id, String licensePlate, LocalDateTime timeStamp) {
        this.id = id;
        LicensePlate = licensePlate;
        TimeStamp = timeStamp;
    }

    public CameraMessage() {

    }
}
