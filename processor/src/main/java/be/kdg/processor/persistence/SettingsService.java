package be.kdg.processor.persistence;

import be.kdg.processor.dto.ViolationFactorDTO;
import be.kdg.processor.model.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import be.kdg.processor.violationmanagers.ViolationManager;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 3/11/2018 19:45
 */
@Service
@Transactional
public class SettingsService {
    @Value("${speedCameraBufferTime}")
    private int bufferTime;
    @Value("${emissionViolationTimeframe}")
    private int timeframeEmission;
    @Value("#{new Integer('${retryDelay}')}")
    private int retryDelay;
    @Value("${retryCount}")
    private int retryCount;
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private List<ViolationManager> violations;

    public void initiate() {
        HashMap<String, Integer> violationFactors = new HashMap<>();
        for (ViolationManager violation : violations) {
            violationFactors.put(violation.getClass().getSimpleName(), violation.getFactor());
        }
        if (!settingsRepository.findById((long)1).isPresent()) {
            Settings settings = new Settings();
            settings.setViolationFactors(violationFactors);
            settings.setTimeframeEmission(timeframeEmission);
            settings.setRetryDelay(retryDelay);
            settings.setRetryCount(retryCount);
            settings.setBufferTime(bufferTime);
            save(settings);
        }

    }
    public Optional<Settings> load() {
        if (!settingsRepository.findById((long)1).isPresent()) {
            initiate();
        }
        return settingsRepository.findById((long)1);
    }

    public Settings save(Settings settings) {
        Settings settingsOut = settingsRepository.save(settings);
        LOGGER.info("Settings saved");
        return settingsOut;
    }

    public Settings getSettingsSetViolationfactors(Map<String, String> list) {
        Settings settings = load().get();
        HashMap<String, Integer> violationFactors = new HashMap<>();
        for (String s : list.keySet()) {
            violationFactors.put(s, Integer.parseInt(list.get(s)));
        }
        settings.setViolationFactors(violationFactors);
        return settings;
    }

    public Settings getSettingsSetSettings(Map<String, String> values) {
        Settings settings = load().get();
        settings.setBufferTime(Integer.parseInt(values.get("bufferTime")));
        settings.setRetryCount(Integer.parseInt(values.get("retryCount")));
        settings.setRetryDelay(Integer.parseInt(values.get("retryDelay")));
        settings.setTimeframeEmission(Integer.parseInt(values.get("timeframeEmission")));
        return settings;
    }

    public List<ViolationFactorDTO> getViolationFactors() {
        List<ViolationFactorDTO> violationFactorDTOS = new ArrayList<>();
        Map<String, Integer> violationFactors = load().get().getViolationFactors();
        for (String violation : violationFactors.keySet()) {
            violationFactorDTOS.add(new ViolationFactorDTO(violation, violationFactors.get(violation)));
        }
        return violationFactorDTOS;
    }
}
