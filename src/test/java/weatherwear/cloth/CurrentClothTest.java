package weatherwear.cloth;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import weatherwear.services.ipservice.IPServiceAPI;
import weatherwear.services.weatherservice.WeatherServiceAPI;
import weatherwear.stubs.ipservice.IPServiceEthiopia;
import weatherwear.stubs.ipservice.IPServiceBackup;
import weatherwear.stubs.weatherservice.current.WeatherCurrentNotRainy;
import weatherwear.stubs.weatherservice.current.WeatherCurrentRainy;
import weatherwear.stubs.weatherservice.future.WeatherNotRainy;
import weatherwear.stubs.weatherservice.future.WeatherRainy;

import java.io.IOException;

public class CurrentClothTest {
    @Test
    public void testCurrentWeather(){
        //setup
        JSONObject result;
        CurrentCloth currentCloth = new CurrentCloth();

        //exercise
        try {
            result = currentCloth.currentWeather("35.8958", "14.5"); //MALTA
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
    public void testIsCurrentlyWarmTrue(){
        //Setup
        IPServiceEthiopia ipService = new IPServiceEthiopia();
        WeatherServiceAPI weatherService = new WeatherServiceAPI();
        CurrentCloth currentCloth = new CurrentCloth(weatherService, ipService);

        //exercise
        try {
            Assertions.assertTrue(currentCloth.isCurrentlyWarm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testWarm_IpTimeout(){
        //Setup
        IPServiceBackup ipService = new IPServiceBackup();
        WeatherServiceAPI weatherService = new WeatherServiceAPI();
        CurrentCloth currentCloth = new CurrentCloth(weatherService, ipService);

        //exercise
        try {
            Assertions.assertTrue(currentCloth.isCurrentlyWarm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsCurrentlyRainFalse(){
        //setup
        IPServiceAPI ipService = new IPServiceAPI();
        WeatherCurrentNotRainy weatherService = new WeatherCurrentNotRainy();
        CurrentCloth currentCloth = new CurrentCloth(weatherService, ipService);

        //exercise
        try {
            Assertions.assertFalse(currentCloth.isCurrentlyRainy());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRain_IpTimeout(){
        //setup
        IPServiceBackup ipService = new IPServiceBackup();
        WeatherCurrentRainy weatherService = new WeatherCurrentRainy();
        CurrentCloth currentCloth = new CurrentCloth(weatherService, ipService);

        //exercise
        try {
            Assertions.assertTrue(currentCloth.isCurrentlyRainy());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
