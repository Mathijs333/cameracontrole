package be.kdg.processor.controllers.web;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.dto.ViolationFactorDTO;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.violations.Violation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:18
 */
@Controller
@RequestMapping("/fine")
public class FineWebController {
    private final ModelMapper modelMapper;
    private final FineService fineService;

    @Autowired
    Map<String, Violation> violations = new HashMap<>();

    public FineWebController(ModelMapper modelMapper, FineService fineService) {
        this.modelMapper = modelMapper;
        this.fineService = fineService;
    }

    @GetMapping("/factor.do")
    public ModelAndView showFactors(Model model) {
        ModelAndView mav = new ModelAndView("factor");
        List<ViolationFactorDTO> violationFactors = new ArrayList<>();
        for (Violation violation : violations.values()) {
            violationFactors.add(new ViolationFactorDTO(violation.getClass().getSimpleName(), violation.getFactor()));
        }
        mav.addObject("violationFactors", violationFactors);
        return mav;
    }

    @PostMapping
    public ModelAndView createFine(@Valid @ModelAttribute FineDTO fineDTO) {
        Fine savedFine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ModelAndView("finesum", "fineDTO", modelMapper.map(savedFine, FineDTO.class));
    }
}