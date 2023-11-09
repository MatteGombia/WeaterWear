package weatherwear.stubs.weatherservice.future;

import org.json.JSONObject;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;

public class WeatherWarm implements WeatherService {
    @Override
    public JSONObject Weather(String uri) throws IOException, InterruptedException {
        String avgtemp = "{\"forecast\":{\"forecastday\":[{\"day\":{\"avgtemp_c\": 15.0}}]}}";
        return  new JSONObject(avgtemp);
    }
}