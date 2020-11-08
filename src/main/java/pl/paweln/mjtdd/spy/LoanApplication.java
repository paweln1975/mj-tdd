package pl.paweln.mjtdd.spy;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

public class LoanApplication {
    private long customerId;
    private int months;
    private BigDecimal amount;
    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private BankInterestService service = url -> {
        throw new NotImplementedException();

    };
    public BigDecimal getCurrentInterestRate() {
        return this.service.getCurrentInterestRate("http://www.nbp.pl/current/interestrate");
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
