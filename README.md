# Kalkulator walut

Aplikacja pobiera aktualne kursy walut z serwisu Narodowego Banku Polskiego, a następnie pozwala użytkownikowi wprowadzić kwotę oraz wybrać walutę źródłową i docelową. Na podstawie podanych danych program wykonuje konwersję, wyświetla obliczony wynik z dokładnością do dwóch miejsc po przecinku, a na końcu oczekuje na naciśnięcie klawisza Enter w celu zakończenia działania.

Przeliczenie odbywa się przez PLN:

```text
kwota w walucie docelowej =
kwota źródłowa × kurs waluty źródłowej / kurs waluty docelowej
```

Kwota wynikowa jest zaokrąglana do dwóch miejsc po przecinku.

## Zastosowane zasady i wzorce

- **SRP** — `ConsoleUI` odpowiada za komunikację z użytkownikiem, `InputValidator` za walidację danych, a `CurrencyConverter` za obliczenia.
- **DIP** — pobieranie danych i parsowanie kursów odbywa się przez interfejsy `Fetcher`, `RatesParser` i `RatesProvider`.
- **OCP** — sposób pobierania kursów lub ich parsowania można rozszerzać przez dodanie nowych implementacji interfejsów.
- **Singleton** — `RatesRepository` udostępnia jedną współdzieloną instancję przechowującą załadowane kursy walut.
