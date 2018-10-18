package be.kdg.processor.dto;

import be.kdg.processor.model.FineData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author Mathijs Constantin
 * @version 1.0 17/10/2018 20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
    @NotEmpty
    private int amount;

    @NotEmpty
    private Boolean approved;

    @NotEmpty
    private String comment;

    @NotEmpty
    private FineData fineData;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public FineData getFineData() {
        return fineData;
    }

    public void setFineData(FineData fineData) {
        this.fineData = fineData;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
