
void main(String[] args) {
    try {
        Fetcher fetcher = new HttpFetcher(
                "https://static.nbp.pl/dane/kursy/xml/lastA.xml"
        );
        RatesParser parser = new NbpXmlParser();
        RatesProvider provider = new NbpRatesProvider(fetcher, parser);

        for (Currency currency : provider.getRates()) {
            System.out.printf(
                    "%s (%s): %s PLN, przelicznik: %d%n",
                    currency.getName(),
                    currency.getCode(),
                    currency.getAverageRatePerUnit(),
                    currency.getMultiplier()
            );
        }
    } catch (RuntimeException e) {
        System.err.println("Nie udało się pobrać kursów walut.");
        System.err.println("Powód: " + e.getMessage());
    }
}
