package weatherwear.cloth;

// currentWeather
import java.io.IOException;

//catchIp
import org.json.JSONObject;
import weatherwear.services.ipservice.IPService;
import weatherwear.services.ipservice.IPServiceAPI;
import weatherwear.services.weatherservice.WeatherService;

public class CurrentCloth extends Cloth {
    private IPService ipService;
    public CurrentCloth() {
        super();
        ipService = new IPServiceAPI();
    }

    public CurrentCloth(WeatherService weatherService, IPService ipService) {
        super(weatherService);
        this.ipService = ipService;
    }

    public JSONObject currentWeather(String lat, String lon) throws IOException, InterruptedException {
        String uri = "https://weatherapi-com.p.rapidapi.com/current.json?q=" + lat + "%2C" + lon;
        return weatherService.Weather(uri);
    }
    public boolean isCurrentlyWarm() throws IOException, InterruptedException {
        //Take Ip and location
        JSONObject ipCatcher;

        try{
            ipCatcher = ipService.catchIp();
        }
        catch (Exception e){
            try {
                ipCatcher = ipService.catchIpBackup();
            }
            catch (InterruptedException ex){
                throw new RuntimeException();
            }
        }

        Double lat = ipCatcher.getDouble("lat");
        Double lon = ipCatcher.getDouble("lon");

        //Ask for the current weather
        JSONObject currentWeather = currentWeather(lat.toString(), lon.toString());


        float temp = currentWeather.getJSONObject("current").getFloat("temp_c");
        //System.out.println(temp);
        //compare the temp
        return isWarm(temp);
    }

    public boolean isCurrentlyRainy() throws IOException, InterruptedException {
        JSONObject ipCatcher;
        //Take Ip and location
        try{
            ipCatcher = ipService.catchIp();
        }
        catch (Exception e){
            try {
                ipCatcher = ipService.catchIpBackup();
            }
            catch (Exception ex){
                throw new RuntimeException();
            }
        }


        Double lat = ipCatcher.getDouble("lat");
        Double lon = ipCatcher.getDouble("lon");

        //Ask for the current weather
        JSONObject currentWeather = currentWeather(lat.toString(), lon.toString());

        float rain_mm = currentWeather.getJSONObject("current").getFloat("precip_in");
        return isRainy(rain_mm);
    }

}
