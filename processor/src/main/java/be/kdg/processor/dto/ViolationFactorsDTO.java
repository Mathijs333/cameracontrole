package be.kdg.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/11/2018 12:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolationFactorsDTO {
    private List<ViolationFactorDTO> violationFactorDTOS;

    public ViolationFactorsDTO(List<ViolationFactorDTO> violationFactorDTOS) {
        this.violationFactorDTOS = violationFactorDTOS;
    }

    public void addViolationFactorDTO(ViolationFactorDTO violationFactorDTO) {
        this.violationFactorDTOS.add(violationFactorDTO);
    }

    public List<ViolationFactorDTO> getViolationFactorDTOS() {
        return violationFactorDTOS;
    }

    public void setViolationFactorDTOS(List<ViolationFactorDTO> violationFactorDTOS) {
        this.violationFactorDTOS = violationFactorDTOS;
    }


}
