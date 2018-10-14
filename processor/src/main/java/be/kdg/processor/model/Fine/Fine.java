package be.kdg.processor.model.Fine;

/**
 * @author Mathijs Constantin
 * @version 1.0 12/10/2018 12:55
 */
public class Fine {
   public int amount;

    public Fine(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
