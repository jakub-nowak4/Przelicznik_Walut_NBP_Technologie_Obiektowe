import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {
    private final RatesRepository ratesRepository;

    public CurrencyConverter(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public BigDecimal convert(BigDecimal amount, String fromCode, String toCode) {
        Currency fromCurrency = ratesRepository.findByCode(fromCode);
        Currency toCurrency = ratesRepository.findByCode(toCode);

        BigDecimal plnAmount = amount.multiply(fromCurrency.getAverageRatePerUnit());
        return plnAmount.divide(
                toCurrency.getAverageRatePerUnit(),
                RoundingMode.HALF_UP
        ).setScale(2, RoundingMode.HALF_UP);
    }
}
