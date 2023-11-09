package weatherwear.services.ipservice;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.IPGeolocationAPI;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class IPServiceAPI implements IPService{
    public JSONObject catchIp() throws IOException, InterruptedException {
        String endpoint = "http://ip-api.com/json/?fields=status,message,countryCode,country,query,lat,lon";
        Duration timeout = Duration.ofSeconds(3);

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


    public JSONObject catchIpBackup() throws InterruptedException {
        // Create IPGeolocationAPI object, passing your valid API key
        IPGeolocationAPI api = new IPGeolocationAPI("d27b192584b74efba3b95886980ace8f");

        // Get geolocation for the calling machine's IP address for all fields
        Geolocation geolocation = api.getGeolocation();

        if(geolocation.getStatus() == 200) {
            String latlon = "{\"lat\":\"" + geolocation.getLatitude() + "\",\"lon\":\"" + geolocation.getLongitude() + "\",\"status\":\"success\"}";
            return  new JSONObject(latlon);
        } else {
            throw new InterruptedException(Integer.toString(geolocation.getStatus()));
        }
    }


}
