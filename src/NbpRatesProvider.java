import java.math.BigDecimal;
import java.util.List;

public class NbpRatesProvider implements RatesProvider {
    private final Fetcher fetcher;
    private final RatesParser parser;

    public NbpRatesProvider(Fetcher fetcher, RatesParser parser) {
        this.fetcher = fetcher;
        this.parser = parser;
    }

    @Override
    public List<Currency> getRates() {
        String data = fetcher.fetch();

        if (data == null || data.isBlank()) {
            throw new IllegalStateException(
                    "Nie udało się pobrać danych z NBP"
            );
        }

        List<Currency> rates = parser.parse(data);
        // Add PLN currency to the list of rates.
        rates.add(
                new Currency(
                        "polski złoty",
                        "PLN",
                        new BigDecimal("1.00"),
                        1
                )
        );

        return rates;
    }
}
