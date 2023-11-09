package weatherwear.stubs.weatherservice.future;

import org.json.JSONObject;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;

public class WeatherNotRainy implements WeatherService {
    @Override
    public JSONObject Weather(String uri) throws IOException, InterruptedException {
        String rain = "{\"forecast\":{\"forecastday\":[{\"day\":{\"totalprecip_mm\": 0.0}}]}}";
        return  new JSONObject(rain);
    }
}