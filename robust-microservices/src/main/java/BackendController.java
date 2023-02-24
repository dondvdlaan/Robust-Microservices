import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class to communicate with backend service
 */
@Path("/hello")
public class BackendController {

    // ---- Attributes ----
    public static final String  URL_BACKEND = "http://localhost:8080/backend/hello";

    private AtomicLong counter = new AtomicLong(0);

    // ---- Routes ----
    @Retry(maxRetries = 4)
    //@Timeout(2000)
    //@CircuitBreaker(requestVolumeThreshold = 1, delay = 25000)
    @Fallback(fallbackMethod = "fallbackRecommendations")
    @GET
    public String testBackend() throws IOException, InterruptedException {

        final Long invocationNumber = counter.getAndIncrement();

        System.out.println("invocationNumber: " + invocationNumber);

        // Create Client
        HttpClient client = HttpClient.newHttpClient();

        // Compose request
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(URL_BACKEND))
                .headers("Accept-Enconding", "gzip, deflate")
                .build();

        // Send an receive from backend
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        System.out.println("httpGetRequest: " + responseBody);
        System.out.println("httpGetRequest status code: " + responseStatusCode);

        return responseBody;
    }

    // ---- Methods ----
    public String fallbackRecommendations(){

        System.out.println(("Recommendations here"));

        return "No recommendations, sorry";
    }
}
