package be.kdg.processor.persistence;

import be.kdg.processor.model.Admin;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:04
 */
@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin load(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return admin.get();
        }
        return new Admin();
    }

    public Admin load(String username) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        }
        return new Admin();
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
