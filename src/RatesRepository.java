import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatesRepository {
    private static final RatesRepository INSTANCE = new RatesRepository();
    private Map<String, Currency> rates;

    private RatesRepository() {
    }

    public static RatesRepository getInstance() {
        return INSTANCE;
    }

    public void load(RatesProvider provider) {
        rates = new HashMap<>();

        for (Currency currency : provider.getRates()) {
            rates.put(currency.getCode(), currency);
        }
    }

    public Currency findByCode(String code) {
        if (rates == null) {
            throw new IllegalStateException("RatesRepository is not loaded. Call load() first.");
        }

        Currency currency = rates.get(code);

        if (currency == null) {
            throw new IllegalArgumentException(
                    "Nie znaleziono waluty o kodzie: " + code
            );
        }

        return currency;
    }

    public List<Currency> getAll() {
        if (rates == null) {
            throw new IllegalStateException("RatesRepository is not loaded. Call load() first.");
        }
        return new ArrayList<>(rates.values());
    }
}
