package be.kdg.processor.controllers.rest;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 17/10/2018 20:21
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/fine")
@ControllerAdvice
public class FineRestController {
    private final ModelMapper modelMapper;
    private final FineService fineService;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public FineRestController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<FineDTO> createFine(@RequestBody FineDTO fineDTO) {
        Fine fine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<FineDTO> loadFine(@PathVariable Long id) {
        Fine fine = fineService.load(id);
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteFine(@PathVariable Long id) {
        Fine fine = fineService.load(id);
        fineService.delete(fine);
        return HttpStatus.GONE;
    }

    @PostMapping("/read")
    public ResponseEntity<List<FineDTO>> loadFines(@RequestParam Map<String, String> map) {
            for (String s : map.keySet()) {
                System.out.println(s);
            }
            List<Fine> fines = fineService.load(LocalDateTime.parse(map.get("startDate"), dateFormat), LocalDateTime.parse(map.get("endDate"), dateFormat));
            List<FineDTO> fineDTOS = new ArrayList<>();
            for (Fine fine : fines) {
                fineDTOS.add(modelMapper.map(fine, FineDTO.class));
            }
            return new ResponseEntity<>(fineDTOS, HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<FineDTO>> loadFines() {
        List<Fine> fines = fineService.load();
        List<FineDTO> fineDTOS = new ArrayList<>();
        for (Fine fine : fines) {
            fineDTOS.add(modelMapper.map(fine, FineDTO.class));
        }
        return new ResponseEntity<>(fineDTOS, HttpStatus.OK);
    }

    @PutMapping("/update/approve/{id}")
    public ResponseEntity<FineDTO> approveFine(@PathVariable Long id) {
        Fine fineIn = fineService.load(id);
        Fine fineOut = fineService.approveFine(fineIn);
        return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FineDTO> updateFine(@PathVariable Long id, @RequestParam("amount") int amount, @RequestParam("comment") String comment) {
        Fine fineIn = fineService.load(id);
        Fine fineOut = fineService.changeAmount(fineIn, comment, amount);
        return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
    }


}
