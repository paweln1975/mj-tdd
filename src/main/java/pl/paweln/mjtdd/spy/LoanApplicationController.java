package pl.paweln.mjtdd.spy;

import java.math.BigDecimal;

public class LoanApplicationController {
    private DataBaseService dataBaseService;

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void processApplication(LoanApplication loan) {
        BigDecimal currentInterestRate = loan.getCurrentInterestRate();
        BigDecimal amount = loan.getAmount();
        BigDecimal totalAmountToPayBack =
                BigDecimal.valueOf(1)
                .add(currentInterestRate.multiply(BigDecimal.valueOf(loan.getMonths() / 12))
                .divide(BigDecimal.valueOf(100), 0))
                .multiply(amount);
        loan.setTotalAmount(totalAmountToPayBack);

        this.dataBaseService.saveData(loan);
    }
}
