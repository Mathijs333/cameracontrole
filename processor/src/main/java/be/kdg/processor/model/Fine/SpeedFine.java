package be.kdg.processor.model.Fine;

/**
 * @author Mathijs Constantin
 * @version 1.0 12/10/2018 13:00
 */
public class SpeedFine extends Fine {
    public int maxSpeed;
    public int speed;

    public SpeedFine(int amount, int maxSpeed, int speed) {
        super(amount);
        this.maxSpeed = maxSpeed;
        this.speed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
