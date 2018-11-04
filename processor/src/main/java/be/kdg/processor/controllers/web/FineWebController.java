package be.kdg.processor.controllers.web;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.dto.SettingsDTO;
import be.kdg.processor.dto.ViolationFactorDTO;
import be.kdg.processor.dto.ViolationFactorsDTO;
import be.kdg.processor.model.Settings;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.persistence.SettingsService;
import be.kdg.processor.violations.Violation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:18
 */
@Controller
@RequestMapping("")
public class FineWebController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FineService fineService;
    @Autowired
    private SettingsService settingsService;

    @Autowired
    Map<String, Violation> violations = new HashMap<>();


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

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/settings/violationfactors")
    public ModelAndView saveSettingsFactors(@RequestParam Map<String,String> allRequestParams) {
        Settings settings = settingsService.getSettingsSetViolationfactors(allRequestParams);
        settingsService.save(settings);
        return settingsFactors();
    }

    @PostMapping("/settings")
    public ModelAndView saveSettings(@RequestParam Map<String,String> allRequestParams) {
        Settings settings = settingsService.getSettingsSetSettings(allRequestParams);
        settingsService.save(settings);
        return settings();
    }

    @GetMapping("/settings")
    public ModelAndView settings() {
        ModelAndView mav = new ModelAndView("settings");
        SettingsDTO settingsDTO = modelMapper.map(settingsService.load().get(), SettingsDTO.class);
        mav.addObject("settings", settingsDTO);
        return mav;
    }

    @GetMapping("/settings/violationfactors")
    public ModelAndView settingsFactors() {
        ModelAndView mav = new ModelAndView("factorsettings");
        mav.addObject("violationFactors", settingsService.getViolationFactors());
        return mav;
    }
}
