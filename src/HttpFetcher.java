import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpFetcher implements Fetcher{
    final private String url;

    public HttpFetcher(String url) {
        this.url = url;
    }

    @Override
    public String fetch() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new java.net.URI(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch data from URL: " + url + ", Status code: " + response.statusCode());
            }
            return response.body();

        } catch (Exception e) {
            System.out.println("Error fetching data from URL: " + e.getMessage());
            return null;
        }
    }
}
