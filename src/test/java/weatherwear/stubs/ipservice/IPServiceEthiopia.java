package weatherwear.stubs.ipservice;

import org.json.JSONObject;
import weatherwear.services.ipservice.IPService;

import java.io.IOException;

public class IPServiceEthiopia implements IPService {
    @Override
    public JSONObject catchIp() throws IOException, InterruptedException, RuntimeException {
        String latlon = "{\"lat\":\"" + "14.8942141" + "\",\"lon\":\"" + "32.9977340" + "\",\"status\":\"success\"}";
        return  new JSONObject(latlon);
    }

    @Override
    public JSONObject catchIpBackup() throws InterruptedException {
        String latlon = "{\"lat\":\"" + "14.8942141" + "\",\"lon\":\"" + "32.9977340" + "\",\"status\":\"success\"}";
        return  new JSONObject(latlon);
    }
}
