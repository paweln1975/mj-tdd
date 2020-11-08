package pl.paweln.mjtdd.spy;

import java.math.BigDecimal;

public interface BankInterestService {
    BigDecimal getCurrentInterestRate(String url);
}
