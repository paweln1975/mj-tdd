package pl.paweln.mjtdd.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoanApplicationTest {

    @Spy
    LoanApplication loan;

    LoanApplicationController controller;
    DataBaseService dataBaseService;
    @Before
    public void setup() {
        this.loan = spy(new LoanApplication());
        this.controller = new LoanApplicationController();
        this.dataBaseService = mock(DataBaseService.class);
        this.controller.setDataBaseService(this.dataBaseService);
    }
    @Test
    public void test1YearLoanTotalAmount() {
        this.loan.setAmount(BigDecimal.valueOf(100));
        this.loan.setMonths(12);
        this.loan.setCustomerId(1);

        doReturn(new BigDecimal(10)).when(this.loan).getCurrentInterestRate();
        this.controller.processApplication(this.loan);

        BigDecimal totalAmount = this.loan.getTotalAmount();

        assertEquals(0, BigDecimal.valueOf(110).compareTo(totalAmount));
    }

    @Test
    public void test2YearLoanTotalAmount() {
        this.loan.setAmount(BigDecimal.valueOf(100));
        this.loan.setMonths(24);
        this.loan.setCustomerId(1);

        doReturn(new BigDecimal(10)).when(this.loan).getCurrentInterestRate();
        this.controller.processApplication(this.loan);

        BigDecimal totalAmount = this.loan.getTotalAmount();

        assertEquals(0, BigDecimal.valueOf(120).compareTo(totalAmount));
    }
}
