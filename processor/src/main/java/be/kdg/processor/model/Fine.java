package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mathijs Constantin
 * @version 1.0 12/10/2018 12:55
 */
@Data
@Entity
public class Fine {
    @Id
    @GeneratedValue
    private Long id;
    private int amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fineData")
    private FineData fineData;
    private Boolean approved;
    private String comment;

    public Fine(int amount, FineData fineData) {
        this.amount = amount;
        this.fineData = fineData;
        this.approved = false;
    }

    public Fine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return String.format("\nOvertreding: %s, boete: %d", fineData.getFineType(), getAmount());
    }
}
