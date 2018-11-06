package be.kdg.processor.controllers.rest;


import be.kdg.processor.dto.UserDTO;
import be.kdg.processor.model.Factors;
import be.kdg.processor.model.User;
import be.kdg.processor.persistence.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:03
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/user")
@ControllerAdvice
public class UserRestController {
    private final ModelMapper modelMapper;
    private final UserService userService;


    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(userDTO.getPassword());
        User user = userService.save(modelMapper.map(userDTO, User.class));
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> loadUser(@PathVariable Long id) {
        User user = userService.load(id);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<UserDTO> test() {
        Factors[] test = Factors.values();
        User user = new User();
        user.setPassword("aaaaaa333");
        user.setUsername("mathijs");
        User userOut = userService.save(user);
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User userIn = userService.load(id);
        User userOut = userService.updateUser(userIn, userDTO.getUsername(), userDTO.getPassword());
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        User user = userService.load(id);
        userService.delete(user);
        return HttpStatus.OK;
    }
}
