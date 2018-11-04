package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.exceptions.MessageProcessingException;
import be.kdg.processor.violations.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorService.class);


    @Autowired
    Map<String, Violation> violations = new HashMap<>();
    public void receiveCameraMessage(CameraMessage message) throws MessageProcessingException {
            System.out.println(message.toString());
            try {
                Camera camera = cameraService.getCameraById(message.getId());
                Car car = licensePlateService.getCarByLicensePlate(message.getLicensePlate());
                for (Violation violation : violations.values()) {
                    Optional<Fine> result = violation.isViolation(camera, message, car);
                    if (result.isPresent()) {
                        Fine fine = result.get();
                        fineService.save(fine);
                        LOGGER.info(fine.toString());
                    }
                }
            }
            catch (Exception ex) {
                throw new MessageProcessingException(ex.getMessage());
            }
        }

    }

