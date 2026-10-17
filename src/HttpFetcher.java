import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class HttpFetcher implements Fetcher {
    private final String url;

    public HttpFetcher(String url) {
        this.url = url;
    }

    @Override
    public String fetch() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new java.net.URI(url))
                    .build();

            HttpResponse<byte[]> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofByteArray()
            );

            if (response.statusCode() != 200) {
                throw new IllegalStateException(
                        "NBP zwrócił status HTTP: " + response.statusCode()
                );
            }

            return new String(
                    response.body(),
                    Charset.forName("ISO-8859-2")
            );
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Nie udało się pobrać danych z NBP",
                    e
            );
        }
    }
}
