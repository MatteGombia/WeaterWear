package weatherwear.stubs.weatherservice.future;

import org.json.JSONObject;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;

public class WeatherRainy implements WeatherService {
    @Override
    public JSONObject Weather(String uri) throws IOException, InterruptedException {
        String rain = "{\"forecast\":{\"forecastday\":[{\"day\":{\"totalprecip_mm\": 0.1}}]}}";
        return  new JSONObject(rain);
    }
}
