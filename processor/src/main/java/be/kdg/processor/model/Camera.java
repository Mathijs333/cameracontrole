package be.kdg.processor.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:59
 */
public class Camera {
    private int cameraId;
    private HashMap<String, Double> location;
    private HashMap<String, Integer> segment;
    private int euroNorm;

    public Camera() {

    }

    public Camera(int cameraId, HashMap<String, Double> location, int euroNorm) {
        this.cameraId = cameraId;
        this.location = location;
        this.euroNorm = euroNorm;
    }

    public Camera(int cameraId, HashMap<String, Double> location, HashMap<String, Integer> segment) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
    }

    public HashMap<String, Integer> getSegment() {
        return segment;
    }

    public void setSegment(HashMap<String, Integer> segment) {
        this.segment = segment;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public HashMap<String, Double> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, Double> location) {
        this.location = location;
    }

    public int getEuroNorm() {
        return euroNorm;
    }

    public void setEuroNorm(int euroNorm) {
        this.euroNorm = euroNorm;
    }
}
