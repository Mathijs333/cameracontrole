package be.kdg.processor.persistence;

import be.kdg.processor.model.User;
import be.kdg.processor.model.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:04
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = load(username);
        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public User load(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return new User();
    }

    public User load(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public User updateUser(User user, String username, String password) {
        user.setUsername(username);
        user.setPassword(password);
        return save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User save(User user) {
        user.setPassword(/*passwordEncoder.encode(*/user.getPassword());
        return userRepository.save(user);
    }
}
