# Kalkulator walut

Aplikacja pobiera aktualne kursy walut z serwisu Narodowego Banku Polskiego, a następnie pozwala użytkownikowi wprowadzić kwotę oraz wybrać walutę źródłową i docelową. Na podstawie podanych danych program wykonuje konwersję, wyświetla obliczony wynik z dokładnością do dwóch miejsc po przecinku, a na końcu oczekuje na naciśnięcie klawisza Enter w celu zakończenia działania.

Przeliczenie odbywa się przez PLN:

```text
kwota w walucie docelowej =
kwota źródłowa × kurs waluty źródłowej / kurs waluty docelowej
```

Kwota wynikowa jest zaokrąglana do dwóch miejsc po przecinku.

## Zastosowane rozwiązania

- **Pobieranie danych z NBP** — `HttpFetcher` pobiera aktualną tabelę kursów z wykorzystaniem HTTP, dzięki czemu aplikacja korzysta z rzeczywistych danych.
- **Parsowanie XML** — `NbpXmlParser` zamienia dane XML na obiekty `Currency`, co oddziela format danych NBP od pozostałej części aplikacji.
- **Interfejsy** — `Fetcher`, `RatesParser` i `RatesProvider` definiują sposób współpracy komponentów. Ułatwia to wymianę implementacji oraz testowanie aplikacji.
- **Walidacja danych** — `InputValidator` sprawdza kwotę i kod waluty przed wykonaniem obliczeń, aby ograniczyć ryzyko błędów w trakcie działania programu.
- **`BigDecimal`** — do obsługi kwot i kursów użyto `BigDecimal`, ponieważ zapewnia dokładniejsze obliczenia niż typy zmiennoprzecinkowe.
- **Separacja warstw** — `ConsoleUI` odpowiada za komunikację z użytkownikiem, a logika pobierania danych, walidacji i konwersji znajduje się w osobnych klasach. Dzięki temu kod jest czytelniejszy i łatwiejszy do modyfikacji.

## Zastosowane zasady i wzorce

- **SRP** — każda klasa ma jedno główne zadanie. Dzięki temu zmiana interfejsu użytkownika nie wymaga modyfikowania logiki konwersji.
- **DIP** — `NbpRatesProvider` korzysta z abstrakcji `Fetcher` i `RatesParser`, a nie z konkretnych implementacji. Ułatwia to podmianę źródła danych.
- **OCP** — sposób pobierania i parsowania kursów można rozszerzać przez dodanie nowych implementacji interfejsów bez zmieniania istniejącej logiki.
- **Singleton** — `RatesRepository` udostępnia jedną współdzieloną instancję przechowującą załadowane kursy walut.
