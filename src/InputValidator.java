import java.math.BigDecimal;
import java.util.Locale;

public class InputValidator {
    private final RatesRepository ratesRepository;

    public InputValidator(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public String validateCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException(
                    "Currency code cannot be empty"
            );
        }

        String normalizedCode = code.trim().toUpperCase(Locale.ROOT);
        ratesRepository.findByCode(normalizedCode);
        return normalizedCode;
    }

    public BigDecimal parseAmount(String numberToParse) {
        if (numberToParse == null || numberToParse.isBlank()) {
            throw new IllegalArgumentException("Amount cannot be empty");
        }

        try {
            BigDecimal amount = new BigDecimal(
                    numberToParse.trim().replace(',', '.')
            );

            if (amount.signum() < 0) {
                throw new IllegalArgumentException(
                        "Amount cannot be negative"
                );
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid amount: " + numberToParse,
                    e
            );
        }
    }
}
