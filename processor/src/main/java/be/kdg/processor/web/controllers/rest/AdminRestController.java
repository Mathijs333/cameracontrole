package be.kdg.processor.web.controllers.rest;

import be.kdg.processor.model.Admin;
import be.kdg.processor.model.Fine;
import be.kdg.processor.model.FineData;
import be.kdg.processor.persistence.AdminRepository;
import be.kdg.processor.persistence.AdminService;
import be.kdg.processor.persistence.FineRepository;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.web.dto.AdminDTO;
import be.kdg.processor.web.dto.FineDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:03
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public AdminRestController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.save(modelMapper.map(adminDTO, Admin.class));
        return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AdminDTO> loadFine(@PathVariable Long id) {
        Admin admin = adminService.load(id);
        return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FineDTO> updateFine(@PathVariable Long id, @RequestParam("username") String username, @RequestParam("password") String password) {
        Admin adminIn = adminService.load(id);
        adminIn.setUsername(username);
        adminIn.setPassword(password);
        Admin adminOut = adminService.save(adminIn);
        return new ResponseEntity<>(modelMapper.map(adminOut, FineDTO.class), HttpStatus.ACCEPTED);
    }
}
