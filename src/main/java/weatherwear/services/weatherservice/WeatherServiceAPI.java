package weatherwear.services.weatherservice;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class WeatherServiceAPI implements WeatherService {
    public JSONObject Weather(String uri) throws IOException, InterruptedException, RuntimeException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", "2862ace7fdmsh4c5804c0f21f9ddp13b338jsnb8e745c564e1")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Optional<String> statusCode = response.headers().firstValue(":status");
        if (statusCode.get().equals("200"))
            return new JSONObject(response.body());
        else
            throw new InterruptedException();
    }
}
