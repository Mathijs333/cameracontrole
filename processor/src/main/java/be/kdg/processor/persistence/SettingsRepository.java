package be.kdg.processor.persistence;

import be.kdg.processor.model.Fine;
import be.kdg.processor.model.Settings;
import be.kdg.processor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mathijs Constantin
 * @version 1.0 3/11/2018 19:45
 */
public interface SettingsRepository extends JpaRepository<Settings, Long> {

}
