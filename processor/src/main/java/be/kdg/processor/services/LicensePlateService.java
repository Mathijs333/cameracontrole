package be.kdg.processor.services;

import be.kdg.processor.model.Car;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 21:59
 */
@Component
public class LicensePlateService extends LicensePlateServiceProxy {
    @Autowired
    private ObjectMapper objectMapper;
    @Cacheable("licensePlates")
    public Car getCarByLicensePlate(String licensePlate) throws IOException, LicensePlateNotFoundException, InvalidLicensePlateException {
        return objectMapper.readValue(get(licensePlate), Car.class);
    }
}
