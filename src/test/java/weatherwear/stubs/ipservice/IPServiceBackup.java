package weatherwear.stubs.ipservice;

import org.json.JSONObject;
import weatherwear.services.ipservice.IPService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class IPServiceBackup implements IPService {
    @Override
    public JSONObject catchIp() throws IOException, InterruptedException, RuntimeException {
        String endpoint = "http://ip-api.com/json/?fields=status,message,countryCode,country,query,lat,lon";
        Duration timeout = Duration.ofNanos(1);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .timeout(timeout)
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int responseCode = response.statusCode();

        if (responseCode == 200) {
            JSONObject responseObject = new JSONObject(response.body());
            if (responseObject.getString("status").equals("success")) {
                return responseObject;
            } else {
                throw new InterruptedException(responseObject.getString("status"));
            }
        } else {
            throw new InterruptedException(Integer.toString(responseCode));
        }
    }
    @Override
    public JSONObject catchIpBackup() throws InterruptedException {
        String latlon = "{\"lat\":\"" + "14.8942141" + "\",\"lon\":\"" + "32.9977340" + "\",\"status\":\"success\"}";
        return  new JSONObject(latlon);
    }
}
