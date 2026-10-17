
import java.math.BigDecimal;

void main(String[] args) {
    try {
        Fetcher fetcher = new HttpFetcher(
                "https://static.nbp.pl/dane/kursy/xml/lastA.xml"
        );
        RatesParser parser = new NbpXmlParser();
        RatesProvider provider = new NbpRatesProvider(fetcher, parser);
        RatesRepository repository = RatesRepository.getInstance();
        repository.load(provider);

        InputValidator validator = new InputValidator(repository);
        BigDecimal amount = validator.parseAmount("100,50");
        String fromCode = validator.validateCode("EUR");
        String toCode = validator.validateCode("USD");

        CurrencyConverter converter = new CurrencyConverter(repository);
        BigDecimal result = converter.convert(amount, fromCode, toCode);

        System.out.printf(
                "%s %s = %s %s%n",
                amount,
                fromCode,
                result,
                toCode
        );
    } catch (RuntimeException e) {
        System.err.println("Nie udało się pobrać kursów walut.");
        System.err.println("Powód: " + e.getMessage());
    }
}
