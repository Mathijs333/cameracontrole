package be.kdg.processor.model.Fine;

/**
 * @author Mathijs Constantin
 * @version 1.0 12/10/2018 13:00
 */
public class EmissionFine extends Fine {
    public int emissionCar;
    public int emissionCamera;

    public EmissionFine(int amount, int emissionCar, int emissionCamera) {
        super(amount);
        this.emissionCar = emissionCar;
        this.emissionCamera = emissionCamera;
    }

    public int getEmissionCar() {
        return emissionCar;
    }

    public void setEmissionCar(int emissionCar) {
        this.emissionCar = emissionCar;
    }

    public int getEmissionCamera() {
        return emissionCamera;
    }

    public void setEmissionCamera(int emissionCamera) {
        this.emissionCamera = emissionCamera;
    }
}
