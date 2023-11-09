package weatherwear.cloth;

import org.json.JSONObject;
import weatherwear.services.ipservice.IPServiceAPI;
import weatherwear.services.weatherservice.WeatherService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FutureCloth extends Cloth {
    public FutureCloth() {
    }

    public FutureCloth(WeatherService weatherService) {
        super(weatherService);
    }

    public JSONObject futureWeather(String date, String codeIATA) throws IOException, InterruptedException, DateTimeParseException, IllegalArgumentException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(codeIATA.length() != 3)
            throw new IllegalArgumentException("Error code IATA, it must be composed of 3 letters");
        LocalDate arrivalDate = LocalDate.parse(date, format);
        if(arrivalDate.isBefore(LocalDate.now()) || arrivalDate.isAfter(LocalDate.now().plusDays(10)))
          throw new IllegalArgumentException("Error Date, the date should be between today and next 10 day");
        String uri = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + codeIATA + "&dt=" + date.toString();
        return weatherService.Weather(uri);
    }

    public boolean isFutureWarm(String date, String codeIATA) throws IOException, InterruptedException, IllegalArgumentException, DateTimeParseException {
        JSONObject futureWeather = futureWeather(date, codeIATA);
        float temp = futureWeather.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getFloat("avgtemp_c");
        //System.out.println(temp);
        //compare the temp
        return isWarm(temp);
    }


    public boolean isFutureRainy(String date, String codeIATA) throws IOException, InterruptedException, DateTimeParseException, IllegalArgumentException{
        JSONObject futureWeather = futureWeather(date, codeIATA);
        float rain_mm = futureWeather.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getFloat("totalprecip_mm");
        //System.out.println(rain_mm);
        //compare the rain
        return isRainy(rain_mm);
    }
}
