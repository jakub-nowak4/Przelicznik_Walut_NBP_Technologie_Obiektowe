import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final InputValidator validator;
    private final CurrencyConverter converter;

    public ConsoleUI(
            Scanner scanner,
            InputValidator validator,
            CurrencyConverter converter
    ) {
        this.scanner = scanner;
        this.validator = validator;
        this.converter = converter;
    }

    public void run() {
        BigDecimal amount = readAmount();
        String fromCode = readCurrencyCode("Podaj walutę źródłową: ");
        String toCode = readCurrencyCode("Podaj walutę docelową: ");

        BigDecimal result = converter.convert(amount, fromCode, toCode);

        System.out.printf("%s %s = %s %s%n",
                amount, fromCode, result, toCode);

        System.out.println("Naciśnij Enter, aby zakończyć.");
        scanner.nextLine();
    }

    private BigDecimal readAmount() {
        while (true) {
            System.out.print("Podaj kwotę: ");

            try {
                return validator.parseAmount(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }

    private String readCurrencyCode(String message) {
        while (true) {
            System.out.print(message);

            try {
                return validator.validateCode(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }
}
