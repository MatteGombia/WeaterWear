package weatherwear.stubs.weatherservice.current;

import org.json.JSONObject;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;

public class WeatherCurrentRainy implements WeatherService {
    @Override
    public JSONObject Weather(String uri) throws IOException, InterruptedException {
        String rain = "{\"current\":{\"precip_in\":0.1}}]}}";
        return  new JSONObject(rain);
    }
}
