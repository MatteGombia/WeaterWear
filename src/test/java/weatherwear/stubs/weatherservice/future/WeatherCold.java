package weatherwear.stubs.weatherservice.future;

import org.json.JSONObject;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;

public class WeatherCold implements WeatherService {
    @Override
    public JSONObject Weather(String uri) throws IOException, InterruptedException {
        String avgtemp = "{\"forecast\":{\"forecastday\":[{\"day\":{\"avgtemp_c\": 14.9}}]}}";
        return  new JSONObject(avgtemp);
    }
}
