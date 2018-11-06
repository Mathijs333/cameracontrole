package be.kdg.processor.persistence;

import be.kdg.processor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:02
 */
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
}
