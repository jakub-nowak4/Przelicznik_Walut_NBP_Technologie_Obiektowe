
import java.util.Scanner;

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
        CurrencyConverter converter = new CurrencyConverter(repository);
        ConsoleUI ui = new ConsoleUI(
                new Scanner(System.in),
                validator,
                converter
        );

        ui.run();
    } catch (RuntimeException e) {
        System.err.println("Nie udało się pobrać kursów walut.");
        System.err.println("Powód: " + e.getMessage());
    }
}
