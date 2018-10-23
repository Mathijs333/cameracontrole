package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineRepository;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.violations.Violation;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 21:31
 * Class that gets messages and checks them on violations
 */
@Service
public class ProcessorService {
    @Autowired
    private CameraService cameraService;
    @Autowired
    private LicensePlateService licensePlateService;
    @Autowired
    private FineService fineService;

    @Autowired
    Map<String, Violation> violations = new HashMap<>();

    public void receiveCameraMessage(CameraMessage message) throws IOException, CameraNotFoundException, InvalidLicensePlateException, LicensePlateNotFoundException {
        System.out.println(message.toString());

            Camera camera = cameraService.getCameraById(message.getId());
            Car car = licensePlateService.getCarByLicensePlate(message.getLicensePlate());
            for (Violation violation : violations.values()) {
                Pair<Boolean, Fine> result = violation.isViolation(camera, message, car);
                if (result.getKey()) {
                    Fine fine = result.getValue();
                    fineService.save(fine);
                    System.out.printf("\nOvertreding: %s, boete: %d, id: %d\n", violation.getClass().getSimpleName(), fine.getAmount(), fine.getId());
                }
            }
        }

    }

