package weatherwear.services.weatherservice;

import org.json.JSONObject;

import java.io.IOException;

public interface WeatherService {
    public JSONObject Weather(String uri) throws IOException, InterruptedException;
}
