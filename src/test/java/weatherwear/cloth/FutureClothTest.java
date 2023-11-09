package weatherwear.cloth;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherwear.stubs.weatherservice.future.WeatherCold;
import weatherwear.stubs.weatherservice.future.WeatherNotRainy;
import weatherwear.stubs.weatherservice.future.WeatherRainy;
import weatherwear.stubs.weatherservice.future.WeatherWarm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FutureClothTest {
    FutureCloth futureCloth;
    String date;
    String codeIATA;
    @BeforeEach
    public void setup(){
        date = LocalDate.now().plusDays(10).toString();

        codeIATA = new String("LON");
    }
    @Test
    public void testFutureWeather10thday(){
        //setup
        futureCloth = new FutureCloth();
        JSONObject result;

        //exercise
        try {
            result = futureCloth.futureWeather(date, codeIATA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(result.toString());
        //
        Assertions.assertEquals("London", result.getJSONObject("location").getString("name"));
    }

    @Test
    public void testFutureWeatherToday(){
        //setup
        date = LocalDate.now().toString();
        futureCloth = new FutureCloth();
        JSONObject result;

        //exercise
        try {
            result = futureCloth.futureWeather(date, codeIATA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(result.toString());
        //
        Assertions.assertEquals("London", result.getJSONObject("location").getString("name"));
    }

    @Test
    public void testFutureWeatherErrorDataGeneric(){
        //setup
        futureCloth = new FutureCloth();
        JSONObject result;
        String dateWrong = "rkebfdf";

        //exercise
        try {
            result = futureCloth.futureWeather(dateWrong, codeIATA);
            Assertions.assertTrue(false);
        } catch (DateTimeParseException e) {
            Assertions.assertTrue(true);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFutureWeatherDateError11thDay(){
        //setup
        futureCloth = new FutureCloth();
        JSONObject result;
        String dateWrong = LocalDate.now().plusDays(11).toString();

        //exercise
        try {
            result = futureCloth.futureWeather(dateWrong, codeIATA);
            Assertions.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFutureWeatherDateErrorYesterday(){
        //setup
        futureCloth = new FutureCloth();
        JSONObject result;
        String dateWrong = LocalDate.now().plusDays(-1).toString();

        //exercise
        try {
            result = futureCloth.futureWeather(dateWrong, codeIATA);
            Assertions.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFutureWeatherErrorCodeIATA(){
        //setup
        futureCloth = new FutureCloth();
        JSONObject result;
        String codeIATAWrong = "FSDG";

        //exercise
        try {
            result = futureCloth.futureWeather(date, codeIATAWrong);
            Assertions.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsFutureWarmTrue(){
        //setup
        boolean warm;
        String codeEthiopia = "ETH";
        WeatherWarm weatherWarm = new WeatherWarm();
        FutureCloth futureCloth = new FutureCloth(weatherWarm);

        //exercise
        try {
            warm = futureCloth.isFutureWarm(date, codeEthiopia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //
        Assertions.assertTrue(warm);
    }
    @Test
    public void testIsFutureWarmFalse(){
        //setup
        boolean warm;
        String codeSib = "NJC"; //siberia
        WeatherCold weatherCold = new WeatherCold();
        FutureCloth futureCloth = new FutureCloth(weatherCold);


        //exercise
        try {
            warm = futureCloth.isFutureWarm(date, codeSib);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //
        Assertions.assertFalse(warm);
    }

    @Test
    public void testIsFutureRainyTrue(){
        //setup
        boolean rain;
        WeatherRainy weatherRainy = new WeatherRainy();
        FutureCloth futureCloth = new FutureCloth(weatherRainy);

        //exercise
        try {
            rain = futureCloth.isFutureRainy(date, codeIATA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(rain);
        //
        Assertions.assertTrue(rain);
    }

    @Test
    public void testIsFutureRainyFalse(){
        //setup
        boolean rain;
        WeatherNotRainy weatherNotRainy = new WeatherNotRainy();
        FutureCloth futureCloth = new FutureCloth(weatherNotRainy);

        //exercise
        try {
            rain = futureCloth.isFutureRainy(date, codeIATA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(rain);
        //
        Assertions.assertFalse(rain);
    }
}
