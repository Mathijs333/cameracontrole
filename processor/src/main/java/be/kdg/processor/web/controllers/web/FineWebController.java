package be.kdg.processor.web.controllers.web;

import be.kdg.processor.model.Factors;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.web.dto.FineDTO;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;


import javax.validation.Valid;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:18
 */
@Controller
@RequestMapping("/fine")
public class FineWebController {
    private final ModelMapper modelMapper;
    private final FineService fineService;


    public FineWebController(ModelMapper modelMapper, FineService fineService) {
        this.modelMapper = modelMapper;
        this.fineService = fineService;
    }

    @GetMapping("/factor.do")
    public ModelAndView showFactors(Factors factors) {
        return new ModelAndView("factor", "factors", factors);
    }

    @PostMapping
    public ModelAndView createFine(@Valid @ModelAttribute FineDTO fineDTO) {
        Fine savedFine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ModelAndView("finesum", "fineDTO", modelMapper.map(savedFine, FineDTO.class));
    }
}
