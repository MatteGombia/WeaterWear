package weatherwear.services;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherwear.services.ipservice.IPServiceAPI;
import weatherwear.services.weatherservice.WeatherService;
import weatherwear.services.weatherservice.WeatherServiceAPI;

import java.io.IOException;
import java.time.LocalDate;

public class WeatherServiceTest {
    WeatherService weatherService;

    @BeforeEach
    public void setup(){
        weatherService = new WeatherServiceAPI();
    }
    @Test
    public void testCurrentWeatherAPI(){
        //setup
        String uri = "https://weatherapi-com.p.rapidapi.com/current.json?q=35.8958%2C14.5";
        JSONObject result;

        //exercise
        try {
            result = weatherService.Weather(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(result.toString());
        //
        Assertions.assertEquals("Malta", result.getJSONObject("location").getString("country"));
    }

    @Test
    public void testForecastWeatherAPI(){
        //setup
        String uri = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=MLA&dt=" + LocalDate.now().plusDays(10).toString();
        JSONObject result;

        //exercise
        try {
            result = weatherService.Weather(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(result.toString());
        //
        Assertions.assertEquals("Malta", result.getJSONObject("location").getString("country"));
    }
}
