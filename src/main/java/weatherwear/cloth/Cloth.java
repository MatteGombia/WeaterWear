package weatherwear.cloth;

import weatherwear.services.weatherservice.WeatherService;
import weatherwear.services.weatherservice.WeatherServiceAPI;

public class Cloth {
    protected WeatherService weatherService;

    protected Cloth() {
        this.weatherService = new WeatherServiceAPI();
    }

    protected Cloth(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    protected boolean isWarm(float temp){
        return temp >= 15;
    }

    protected boolean isRainy(float rain){
        return rain > 0;
    }
}
