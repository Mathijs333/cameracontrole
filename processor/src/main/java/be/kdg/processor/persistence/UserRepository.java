package be.kdg.processor.persistence;

import be.kdg.processor.model.Fine;
import be.kdg.processor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:02
 */
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
}
