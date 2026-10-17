import java.util.List;

public interface RatesParser {
    List<Currency> parse(String data);
}
